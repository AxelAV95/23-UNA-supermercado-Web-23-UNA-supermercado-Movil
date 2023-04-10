package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.SearchView;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.daimajia.swipe.util.Attributes;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;
import java.util.ArrayList;
import java.util.Arrays;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.view.adapters.TipoUsuarioAdapter;

public class TipoUsuarioActivity extends AppCompatActivity {
    CardView cardViewTitulo;
    private RecyclerView recyclerTipoUsuarios; //recycler
    private RecyclerView.Adapter mAdaptadorTipoUsuario; //Objeto para adaptador
    private ArrayList<TipoUsuario> listaTipoUsuarios; //Lista auxiliar
    private SearchView searchTipoUsuario; //buscador
    private LottieAnimationView iconUsuarios, iconAgregar; //iconos

    private RecyclerView.OnScrollListener onScrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);
            Log.e("ListView", "onScrollStateChanged");
        }

        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            // Could hide open views here if you wanted. //
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_usuario);

        iniciarWidgets();
        configurarRecycler();
        agregarEventos();

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
                startActivity(intent);
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

        ArrayList<TipoUsuario> data = new ArrayList<>();
        data.add(new TipoUsuario(1,"Administrador"));
        data.add(new TipoUsuario(2,"Empleado"));

        listaTipoUsuarios = new ArrayList<TipoUsuario>(data);
        mAdaptadorTipoUsuario = new TipoUsuarioAdapter( listaTipoUsuarios, getApplicationContext());

        ((TipoUsuarioAdapter)mAdaptadorTipoUsuario ).setMode(Attributes.Mode.Single);
        recyclerTipoUsuarios.setAdapter(mAdaptadorTipoUsuario );

        recyclerTipoUsuarios.addOnScrollListener(onScrollListener);
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
        ArrayList<TipoUsuario> data = new ArrayList<>();
        data.add(new TipoUsuario(1,"Administrador"));
        data.add(new TipoUsuario(2,"Empleado"));

        if(mAdaptadorTipoUsuario == null){
            return;
        }else{
            listaTipoUsuarios = new ArrayList<TipoUsuario>(data);

        }

        ((TipoUsuarioAdapter)mAdaptadorTipoUsuario ).setListaTipoUsuarios(listaTipoUsuarios);
        mAdaptadorTipoUsuario.notifyDataSetChanged();
    }

    public void filtrar(String dato){
        ArrayList<TipoUsuario> filtrado = new ArrayList<>();

        for(TipoUsuario tu : listaTipoUsuarios){
            if(tu.getDescripcion().toLowerCase().contains(dato.toLowerCase())){
                filtrado.add(tu);
            }
        }

        listaTipoUsuarios = filtrado;
        ((TipoUsuarioAdapter)mAdaptadorTipoUsuario ).setListaTipoUsuarios(listaTipoUsuarios);

        mAdaptadorTipoUsuario.notifyDataSetChanged();
    }


}