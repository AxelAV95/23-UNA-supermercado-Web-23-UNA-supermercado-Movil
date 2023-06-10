package una.ac.cr.supermercadoapp.view.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.daimajia.swipe.util.Attributes;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.data.EmpleadoData;
import una.ac.cr.supermercadoapp.domain.Empleado;
import una.ac.cr.supermercadoapp.domain.Producto;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.network.VolleyEmpleado;
import una.ac.cr.supermercadoapp.network.VolleyProducto;
import una.ac.cr.supermercadoapp.network.VolleyProveedor;
import una.ac.cr.supermercadoapp.network.VolleyTipoEmpleado;
import una.ac.cr.supermercadoapp.utils.MonitorRedUtils;
import una.ac.cr.supermercadoapp.view.adapters.EmpleadoAdapter;
import una.ac.cr.supermercadoapp.view.adapters.ProductoAdapter;
import una.ac.cr.supermercadoapp.view.adapters.TipoEmpleadoAdapter;
import una.ac.cr.supermercadoapp.view.interfaces.EmpleadoICallback;
import una.ac.cr.supermercadoapp.view.interfaces.ProductoICallback;
import una.ac.cr.supermercadoapp.view.interfaces.TipoEmpleadoICallback;

public class EmpleadoActivity extends AppCompatActivity {

    private RecyclerView recyclerEmpleados;

    //Adaptador
    private RecyclerView.Adapter  empleadoAdapter;
    private ArrayList<Empleado> listaEmpleados;
    private SearchView searchEmpleado;
    private LottieAnimationView iconEmpleado;
    private RecyclerView recyclerEmpleado;
    private LottieAnimationView iconAgregar;
    private LottieAnimationView iconMenu;
    private SharedPreferences credenciales;
    private PowerMenu powerMenu;

    public static final int REQUEST_CODE = 1;

    private EmpleadoData empleadoData;
    public MonitorRedUtils monitorRedUtils;
    private ArrayList<Empleado> listaEmpleado;
    private RecyclerView.Adapter mAdaptadorEmpleado;

