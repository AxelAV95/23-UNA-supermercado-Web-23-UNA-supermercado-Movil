package una.ac.cr.supermercadoapp.view.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.daimajia.swipe.util.Attributes;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.data.DescuentoData;
import una.ac.cr.supermercadoapp.data.ProductoData;
import una.ac.cr.supermercadoapp.data.TipoEmpleadoData;
import una.ac.cr.supermercadoapp.domain.AdministradorRed;
import una.ac.cr.supermercadoapp.domain.Descuento;
import una.ac.cr.supermercadoapp.domain.Empleado;
import una.ac.cr.supermercadoapp.domain.Producto;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.network.VolleyDescuento;
import una.ac.cr.supermercadoapp.network.VolleyEmpleado;
import una.ac.cr.supermercadoapp.network.VolleyProducto;
import una.ac.cr.supermercadoapp.network.VolleySingleton;
import una.ac.cr.supermercadoapp.network.VolleyTipoEmpleado;
import una.ac.cr.supermercadoapp.network.VolleyTipoUsuario;
import una.ac.cr.supermercadoapp.utils.MonitorRedUtils;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.adapters.DescuentoAdapter;
import una.ac.cr.supermercadoapp.view.adapters.EmpleadoAdapter;
import una.ac.cr.supermercadoapp.view.adapters.ProductoAdapter;
import una.ac.cr.supermercadoapp.view.adapters.TipoEmpleadoAdapter;
import una.ac.cr.supermercadoapp.view.adapters.TipoUsuarioAdapter;
import una.ac.cr.supermercadoapp.view.interfaces.DescuentoICallback;
import una.ac.cr.supermercadoapp.view.interfaces.EmpleadoICallback;
import una.ac.cr.supermercadoapp.view.interfaces.ProductoICallback;
import una.ac.cr.supermercadoapp.view.interfaces.TipoEmpleadoICallback;
import una.ac.cr.supermercadoapp.view.interfaces.TipoUsuarioICallback;

public class DescuentoActivity extends AppCompatActivity {

    private SharedPreferences credenciales;
    private LottieAnimationView iconAgregar, iconDescuento;

    private RecyclerView recyclerDescuento;
    private RecyclerView.Adapter mAdaptadorDescuento;
    private ArrayList<Descuento> listaDescuentos;
    private SearchView searchDescuento;
    public static final int REQUEST_CODE = 1;

    private DescuentoData descuentoData;
    public MonitorRedUtils monitorRedUtils;
    private PowerMenu powerMenu;

