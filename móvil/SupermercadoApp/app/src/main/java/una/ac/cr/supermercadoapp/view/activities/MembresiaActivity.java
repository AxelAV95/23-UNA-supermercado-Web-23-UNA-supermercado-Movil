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
import una.ac.cr.supermercadoapp.data.MembresiaData;
import una.ac.cr.supermercadoapp.domain.Membresia;
import una.ac.cr.supermercadoapp.utils.MonitorRedUtils;
import una.ac.cr.supermercadoapp.data.MembresiaData;
import una.ac.cr.supermercadoapp.domain.AdministradorRed;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.network.VolleySingleton;
import una.ac.cr.supermercadoapp.network.VolleyMembresia;
import una.ac.cr.supermercadoapp.utils.MonitorRedUtils;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.adapters.MembresiaAdapter;
import una.ac.cr.supermercadoapp.view.interfaces.MembresiaICallback;


public class MembresiaActivity extends AppCompatActivity {

        private SharedPreferences credenciales;
        private LottieAnimationView iconAgregar, iconMembresia;

        private RecyclerView recyclerMembresia;
        private RecyclerView.Adapter mAdaptadorMembresia;
        private ArrayList<Membresia> listaMembresias;
        private SearchView searchMembresia;
        public static final int REQUEST_CODE = 1;

    private LottieAnimationView iconMenu;

    private PowerMenu powerMenu;

    private MembresiaData membresiaData;
        public MonitorRedUtils monitorRedUtils;

    private OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener = new OnMenuItemClickListener<PowerMenuItem>() {
        @Override
        public void onItemClick(int position, PowerMenuItem item) {

            if(item.title.equals("Membresias")){
                powerMenu.dismiss();
                Intent intent = new Intent(MembresiaActivity.this, MembresiaActivity.class);
                startActivity(intent);
                finish();
            } else if(item.title.equals("Área clientes")){
                powerMenu.dismiss();
                Intent intent = new Intent(MembresiaActivity.this, ClienteActivity.class);
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
            setContentView(R.layout.activity_membresia);
            credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
            String cedula  = credenciales.getString("cedula", null);

            verificarEstadoSesion(cedula);
            configurarRecycler();
            iniciarWidgets();
            agregarEventos();

            membresiaData = new MembresiaData(this);

            iconAgregar = findViewById(R.id.icon_add_membresia);
            iconAgregar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(MembresiaActivity.this, FormularioMembresiaActivity.class);
                    intent.putExtra("metodo","agregar");
                    startActivity(intent);

                }
            });
            iconMenu = findViewById(R.id.icon_ver_membresias);
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
                    .addItem(new PowerMenuItem("Área clientes", false)) // add an item.
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
            recyclerMembresia = findViewById(R.id.recycler_view_membresia);
            recyclerMembresia.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
            recyclerMembresia.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                    .drawable(R.drawable.divider)
                    .size(3)
                    .margin(18,18)
                    .build());

            //Aquí se tendría que llenar la lista auxiliar con lo que hay en la BD del server
            VolleyMembresia volleyMembresia = new VolleyMembresia();

            MembresiaICallback listener = new MembresiaICallback() {
                @Override
                public void onMembresiasReceived(ArrayList<Membresia> lista) {
                    listaMembresias = lista;
                    mAdaptadorMembresia = new MembresiaAdapter(launcher, listaMembresias, getApplicationContext());
                    ((MembresiaAdapter)mAdaptadorMembresia).setOnItemClickListener(new MembresiaAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            volleyMembresia.eliminarMembresia(MembresiaActivity.this, listaMembresias.get(position),credenciales.getString("ip", "192.168.100.216"));
                            actualizarLista();

                        }
                    });
                    ((MembresiaAdapter)mAdaptadorMembresia).setMode(Attributes.Mode.Single);
                    recyclerMembresia.setAdapter(mAdaptadorMembresia);

                    recyclerMembresia.addOnScrollListener(onScrollListener);

                }
            };

            volleyMembresia.obtenerMembresias(this,credenciales.getString("ip", "192.168.100.216"),listener);

        }


        private void iniciarWidgets() {
            // cardViewTitulo = findViewById(R.id.contenedor_titulo);
            searchMembresia = findViewById(R.id.barra_busqueda_membresia);
            searchMembresia.clearFocus();
            iconMembresia = findViewById(R.id.icon_membresias);
            iconAgregar = findViewById(R.id.icon_add_membresia);
            int whiteColor = ContextCompat.getColor(this, R.color.white);
            iconMembresia .addValueCallback(
                    new KeyPath("**"),
                    LottieProperty.COLOR_FILTER,
                    new LottieValueCallback<>(new SimpleColorFilter(whiteColor)));


        }

        private void verificarEstadoSesion(String cedula) {
            if(cedula == null){
                Intent intent = new Intent(MembresiaActivity.this, LoginActivity.class);
                startActivity(intent);;
                finish();
            }
        }

        private void actualizarLista() {
            listaMembresias.clear();
            VolleyMembresia volleyMembresia = new VolleyMembresia();
            if(mAdaptadorMembresia == null){
                return;
            }else{

                MembresiaICallback listener = new MembresiaICallback() {
                    @Override
                    public void onMembresiasReceived(ArrayList<Membresia> lista) {
                        listaMembresias = lista;
                        ((MembresiaAdapter)mAdaptadorMembresia ).setListaMembresias(listaMembresias);
                        mAdaptadorMembresia.notifyDataSetChanged();
                    }
                };

                volleyMembresia.obtenerMembresias(this,credenciales.getString("ip", "192.168.100.216"),listener);

            }


        }

        public void filtrar(String dato){
            ArrayList<Membresia> filtrado = new ArrayList<>();
            VolleyMembresia volleyMembresia = new VolleyMembresia();
            MembresiaICallback listener = new MembresiaICallback() {
                @Override
                public void onMembresiasReceived(ArrayList<Membresia> lista) {
                    listaMembresias = lista;
                    for(Membresia tu : listaMembresias){
                        if(tu.getDescripcion().toLowerCase().contains(dato.toLowerCase())){
                            filtrado.add(tu);
                        }
                    }

                    listaMembresias = filtrado;
                    ((MembresiaAdapter)mAdaptadorMembresia).setListaMembresias(listaMembresias);

                    mAdaptadorMembresia.notifyDataSetChanged();
                }
            };

            volleyMembresia.obtenerMembresias(this,credenciales.getString("ip", "192.168.100.216"),listener);


        }

        private void agregarEventos() {
            searchMembresia.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

                    Intent intent = new Intent(MembresiaActivity.this, FormularioMembresiaActivity.class);
                    intent.putExtra("metodo","agregar");
                    launcher.launch(intent);

                }
            });

        }

}