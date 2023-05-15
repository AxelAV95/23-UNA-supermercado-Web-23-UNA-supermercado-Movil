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
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.data.DescuentoData;
import una.ac.cr.supermercadoapp.data.TipoEmpleadoData;
import una.ac.cr.supermercadoapp.domain.AdministradorRed;
import una.ac.cr.supermercadoapp.domain.Descuento;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.network.VolleyDescuento;
import una.ac.cr.supermercadoapp.network.VolleySingleton;
import una.ac.cr.supermercadoapp.network.VolleyTipoEmpleado;
import una.ac.cr.supermercadoapp.network.VolleyTipoUsuario;
import una.ac.cr.supermercadoapp.utils.MonitorRedUtils;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.adapters.DescuentoAdapter;
import una.ac.cr.supermercadoapp.view.adapters.TipoEmpleadoAdapter;
import una.ac.cr.supermercadoapp.view.adapters.TipoUsuarioAdapter;
import una.ac.cr.supermercadoapp.view.interfaces.DescuentoICallback;
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
        setContentView(R.layout.activity_descuento);
        credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        verificarEstadoSesion(cedula);
        configurarRecycler();
        iniciarWidgets();
        agregarEventos();

        descuentoData = new DescuentoData(this);

        iconAgregar = findViewById(R.id.icon_agregar_descuento);
        iconAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(DescuentoActivity.this, FormularioDescuentoActivity.class);
                intent.putExtra("metodo","agregar");
                startActivity(intent);

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

        //Aquí se tendría que llenar la lista auxiliar con lo que hay en la BD del server
        VolleyDescuento volleyDescuento = new VolleyDescuento();

        DescuentoICallback listener = new DescuentoICallback() {
            @Override
            public void onDescuentoReceived(ArrayList<Descuento> lista) {
                listaDescuentos = lista;
                mAdaptadorDescuento = new DescuentoAdapter(launcher, listaDescuentos, getApplicationContext());
                ((DescuentoAdapter)mAdaptadorDescuento).setOnItemClickListener(new DescuentoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        volleyDescuento.eliminarDescuento(DescuentoActivity.this, listaDescuentos.get(position),credenciales.getString("ip", "192.168.100.216"));
                        actualizarLista();

                    }
                });
                ((DescuentoAdapter)mAdaptadorDescuento).setMode(Attributes.Mode.Single);
                recyclerDescuento.setAdapter(mAdaptadorDescuento);

                recyclerDescuento.addOnScrollListener(onScrollListener);

            }
        };

        volleyDescuento.obtenerDescuentos(this,credenciales.getString("ip", "192.168.100.216"),listener);

    }


    private void iniciarWidgets() {
        // cardViewTitulo = findViewById(R.id.contenedor_titulo);
        searchDescuento = findViewById(R.id.barra_busqueda_te);
        searchDescuento.clearFocus();
        iconDescuento = findViewById(R.id.icon_ti_descuentos);
        iconAgregar = findViewById(R.id.icon_agregar_descuento);


    }



    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(DescuentoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }



    private void actualizarLista() {
        listaDescuentos.clear();
        VolleyDescuento volleyDescuento = new VolleyDescuento();
        if(mAdaptadorDescuento == null){
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

            volleyDescuento.obtenerDescuentos(this,credenciales.getString("ip", "192.168.100.216"),listener);

        }


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
                    filtrar(((Float.parseFloat(newText)) ));

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



}
