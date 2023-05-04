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
import una.ac.cr.supermercadoapp.data.CategoriaData;
import una.ac.cr.supermercadoapp.domain.AdministradorRed;
import una.ac.cr.supermercadoapp.domain.Categoria;
import una.ac.cr.supermercadoapp.network.VolleySingleton;
import una.ac.cr.supermercadoapp.network.VolleyCategoria;
import una.ac.cr.supermercadoapp.utils.MonitorRedUtils;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.adapters.CategoriaAdapter;
import una.ac.cr.supermercadoapp.view.interfaces.CategoriaICallback;

public class CategoriaActivity extends AppCompatActivity {
    private RecyclerView recyclerCategorias; //recycler
    private RecyclerView.Adapter mAdaptadorCategoria; //Objeto para adaptador
    private ArrayList<Categoria> listaCategorias; //Lista auxiliar
    private SearchView searchCategoria; //buscador
    private LottieAnimationView iconCategoria, iconAgregar; //iconos
    private SharedPreferences credenciales;
    public static final int REQUEST_CODE = 1;

    private CategoriaData categoriaData;
    public MonitorRedUtils monitorRedUtils;

    private final Observer<Boolean> observadoEstadoRed = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isConnected) {
            if(isConnected){
                // Toast.makeText(getApplicationContext(),"Sincronizando...",Toast.LENGTH_SHORT).show();
                // sincronizarDB();
                sincronizarBDServer();
                Toast.makeText(getApplicationContext(),"Sincronizando",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(getApplicationContext(),"Sin conexion",Toast.LENGTH_SHORT).show();
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
        setContentView(R.layout.activity_categoria);

        categoriaData = new CategoriaData(this);



        if(categoriaData.insertarCategoria(new Categoria("Test")) == -1){
            Toast.makeText(this,"Error al insertar",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Insertado con éxito",Toast.LENGTH_SHORT).show();
        }

        /*if(categoriaData.actualizarCategoria(new Categoria(1,"Modificado",1))!= 1){
            Toast.makeText(this,"Error al actualizar",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this,"Actualizado con éxito",Toast.LENGTH_SHORT).show();
        }*/

        /*if(categoriaData.eliminarCategoria(new Categoria(1,"Modificado",1))!= 1){
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
            Intent intent = new Intent(CategoriaActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }
    private void agregarEventos() {
        searchCategoria.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

                Intent intent = new Intent(CategoriaActivity.this, FormularioCategoriaActivity.class);
                intent.putExtra("metodo","agregar");
                launcher.launch(intent);

            }
        });

    }
    private void configurarRecycler() {
        recyclerCategorias = findViewById(R.id.recycler_view_categoria);
        recyclerCategorias .setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerCategorias.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .drawable(R.drawable.divider)
                .size(3)
                .margin(18,18)
                .build());

        //Aquí se tendría que llenar la lista auxiliar con lo que hay en la BD del server
        VolleyCategoria volleyCategoria = new VolleyCategoria();

        // Callback es una función que se pasa como argumento a otra función
        // y se invoca cuando se completa una tarea.
        CategoriaICallback listener = new CategoriaICallback() {


            @Override
            public void onCategoriaReceived(ArrayList<Categoria> lista) {
                listaCategorias = lista;
                mAdaptadorCategoria = new CategoriaAdapter(launcher, listaCategorias, getApplicationContext());
                ((CategoriaAdapter)mAdaptadorCategoria ).setOnItemClickListener(new CategoriaAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        volleyCategoria.eliminarCategoria(CategoriaActivity.this,listaCategorias.get(position),credenciales.getString("ip", "192.168.100.216"));
                        actualizarLista();

                    }
                });
                ((CategoriaAdapter)mAdaptadorCategoria ).setMode(Attributes.Mode.Single);
                recyclerCategorias.setAdapter(mAdaptadorCategoria );

                recyclerCategorias.addOnScrollListener(onScrollListener);
            }
        };

        volleyCategoria.obtenerTipos(this,credenciales.getString("ip", "192.168.100.216"),listener);

    }
    private void iniciarWidgets() {
        // cardViewTitulo = findViewById(R.id.contenedor_titulo);
        searchCategoria = findViewById(R.id.barra_busqueda_categoria);
          searchCategoria.clearFocus();
        iconCategoria = findViewById(R.id.icon_ver_categorias);
        iconAgregar = findViewById(R.id.icon_add_categoria);
        int whiteColor = ContextCompat.getColor(this, R.color.white);
        iconCategoria .addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(whiteColor)));


    }
    private void actualizarLista() {
        listaCategorias.clear();
        VolleyCategoria volleyCategoria = new VolleyCategoria();
        if(mAdaptadorCategoria == null){
            return;
        }else{

            CategoriaICallback listener = new CategoriaICallback() {
                @Override
                public void onCategoriaReceived(ArrayList<Categoria> lista) {
                    listaCategorias = lista;
                    ((CategoriaAdapter)mAdaptadorCategoria ).setListaCategorias(listaCategorias);
                    mAdaptadorCategoria.notifyDataSetChanged();
                }
            };

            volleyCategoria.obtenerTipos(this,credenciales.getString("ip", "192.168.100.216"),listener);

        }


    }
    public void filtrar(String dato){
        ArrayList<Categoria> filtrado = new ArrayList<>();
        VolleyCategoria volleyCategoria = new VolleyCategoria();
        CategoriaICallback listener = new CategoriaICallback() {
            @Override
            public void onCategoriaReceived(ArrayList<Categoria> lista) {
                listaCategorias = lista;
                for(Categoria tu : listaCategorias){
                    if(tu.getNombre().toLowerCase().contains(dato.toLowerCase())){
                        filtrado.add(tu);
                    }
                }

                listaCategorias = filtrado;
                ((CategoriaAdapter)mAdaptadorCategoria ).setListaCategorias(listaCategorias);

                mAdaptadorCategoria.notifyDataSetChanged();
            }
        };

        volleyCategoria.obtenerTipos(this,credenciales.getString("ip", "192.168.100.216"),listener);


    }



    private void configurarEstadoRed() {
        monitorRedUtils = new MonitorRedUtils(this);
        monitorRedUtils.verificarEstadoRed();
        monitorRedUtils.registrarEventosCallbackRed();

        AdministradorRed.getInstance().getEstadoConectividad().observe(this,observadoEstadoRed);
    }

    private void sincronizarBDServer(){
        Cursor cursor = categoriaData.obtenerCategorias();
        String IP = credenciales.getString("ip", "192.168.100.216");

        while(cursor.moveToNext()){
            int estado = cursor.getInt(2); //obtengo el estado
            if(estado == -1){
                //Construyo objeto
                Categoria categoria = new Categoria(cursor.getInt(0),cursor.getString(1),cursor.getInt(2));
                JSONObject categoriaJson = new JSONObject();
                try {
                    categoriaJson.put("metodo", "insertar");
                    categoriaJson.put("nombre",categoria.getNombre());

                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_CATEGORIA,  categoriaJson , new Response.Listener<JSONObject>() {
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

                boolean actualizado = categoriaData.actualizarEstado(0);
                if(actualizado){
                    //Actualizo recyclerview


                }else{
                    Toasty.error(getApplicationContext(), "Error al actualizar estado de datos.", Toast.LENGTH_SHORT, true).show();
                }

            }
        }

    }


}