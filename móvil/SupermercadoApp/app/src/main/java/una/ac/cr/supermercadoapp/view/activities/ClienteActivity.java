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
import una.ac.cr.supermercadoapp.domain.Cliente;
import una.ac.cr.supermercadoapp.utils.MonitorRedUtils;
import una.ac.cr.supermercadoapp.data.ClienteData;
import una.ac.cr.supermercadoapp.domain.AdministradorRed;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.network.VolleySingleton;
import una.ac.cr.supermercadoapp.network.VolleyCliente;
import una.ac.cr.supermercadoapp.utils.MonitorRedUtils;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.adapters.ClienteAdapter;
import una.ac.cr.supermercadoapp.view.interfaces.ClienteICallback;


public class ClienteActivity extends AppCompatActivity {

    private SharedPreferences credenciales;
    private LottieAnimationView iconAgregar, iconCliente;

    private RecyclerView recyclerCliente;
    private RecyclerView.Adapter mAdaptadorCliente;
    private ArrayList<Cliente> listaClientes;
    private SearchView searchCliente;
    public static final int REQUEST_CODE = 1;

    private LottieAnimationView iconMenu;

    private PowerMenu powerMenu;

    private ClienteData clienteData;
    public MonitorRedUtils monitorRedUtils;

    private OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener = new OnMenuItemClickListener<PowerMenuItem>() {
        @Override
        public void onItemClick(int position, PowerMenuItem item) {

            if(item.title.equals("Membresias")){
                powerMenu.dismiss();
                Intent intent = new Intent(ClienteActivity.this, MembresiaActivity.class);
                startActivity(intent);
                finish();
            } else if(item.title.equals("Descuentos")){
                powerMenu.dismiss();
                Intent intent = new Intent(ClienteActivity.this, DescuentoActivity.class);
                startActivity(intent);
                finish();
            } else if(item.title.equals("Refrescar área")){
                powerMenu.dismiss();
                Intent intent = new Intent(ClienteActivity.this, ClienteActivity.class);
                startActivity(intent);
                finish();

            } else if(item.title.equals("Menú principal")){
                powerMenu.dismiss();
                Intent intent = new Intent(ClienteActivity.this, MainActivity.class);
                startActivity(intent);
                finish();

            }
        }
    };
    //API para manejar la iniciación de actividades y la recepción de resultados de actividades
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
        setContentView(R.layout.activity_cliente);
        credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        verificarEstadoSesion(cedula);
        configurarRecycler();
        iniciarWidgets();
        agregarEventos();

        clienteData = new ClienteData(this);

        iconAgregar = findViewById(R.id.icon_ver_clientes);
        iconAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ClienteActivity.this, FormularioClienteActivity.class);
                intent.putExtra("metodo","agregar");
                launcher.launch(intent);

            }
        });
        iconMenu = findViewById(R.id.icon_ver_clientes);
        int primaryColor = ContextCompat.getColor(this, R.color.primary);
        iconMenu.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(primaryColor)));
        iconMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (powerMenu.isShowing()) {
                    powerMenu.dismiss();
                    return;
                }
                powerMenu.showAsDropDown(view);
            }
        });

        powerMenu = new PowerMenu.Builder(this)
                .addItem(new PowerMenuItem("Membresias", false)) // add an item.
                .addItem(new PowerMenuItem("Descuentos", false)) // add an item.
                .addItem(new PowerMenuItem("Refrescar área", false)) // add an item.
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

    }



    private void configurarRecycler() {
        recyclerCliente = findViewById(R.id.recycler_view_cliente);
        recyclerCliente.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerCliente.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .drawable(R.drawable.divider)
                .size(3)
                .margin(18,18)
                .build());

        //Aquí se tendría que llenar la lista auxiliar con lo que hay en la BD del server
        VolleyCliente volleyCliente = new VolleyCliente();

        ClienteICallback listener = new ClienteICallback() {
            @Override
            public void onClientesReceived(ArrayList<Cliente> lista) {
                listaClientes = lista;
                mAdaptadorCliente = new ClienteAdapter(launcher, listaClientes, getApplicationContext());
                ((ClienteAdapter)mAdaptadorCliente).setOnItemClickListener(new ClienteAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        volleyCliente.eliminarCliente(ClienteActivity.this, listaClientes.get(position),credenciales.getString("ip", "192.168.100.216"));
                        actualizarLista();

                    }
                });
                ((ClienteAdapter)mAdaptadorCliente).setMode(Attributes.Mode.Single);
                recyclerCliente.setAdapter(mAdaptadorCliente);

                recyclerCliente.addOnScrollListener(onScrollListener);

            }
        };

        volleyCliente.obtenerClientes(this,credenciales.getString("ip", "192.168.100.216"),listener);

    }


    private void iniciarWidgets() {
        // cardViewTitulo = findViewById(R.id.contenedor_titulo);
        searchCliente = findViewById(R.id.barra_busqueda_cliente);
        searchCliente.clearFocus();
        iconCliente = findViewById(R.id.icon_clientes);
        iconAgregar = findViewById(R.id.icon_add_cliente);
        int whiteColor = ContextCompat.getColor(this, R.color.white);
        iconCliente .addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(whiteColor)));


    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(ClienteActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }

    private void actualizarLista() {
        listaClientes.clear();
        VolleyCliente volleyCliente = new VolleyCliente();
        if(mAdaptadorCliente == null){
            return;
        }else{

            ClienteICallback listener = new ClienteICallback() {
                @Override
                public void onClientesReceived(ArrayList<Cliente> lista) {
                    listaClientes = lista;
                    ((ClienteAdapter)mAdaptadorCliente ).setListaClientes(listaClientes);
                    mAdaptadorCliente.notifyDataSetChanged();
                }
            };

            volleyCliente.obtenerClientes(this,credenciales.getString("ip", "192.168.100.216"),listener);

        }


    }

    public void filtrar(String dato){
        ArrayList<Cliente> filtrado = new ArrayList<>();
        VolleyCliente volleyCliente = new VolleyCliente();
        ClienteICallback listener = new ClienteICallback() {
            @Override
            public void onClientesReceived(ArrayList<Cliente> lista) {
                listaClientes = lista;
                for(Cliente tu : listaClientes){
                    if(tu.getNombre().toLowerCase().contains(dato.toLowerCase())){
                        filtrado.add(tu);
                    }
                }

                listaClientes = filtrado;
                ((ClienteAdapter)mAdaptadorCliente).setListaClientes(listaClientes);

                mAdaptadorCliente.notifyDataSetChanged();
            }
        };

        volleyCliente.obtenerClientes(this,credenciales.getString("ip", "192.168.100.216"),listener);


    }

    private void agregarEventos() {
        searchCliente.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

        iconAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ClienteActivity.this, FormularioClienteActivity.class);
                intent.putExtra("metodo","agregar");
                launcher.launch(intent);

            }
        });

    }

}