    private OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener = new OnMenuItemClickListener<PowerMenuItem>() {
        @Override
        public void onItemClick(int position, PowerMenuItem item) {

            if(item.title.equals("Tipos de empleado")){

                powerMenu.dismiss();
                Intent intent = new Intent(EmpleadoActivity.this, TipoEmpleadoActivity.class);
                startActivity(intent);

            }else if(item.title.equals("Menú principal")){
                powerMenu.dismiss();
                String cedula = credenciales.getString("cedula", null);
                String tipo = credenciales.getString("tipo",null);
                if (cedula != null ) {

                    if(tipo.equals("Administrador")){
                        Intent intent = new Intent(EmpleadoActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else if(tipo.equals("Empleado")){
                        Intent intent  = new Intent(EmpleadoActivity.this, MenuEmpleadoActivity.class);
                        startActivity(intent);
                        finish();
                    }
                }
            }
        }
    };

    ActivityResultLauncher<Intent> launcher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == Activity.RESULT_OK) {
                    Toasty.success(this, "Agregado con éxito", Toast.LENGTH_SHORT, true).show();
                    actualizarLista();
                }
                else if(result.getResultCode() == 200){
                    Toasty.success(this, "Actualizado con éxito", Toast.LENGTH_SHORT, true).show();
                    actualizarLista();
                }else if(result.getResultCode() == 400){
                    Toasty.error(this, "Error al actualizar", Toast.LENGTH_SHORT, true).show();
                }
            }
    );

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            Log.e("ListView", "onScrollStateChanged");
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);
        empleadoData = new EmpleadoData(this);
        listaEmpleados = new ArrayList<>();
        recyclerEmpleado = findViewById(R.id.recycler_view_empleado);

        credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula = credenciales.getString("cedula", null);
        VolleyEmpleado volleyEmpleado = new VolleyEmpleado();

        if (cedula == null) {
            Intent intent = new Intent(EmpleadoActivity.this, LoginActivity.class);
            startActivity(intent);
            ;
            finish();
        }

        verificarEstadoSesion(cedula);
        iniciarWidgets();
        configurarRecycler();
        agregarEventos();


        powerMenu = new PowerMenu.Builder(this)
                .addItem(new PowerMenuItem("Menú principal", false)) // add an item.
                .setAnimation(MenuAnimation.SHOWUP_BOTTOM_RIGHT) // Animation start point (TOP | LEFT).
                .setMenuRadius(10f) // sets the corner radius.
                .setMenuShadow(10f) // sets the shadow.
                .setTextColor(ContextCompat.getColor(this, R.color.dark_gray))
                .setTextGravity(Gravity.CENTER)
                .setTextTypeface(Typeface.create("sans-serif-medium", Typeface.BOLD))
                .setSelectedTextColor(Color.WHITE)
                .setMenuColor(Color.WHITE)
                .setSelectedMenuColor(ContextCompat.getColor(getApplicationContext(), R.color.black))
                .setOnMenuItemClickListener(onMenuItemClickListener)
                .build();

        // iconEmpleado = findViewById(R.id.icon_ver_tipos_empleados);
        int primaryColor = ContextCompat.getColor(this, R.color.primary);
        iconEmpleado.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(primaryColor)));
        iconEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (powerMenu.isShowing()) {
                    powerMenu.dismiss();
                    return;
                }
                powerMenu.showAsDropDown(view);
            }
        });
    }


    private void configurarRecycler() {
        recyclerEmpleados = findViewById(R.id.recycler_view_empleado);
        recyclerEmpleados.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerEmpleados.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .drawable(R.drawable.divider)
                .size(3)
                .margin(18,18)
                .build());

        //Aquí se tendría que llenar la lista auxiliar con lo que hay en la BD del server
        VolleyEmpleado volleyEmpleado = new VolleyEmpleado();

        EmpleadoICallback listener = new EmpleadoICallback() {
            @Override
            public void onEmpleadoReceived(ArrayList<Empleado> lista) {
                listaEmpleados = lista;
                empleadoAdapter = new EmpleadoAdapter(launcher, listaEmpleados, getApplicationContext());
                ((EmpleadoAdapter)empleadoAdapter).setOnItemClickListener(new EmpleadoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        volleyEmpleado.eliminarEmpleado(EmpleadoActivity.this, listaEmpleados.get(position).getId(), credenciales.getString("ip", "192.168.100.216"));
                        actualizarLista();
                    }
                });
                ((EmpleadoAdapter)empleadoAdapter).setMode(Attributes.Mode.Single);
                recyclerEmpleado.setAdapter(empleadoAdapter);

                recyclerEmpleados.addOnScrollListener(onScrollListener);
            }
        };
        volleyEmpleado.obtenerEmpleados(this,credenciales.getString("ip", "192.168.100.216"),listener);
    }

    private void actualizarLista() {
        listaEmpleados.clear();
        VolleyEmpleado volleyEmpleado = new VolleyEmpleado();
        if(empleadoAdapter==null){
            return;
        }else{
            EmpleadoICallback listener = new EmpleadoICallback() {
                @Override
                public void onEmpleadoReceived(ArrayList<Empleado> lista) {
                    listaEmpleados = lista;
                    ((EmpleadoAdapter)empleadoAdapter ).setListaEmpleados(listaEmpleados);

                    empleadoAdapter.notifyDataSetChanged();
                }
            };

            volleyEmpleado.obtenerEmpleados(this, credenciales.getString("ip", "192.168.100.216"), listener);
        }
    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(EmpleadoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }

    @Override
    public void onBackPressed() {
    }

    public void filtrar(String dato){
        ArrayList<Empleado> filtrado = new ArrayList<>();
        VolleyEmpleado volleyEmpleado = new VolleyEmpleado();
        EmpleadoICallback listener = new EmpleadoICallback() {
            @Override
            public void onEmpleadoReceived(ArrayList<Empleado> lista) {
                listaEmpleados = lista;
                for(Empleado tu : listaEmpleados){
                    if(tu.getNombre().toLowerCase().contains(dato.toLowerCase())){
                        filtrado.add(tu);
                    }
                }

                listaEmpleados = filtrado;
                ((EmpleadoAdapter)empleadoAdapter).setListaEmpleados(listaEmpleados);

                empleadoAdapter.notifyDataSetChanged();
            }
        };

        volleyEmpleado.obtenerEmpleados(this,credenciales.getString("ip", "192.168.100.216"),listener);
    }

    private void agregarEventos() {
        searchEmpleado.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                if(newText.equals("")){
                    actualizarLista();
                }else{
                    actualizarLista();
                    filtrar(newText);
                }
                return true;
            }
        });

      //  iconAgregar = findViewById(R.id.icon_add_empleado);
        iconAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmpleadoActivity.this, FormularioEmpleadoActivity.class);
                intent.putExtra("metodo","agregar");
                launcher.launch(intent);
            }
        });
    }

    private void iniciarWidgets() {
        // cardViewTitulo = findViewById(R.id.contenedor_titulo);

        searchEmpleado = findViewById(R.id.barra_busqueda_empleado);
        searchEmpleado.clearFocus();
        iconEmpleado = findViewById(R.id.icon_ver_tipos_empleados);
        iconAgregar = findViewById(R.id.icon_add_empleado);
        int whiteColor = ContextCompat.getColor(this, R.color.white);
       /* iconEmpleado.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(whiteColor)));*/
    }


}