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
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.data.ProveedorData;
import una.ac.cr.supermercadoapp.data.TipoEmpleadoData;
import una.ac.cr.supermercadoapp.domain.AdministradorRed;
import una.ac.cr.supermercadoapp.domain.Proveedor;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.network.VolleyProveedor;
import una.ac.cr.supermercadoapp.network.VolleySingleton;
import una.ac.cr.supermercadoapp.network.VolleyTipoEmpleado;
import una.ac.cr.supermercadoapp.network.VolleyTipoUsuario;
import una.ac.cr.supermercadoapp.utils.MonitorRedUtils;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.adapters.ProveedorAdapter;
import una.ac.cr.supermercadoapp.view.adapters.TipoEmpleadoAdapter;
import una.ac.cr.supermercadoapp.view.adapters.TipoUsuarioAdapter;
import una.ac.cr.supermercadoapp.view.interfaces.ProveedorICallback;
import una.ac.cr.supermercadoapp.view.interfaces.TipoEmpleadoICallback;
import una.ac.cr.supermercadoapp.view.interfaces.TipoUsuarioICallback;

public class ProveedorActivity extends AppCompatActivity {

    private SharedPreferences credenciales;
    private LottieAnimationView iconAgregar, iconProveedor;

    private RecyclerView recyclerProveedor;
    private RecyclerView.Adapter mAdaptadorProveedor;
    private ArrayList<Proveedor> listaProveedores;
    private SearchView searchProveedor;
    private PowerMenu powerMenu;

    public static final int REQUEST_CODE = 1;

    private ProveedorData proveedorData;
    public MonitorRedUtils monitorRedUtils;


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

    private OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener = new OnMenuItemClickListener<PowerMenuItem>() {
        @Override
        public void onItemClick(int position, PowerMenuItem item) {

           if(item.title.equals("Menú principal")){
                powerMenu.dismiss();
               String cedula = credenciales.getString("cedula", null);
               String tipo = credenciales.getString("tipo",null);
               if (cedula != null ) {

                   if(tipo.equals("Administrador")){
                       Intent intent = new Intent(ProveedorActivity.this, MainActivity.class);
                       startActivity(intent);
                       finish();
                   }else if(tipo.equals("Empleado")){
                       Intent intent  = new Intent(ProveedorActivity.this, MenuEmpleadoActivity.class);
                       startActivity(intent);
                       finish();
                   }


               }
            }
            //Toast.makeText(getBaseContext(), item.title, Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proveedor);
        credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        verificarEstadoSesion(cedula);
        configurarRecycler();
        iniciarWidgets();
        agregarEventos();

        proveedorData = new ProveedorData(this);

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

        iconAgregar = findViewById(R.id.icon_add_proveedor);
        iconProveedor = findViewById(R.id.icon_ver_proveedor);
        iconAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(ProveedorActivity.this, FormularioProveedorActivity.class);
                intent.putExtra("metodo","agregar");
                startActivity(intent);

            }
        });

        iconProveedor.setOnClickListener(new View.OnClickListener() {
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
        recyclerProveedor = findViewById(R.id.recycler_view_proveedor);
        recyclerProveedor.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerProveedor.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .drawable(R.drawable.divider)
                .size(3)
                .margin(18,18)
                .build());

        //Aquí se tendría que llenar la lista auxiliar con lo que hay en la BD del server
        VolleyProveedor volleyProveedor = new VolleyProveedor();

        ProveedorICallback listener = new ProveedorICallback() {
            @Override
            public void onProveedorReceived(ArrayList<Proveedor> lista) {
                listaProveedores = lista;
                mAdaptadorProveedor = new ProveedorAdapter(launcher, listaProveedores, getApplicationContext());
                ((ProveedorAdapter)mAdaptadorProveedor).setOnItemClickListener(new ProveedorAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        volleyProveedor.eliminarProveedor(ProveedorActivity.this, listaProveedores.get(position),credenciales.getString("ip", "192.168.100.216"));
                        actualizarLista();

                    }
                });
                ((ProveedorAdapter)mAdaptadorProveedor).setMode(Attributes.Mode.Single);
                recyclerProveedor.setAdapter(mAdaptadorProveedor);

                recyclerProveedor.addOnScrollListener(onScrollListener);

            }
        };

        volleyProveedor.obtenerProveedores(this,credenciales.getString("ip", "192.168.100.216"),listener);

    }


    private void iniciarWidgets() {
        // cardViewTitulo = findViewById(R.id.contenedor_titulo);
        searchProveedor = findViewById(R.id.barra_busqueda_proveedor);
        searchProveedor.clearFocus();
        iconProveedor = findViewById(R.id.icon_proveedores);
        iconAgregar = findViewById(R.id.icon_add_proveedor);



    }



    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(ProveedorActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }



    private void actualizarLista() {
        listaProveedores.clear();
        VolleyProveedor volleyProveedor = new VolleyProveedor();
        if(mAdaptadorProveedor == null){
            return;
        }else{

            ProveedorICallback listener = new ProveedorICallback() {
                @Override
                public void onProveedorReceived(ArrayList<Proveedor> lista) {
                    listaProveedores = lista;
                    ((ProveedorAdapter)mAdaptadorProveedor ).setListaProveedores(listaProveedores);
                    mAdaptadorProveedor.notifyDataSetChanged();
                }
            };

            volleyProveedor.obtenerProveedores(this,credenciales.getString("ip", "192.168.100.216"),listener);

        }


    }
    public void filtrar(String dato){
        ArrayList<Proveedor> filtrado = new ArrayList<>();
        VolleyProveedor volleyProveedor = new VolleyProveedor();
        ProveedorICallback listener = new ProveedorICallback() {
            @Override
            public void onProveedorReceived(ArrayList<Proveedor> lista) {
                listaProveedores = lista;
                for(Proveedor tu : listaProveedores){
                    if(tu.getNombre().toLowerCase().contains(dato.toLowerCase())){
                        filtrado.add(tu);
                    }
                }

                listaProveedores = filtrado;
                ((ProveedorAdapter)mAdaptadorProveedor).setListaProveedores(listaProveedores);

                mAdaptadorProveedor.notifyDataSetChanged();
            }
        };

        volleyProveedor.obtenerProveedores(this,credenciales.getString("ip", "192.168.100.216"),listener);


    }

    private void agregarEventos() {
        searchProveedor.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

                Intent intent = new Intent(ProveedorActivity.this, FormularioProveedorActivity.class);
                intent.putExtra("metodo","agregar");
                launcher.launch(intent);

            }
        });

    }



}