    private OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener = new OnMenuItemClickListener<PowerMenuItem>() {
        @Override
        public void onItemClick(int position, PowerMenuItem item) {

            if(item.title.equals("Menú principal")){
                powerMenu.dismiss();
                String cedula = credenciales.getString("cedula", null);
                String tipo = credenciales.getString("tipo",null);
                if (cedula != null ) {

                    if(tipo.equals("Administrador")){
                        Intent intent = new Intent(DescuentoActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else if(tipo.equals("Empleado")){
                        Intent intent  = new Intent(DescuentoActivity.this, MenuEmpleadoActivity.class);
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
        setContentView(R.layout.activity_descuento);
        descuentoData = new DescuentoData(this);
        listaDescuentos = new ArrayList<>();

        credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        if(cedula == null){
            Intent intent = new Intent(DescuentoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }

        verificarEstadoSesion(cedula);
        configurarRecycler();
        iniciarWidgets();
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

        iconDescuento = findViewById(R.id.icon_ti_descuentos);
        int primaryColor = ContextCompat.getColor(this, R.color.primary);
        iconDescuento.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(primaryColor)));
        iconDescuento.setOnClickListener(new View.OnClickListener() {
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
        recyclerDescuento = findViewById(R.id.recycler_view_descuento);
        recyclerDescuento.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerDescuento.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .drawable(R.drawable.divider)
                .size(3)
                .margin(18,18)
                .build());


        VolleyDescuento volleyDescuento = new VolleyDescuento();

        DescuentoICallback listener = new DescuentoICallback() {
            @Override
            public void onDescuentoReceived(ArrayList<Descuento> lista) {
                listaDescuentos = lista;
                mAdaptadorDescuento = new DescuentoAdapter(launcher, listaDescuentos, getApplicationContext());
                ((DescuentoAdapter)mAdaptadorDescuento).setOnItemClickListener(new DescuentoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        VolleyDescuento volleyDescuento = new VolleyDescuento();
                        DescuentoICallback listener = new DescuentoICallback() {
                            @Override
                            public void onDescuentoReceived(ArrayList<Descuento> lista) {
                                listaDescuentos = lista;
                                ((DescuentoAdapter)mAdaptadorDescuento ).setListaDescuentos(listaDescuentos);
                                mAdaptadorDescuento.notifyDataSetChanged();
                            }
                        };
                        volleyDescuento.eliminarDescuento(DescuentoActivity.this,listaDescuentos.get(position),credenciales.getString("ip", "192.168.100.216"));
                        volleyDescuento.obtenerDescuentos(DescuentoActivity.this,credenciales.getString("ip", "192.168.100.216"),listener);
                    }
                });
                ((DescuentoAdapter)mAdaptadorDescuento).setMode(Attributes.Mode.Single);
                recyclerDescuento.setAdapter(mAdaptadorDescuento);

                recyclerDescuento.addOnScrollListener(onScrollListener);

            }
        };

        volleyDescuento.obtenerDescuentos(this,credenciales.getString("ip", "192.168.100.216"),listener);

    }



    private void actualizarLista() {
        listaDescuentos.clear();
        VolleyDescuento volleyDescuento = new VolleyDescuento();
        if(mAdaptadorDescuento==null){
            return;
        }else{
            DescuentoICallback listener = new DescuentoICallback() {
                @Override
                public void onDescuentoReceived(ArrayList<Descuento> lista) {
                    listaDescuentos = lista;
                    ((DescuentoAdapter)mAdaptadorDescuento ).setListaDescuentos(listaDescuentos);

                    mAdaptadorDescuento.notifyDataSetChanged();
                }
            };

            volleyDescuento.obtenerDescuentos(this, credenciales.getString("ip", "192.168.100.216"), listener);
        }
    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(DescuentoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }


    @Override
    public void onBackPressed() {

    }


    public void filtrar(Float dato){
        ArrayList<Descuento> filtrado = new ArrayList<>();
        VolleyDescuento volleyDescuento = new VolleyDescuento();
        DescuentoICallback listener = new DescuentoICallback() {
            @Override
            public void onDescuentoReceived(ArrayList<Descuento> lista) {
                listaDescuentos = lista;
                for(Descuento descuento : listaDescuentos){
                    if(descuento.getTarifa()==(dato.floatValue())){
                        filtrado.add(descuento);
                    }
                }

                listaDescuentos = filtrado;
                ((DescuentoAdapter)mAdaptadorDescuento).setListaDescuentos(listaDescuentos);

                mAdaptadorDescuento.notifyDataSetChanged();
            }
        };

        volleyDescuento.obtenerDescuentos(this,credenciales.getString("ip", "192.168.100.216"),listener);


    }

    private void agregarEventos() {
        searchDescuento.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
                    filtrar(Float.valueOf(newText));

                }

                return true;
            }
        });

        iconAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DescuentoActivity.this, FormularioDescuentoActivity.class);
                intent.putExtra("metodo","agregar");
                launcher.launch(intent);

            }
        });

    }


    private void iniciarWidgets() {

        searchDescuento = findViewById(R.id.barra_busqueda_te);
        searchDescuento.clearFocus();
        iconDescuento = findViewById(R.id.icon_ti_descuentos);
        iconAgregar = findViewById(R.id.icon_agregar_descuento);
        int whiteColor = ContextCompat.getColor(this, R.color.white);
        iconDescuento .addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(whiteColor)));

    }




}

