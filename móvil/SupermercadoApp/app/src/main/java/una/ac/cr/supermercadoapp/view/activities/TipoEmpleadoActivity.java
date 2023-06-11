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
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.daimajia.swipe.util.Attributes;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.data.TipoEmpleadoData;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.network.VolleyTipoEmpleado;
import una.ac.cr.supermercadoapp.utils.MonitorRedUtils;
import una.ac.cr.supermercadoapp.view.adapters.TipoEmpleadoAdapter;
import una.ac.cr.supermercadoapp.view.interfaces.TipoEmpleadoICallback;

public class TipoEmpleadoActivity extends AppCompatActivity {

    private SharedPreferences credenciales;
    private LottieAnimationView iconAgregar, iconTipoEmpleado;

    private RecyclerView recyclerTipoEmpleado;
    private RecyclerView.Adapter mAdaptadorTipoEmpleado;
    private ArrayList<TipoEmpleado> listaTipoEmpleado;
    private SearchView searchTipoEmpleado;
    public static final int REQUEST_CODE = 1;

    private TipoEmpleadoData tipoEmpleadoData;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_empleado);
        credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        verificarEstadoSesion(cedula);
        configurarRecycler();
        iniciarWidgets();
        agregarEventos();

        tipoEmpleadoData = new TipoEmpleadoData(this);

        iconAgregar = findViewById(R.id.icon_agregar_tipo_empleado);
        iconAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TipoEmpleadoActivity.this, FormularioTipoEmpleadoActivity.class);
                intent.putExtra("metodo","agregar");
                startActivity(intent);

            }
        });
    }



    private void configurarRecycler() {
        recyclerTipoEmpleado = findViewById(R.id.recycler_view_tipos_empleado);
        recyclerTipoEmpleado.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerTipoEmpleado.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .drawable(R.drawable.divider)
                .size(3)
                .margin(18,18)
                .build());

        //Aquí se tendría que llenar la lista auxiliar con lo que hay en la BD del server
        VolleyTipoEmpleado volleyTipoEmpleado = new VolleyTipoEmpleado();

        TipoEmpleadoICallback listener = new TipoEmpleadoICallback() {
            @Override
            public void onTiposEmpeladoReceived(ArrayList<TipoEmpleado> lista) {
                listaTipoEmpleado = lista;
                mAdaptadorTipoEmpleado = new TipoEmpleadoAdapter(launcher, listaTipoEmpleado, getApplicationContext());
                ((TipoEmpleadoAdapter)mAdaptadorTipoEmpleado).setOnItemClickListener(new TipoEmpleadoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        volleyTipoEmpleado.eliminarTipoEmpleado(TipoEmpleadoActivity.this, listaTipoEmpleado.get(position),credenciales.getString("ip", "192.168.100.216"));
                        actualizarLista();

                    }
                });
                ((TipoEmpleadoAdapter)mAdaptadorTipoEmpleado).setMode(Attributes.Mode.Single);
                recyclerTipoEmpleado.setAdapter(mAdaptadorTipoEmpleado);

                recyclerTipoEmpleado.addOnScrollListener(onScrollListener);

            }
        };

        volleyTipoEmpleado.obtenerTipos(this,credenciales.getString("ip", "192.168.100.216"),listener);

    }


    private void iniciarWidgets() {
        // cardViewTitulo = findViewById(R.id.contenedor_titulo);
        searchTipoEmpleado = findViewById(R.id.barra_busqueda_te);
        searchTipoEmpleado.clearFocus();
        iconTipoEmpleado = findViewById(R.id.icon_ti_empleados);
        iconAgregar = findViewById(R.id.icon_agregar_tipo_empleado);
        int whiteColor = ContextCompat.getColor(this, R.color.white);
        iconTipoEmpleado .addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(whiteColor)));


    }



    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(TipoEmpleadoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }



    private void actualizarLista() {
        listaTipoEmpleado.clear();
        VolleyTipoEmpleado volleyTipoEmpleado = new VolleyTipoEmpleado();
        if(mAdaptadorTipoEmpleado == null){
            return;
        }else{

            TipoEmpleadoICallback listener = new TipoEmpleadoICallback() {
                @Override
                public void onTiposEmpeladoReceived(ArrayList<TipoEmpleado> lista) {
                    listaTipoEmpleado = lista;
                    ((TipoEmpleadoAdapter)mAdaptadorTipoEmpleado ).setListaTipoEmpleados(listaTipoEmpleado);
                    mAdaptadorTipoEmpleado.notifyDataSetChanged();
                }
            };

            volleyTipoEmpleado.obtenerTipos(this,credenciales.getString("ip", "192.168.100.216"),listener);

        }


    }

    public void filtrar(String dato){
        ArrayList<TipoEmpleado> filtrado = new ArrayList<>();
        VolleyTipoEmpleado volleyTipoEmpleado = new VolleyTipoEmpleado();
        TipoEmpleadoICallback listener = new TipoEmpleadoICallback() {
            @Override
            public void onTiposEmpeladoReceived(ArrayList<TipoEmpleado> lista) {
                listaTipoEmpleado = lista;
                for(TipoEmpleado tu : listaTipoEmpleado){
                    if(tu.getTipoEmpleadoDescripcion().toLowerCase().contains(dato.toLowerCase())){
                        filtrado.add(tu);
                    }
                }

                listaTipoEmpleado = filtrado;
                ((TipoEmpleadoAdapter)mAdaptadorTipoEmpleado).setListaTipoEmpleados(listaTipoEmpleado);

                mAdaptadorTipoEmpleado.notifyDataSetChanged();
            }
        };

        volleyTipoEmpleado.obtenerTipos(this,credenciales.getString("ip", "192.168.100.216"),listener);


    }

    private void agregarEventos() {
        searchTipoEmpleado.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

                Intent intent = new Intent(TipoEmpleadoActivity.this, FormularioTipoUsuarioActivity.class);
                intent.putExtra("metodo","agregar");
                launcher.launch(intent);

            }
        });

    }



}