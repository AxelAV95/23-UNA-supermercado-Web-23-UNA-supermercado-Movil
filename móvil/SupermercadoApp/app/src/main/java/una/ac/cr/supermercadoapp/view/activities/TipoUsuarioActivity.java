package una.ac.cr.supermercadoapp.view.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
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
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.daimajia.swipe.util.Attributes;
import com.r0adkll.slidr.Slidr;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.data.TipoUsuarioData;
import una.ac.cr.supermercadoapp.domain.AdministradorRed;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.network.VolleySingleton;
import una.ac.cr.supermercadoapp.network.VolleyTipoUsuario;
import una.ac.cr.supermercadoapp.utils.MonitorRedUtils;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.adapters.TipoUsuarioAdapter;
import una.ac.cr.supermercadoapp.view.interfaces.TipoUsuarioICallback;

public class TipoUsuarioActivity extends AppCompatActivity {
    private RecyclerView recyclerTipoUsuarios; //recycler
    private RecyclerView.Adapter mAdaptadorTipoUsuario; //Objeto para adaptador
    private ArrayList<TipoUsuario> listaTipoUsuarios; //Lista auxiliar
    private SearchView searchTipoUsuario; //buscador
    private LottieAnimationView iconUsuarios, iconAgregar; //iconos
    private SharedPreferences credenciales;
    public static final int REQUEST_CODE = 1;

    private TipoUsuarioData tipoUsuarioData;
    public MonitorRedUtils monitorRedUtils;

    private final Observer<Boolean> observadoEstadoRed = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isConnected) {
            if(isConnected){
                // Toast.makeText(getApplicationContext(),"Sincronizando...",Toast.LENGTH_SHORT).show();
                // sincronizarDB();
                sincronizarBDServer();
                Toasty.info(getApplicationContext(), "Sincronizando...", Toast.LENGTH_SHORT, true).show();

            }else{
                Toasty.error(getApplicationContext(), "Sin conexión", Toast.LENGTH_SHORT, true).show();
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
        setContentView(R.layout.activity_tipo_usuario);

        tipoUsuarioData = new TipoUsuarioData(this);

        /*

        if(tipoUsuarioData.insertarTipoUsuario(new TipoUsuario("Test")) == -1){
            Toast.makeText(this,"Error al insertar",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Insertado con éxito",Toast.LENGTH_SHORT).show();
        }*/

        /*if(tipoUsuarioData.actualizarTipoUsuario(new TipoUsuario(1,"Modificado",1))!= 1){
            Toast.makeText(this,"Error al actualizar",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Actualizado con éxito",Toast.LENGTH_SHORT).show();
        }*/

        /*if(tipoUsuarioData.eliminarTipoUsuario(new TipoUsuario(1,"Modificado",1))!= 1){
            Toast.makeText(this,"Error al borrar",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Borrado con éxito",Toast.LENGTH_SHORT).show();
        }*/



        credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        verificarEstadoSesion(cedula);
        iniciarWidgets();
        configurarEstadoRed();
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
                        VolleyTipoUsuario volleyTipoUsuario = new VolleyTipoUsuario();
                        TipoUsuarioICallback listener = new TipoUsuarioICallback() {
                            @Override
                            public void onTiposUsuarioReceived(ArrayList<TipoUsuario> lista) {
                                listaTipoUsuarios = lista;
                                ((TipoUsuarioAdapter)mAdaptadorTipoUsuario ).setListaTipoUsuarios(listaTipoUsuarios);
                                mAdaptadorTipoUsuario.notifyDataSetChanged();
                            }
                        };

                        volleyTipoUsuario.eliminarUsuario(TipoUsuarioActivity.this,listaTipoUsuarios.get(position),credenciales.getString("ip", "192.168.100.216"));
                        volleyTipoUsuario.obtenerTipos(TipoUsuarioActivity.this,credenciales.getString("ip", "192.168.100.216"),listener);

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



    private void configurarEstadoRed() {
        monitorRedUtils = new MonitorRedUtils(this);
        monitorRedUtils.verificarEstadoRed();
        monitorRedUtils.registrarEventosCallbackRed();

        AdministradorRed.getInstance().getEstadoConectividad().observe(this,observadoEstadoRed);
    }

    private void sincronizarBDServer(){
        Cursor cursor = tipoUsuarioData.obtenerTipoUsuarios();
        String IP = credenciales.getString("ip", "192.168.100.216");

        while(cursor.moveToNext()){
            int estado = cursor.getInt(2); //obtengo el estado
            if(estado == -1){
                //Construyo objeto
                TipoUsuario tipoUsuario = new TipoUsuario(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
                JSONObject tipoUsuarioJson = new JSONObject();
                try {
                    tipoUsuarioJson.put("metodo", "insertar");
                    tipoUsuarioJson.put("descripcion",tipoUsuario.getDescripcion());

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_TIPO_USUARIO,  tipoUsuarioJson , new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        if(response.optString("statusCode").toString().equals("200")){
                            Toasty.success(getApplicationContext(), "Se han sincronizado todos los datos", Toast.LENGTH_SHORT, true).show();
                            actualizarLista();

                        }else{
                            Toasty.error(getApplicationContext(), "Error al insertar", Toast.LENGTH_SHORT, true).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("anyText",error.toString());
                        Toasty.error(getApplicationContext(), "Error al sincronizar", Toast.LENGTH_SHORT, true).show();
                    }
                });

                VolleySingleton.getVolleySingleton(this).addToRequestQueue(jsonObjectRequest);

                boolean actualizado = tipoUsuarioData.actualizarEstado(0);
                if(actualizado){
                    //Actualizo recyclerview


                }else{
                    Toasty.error(getApplicationContext(), "Error al actualizar estado de datos.", Toast.LENGTH_SHORT, true).show();
                }

            }
        }

    }


}