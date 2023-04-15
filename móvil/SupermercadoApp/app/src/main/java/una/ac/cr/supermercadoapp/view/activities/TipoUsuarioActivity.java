package una.ac.cr.supermercadoapp.view.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
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
import com.r0adkll.slidr.Slidr;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import java.util.Arrays;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.network.VolleyTipoUsuario;
import una.ac.cr.supermercadoapp.view.adapters.TipoUsuarioAdapter;
import una.ac.cr.supermercadoapp.view.interfaces.TipoUsuarioICallback;

public class TipoUsuarioActivity extends AppCompatActivity {
    CardView cardViewTitulo;
    private RecyclerView recyclerTipoUsuarios; //recycler
    private RecyclerView.Adapter mAdaptadorTipoUsuario; //Objeto para adaptador
    private ArrayList<TipoUsuario> listaTipoUsuarios; //Lista auxiliar
    private SearchView searchTipoUsuario; //buscador
    private LottieAnimationView iconUsuarios, iconAgregar; //iconos
    private SharedPreferences credenciales;
    public static final int REQUEST_CODE = 1;

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
        setContentView(R.layout.activity_tipo_usuario);

        credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        verificarEstadoSesion(cedula);
        iniciarWidgets();
        configurarRecycler();
        agregarEventos();

    }
    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(TipoUsuarioActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }
    private void agregarEventos() {
        searchTipoUsuario.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

                Intent intent = new Intent(TipoUsuarioActivity.this, FormularioTipoUsuarioActivity.class);
                intent.putExtra("metodo","agregar");
                launcher.launch(intent);

            }
        });

    }
    private void configurarRecycler() {
        recyclerTipoUsuarios = findViewById(R.id.recycler_view_tipos_usuario);
        recyclerTipoUsuarios .setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerTipoUsuarios.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .drawable(R.drawable.divider)
                .size(3)
                .margin(18,18)
                .build());

        //Aquí se tendría que llenar la lista auxiliar con lo que hay en la BD del server
        VolleyTipoUsuario volleyTipoUsuario = new VolleyTipoUsuario();

        // Callback es una función que se pasa como argumento a otra función
        // y se invoca cuando se completa una tarea.
        TipoUsuarioICallback listener = new TipoUsuarioICallback() {
            @Override
            public void onTiposUsuarioReceived(ArrayList<TipoUsuario> lista) {
                listaTipoUsuarios = lista;
                mAdaptadorTipoUsuario = new TipoUsuarioAdapter(launcher, listaTipoUsuarios, getApplicationContext());
                ((TipoUsuarioAdapter)mAdaptadorTipoUsuario ).setOnItemClickListener(new TipoUsuarioAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        volleyTipoUsuario.eliminarUsuario(TipoUsuarioActivity.this,listaTipoUsuarios.get(position),credenciales.getString("ip", "192.168.100.216"));
                        actualizarLista();

                    }
                });
                ((TipoUsuarioAdapter)mAdaptadorTipoUsuario ).setMode(Attributes.Mode.Single);
                recyclerTipoUsuarios.setAdapter(mAdaptadorTipoUsuario );

                recyclerTipoUsuarios.addOnScrollListener(onScrollListener);
            }
        };

        volleyTipoUsuario.obtenerTipos(this,credenciales.getString("ip", "192.168.100.216"),listener);

    }
    private void iniciarWidgets() {
       // cardViewTitulo = findViewById(R.id.contenedor_titulo);
        searchTipoUsuario = findViewById(R.id.barra_busqueda_tu);
        searchTipoUsuario.clearFocus();
        iconUsuarios = findViewById(R.id.icon_ti_usuarios);
        iconAgregar = findViewById(R.id.icon_agregar_tipo_usuario);
        int whiteColor = ContextCompat.getColor(this, R.color.white);
        iconUsuarios .addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(whiteColor)));


    }
    private void actualizarLista() {
        listaTipoUsuarios.clear();
        VolleyTipoUsuario volleyTipoUsuario = new VolleyTipoUsuario();
        if(mAdaptadorTipoUsuario == null){
            return;
        }else{

            TipoUsuarioICallback listener = new TipoUsuarioICallback() {
                @Override
                public void onTiposUsuarioReceived(ArrayList<TipoUsuario> lista) {
                    listaTipoUsuarios = lista;
                    ((TipoUsuarioAdapter)mAdaptadorTipoUsuario ).setListaTipoUsuarios(listaTipoUsuarios);
                    mAdaptadorTipoUsuario.notifyDataSetChanged();
                }
            };

            volleyTipoUsuario.obtenerTipos(this,credenciales.getString("ip", "192.168.100.216"),listener);

        }


    }
    public void filtrar(String dato){
        ArrayList<TipoUsuario> filtrado = new ArrayList<>();
        VolleyTipoUsuario volleyTipoUsuario = new VolleyTipoUsuario();
        TipoUsuarioICallback listener = new TipoUsuarioICallback() {
            @Override
            public void onTiposUsuarioReceived(ArrayList<TipoUsuario> lista) {
                listaTipoUsuarios = lista;
                for(TipoUsuario tu : listaTipoUsuarios){
                    if(tu.getDescripcion().toLowerCase().contains(dato.toLowerCase())){
                        filtrado.add(tu);
                    }
                }

                listaTipoUsuarios = filtrado;
                ((TipoUsuarioAdapter)mAdaptadorTipoUsuario ).setListaTipoUsuarios(listaTipoUsuarios);

                mAdaptadorTipoUsuario.notifyDataSetChanged();
            }
        };

        volleyTipoUsuario.obtenerTipos(this,credenciales.getString("ip", "192.168.100.216"),listener);


    }


}