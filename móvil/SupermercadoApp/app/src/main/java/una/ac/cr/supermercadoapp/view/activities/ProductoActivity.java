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
import una.ac.cr.supermercadoapp.data.ProductoData;
import una.ac.cr.supermercadoapp.domain.AdministradorRed;
import una.ac.cr.supermercadoapp.domain.Producto;
import una.ac.cr.supermercadoapp.network.VolleyProducto;
import una.ac.cr.supermercadoapp.utils.MonitorRedUtils;
import una.ac.cr.supermercadoapp.view.adapters.CategoriaAdapter;
import una.ac.cr.supermercadoapp.view.adapters.ProductoAdapter;
import una.ac.cr.supermercadoapp.view.interfaces.ProductoICallback;

public class ProductoActivity extends AppCompatActivity {


    private RecyclerView recyclerProductos;

    //Adaptador
    private RecyclerView.Adapter  productoAdapter;
    private ArrayList<Producto> listaProductos;
    private SearchView searchProducto;
    private LottieAnimationView iconProducto;

    private LottieAnimationView iconAgregar;
    private LottieAnimationView iconMenu;
    private SharedPreferences credenciales;
    private PowerMenu powerMenu;

    public static final int REQUEST_CODE = 1;

    private ProductoData productoData;
    public MonitorRedUtils monitorRedUtils;
    private OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener = new OnMenuItemClickListener<PowerMenuItem>() {
        @Override
        public void onItemClick(int position, PowerMenuItem item) {

            if(item.title.equals("Menú principal")){
                powerMenu.dismiss();
                String cedula = credenciales.getString("cedula", null);
                String tipo = credenciales.getString("tipo",null);
                if (cedula != null ) {

                    if(tipo.equals("Administrador")){
                        Intent intent = new Intent(ProductoActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else if(tipo.equals("Empleado")){
                        Intent intent  = new Intent(ProductoActivity.this, MenuEmpleadoActivity.class);
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
        setContentView(R.layout.activity_producto);
        productoData = new ProductoData(this);
        listaProductos = new ArrayList<>();


        credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        if(cedula == null){
            Intent intent = new Intent(ProductoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }

        verificarEstadoSesion(cedula);
        iniciarWidgets();
        configurarRecycler();
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

        iconProducto = findViewById(R.id.icon_ver_producto);
        int primaryColor = ContextCompat.getColor(this, R.color.primary);
        iconProducto.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(primaryColor)));
        iconProducto.setOnClickListener(new View.OnClickListener() {
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
        recyclerProductos = findViewById(R.id.recycler_view_producto);
        recyclerProductos.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerProductos.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .drawable(R.drawable.divider)
                .size(3)
                .margin(18,18)
                .build());

        //Aquí se tendría que llenar la lista auxiliar con lo que hay en la BD del server
        VolleyProducto volleyProducto = new VolleyProducto();

        ProductoICallback listener = new ProductoICallback() {
            @Override
            public void onProductoReceived(ArrayList<Producto> lista) {
                listaProductos = lista;
                productoAdapter = new ProductoAdapter(launcher, listaProductos, getApplicationContext());
                ((ProductoAdapter)productoAdapter).setOnItemClickListener(new ProductoAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {
                        VolleyProducto volleyProducto = new VolleyProducto();
                        ProductoICallback listener = new ProductoICallback() {
                            @Override
                            public void onProductoReceived(ArrayList<Producto> lista) {
                                listaProductos = lista;
                                ((ProductoAdapter)productoAdapter ).setListaProductos(listaProductos);
                                productoAdapter.notifyDataSetChanged();
                            }
                        };
                        volleyProducto.eliminarProducto(ProductoActivity.this,listaProductos.get(position),credenciales.getString("ip", "192.168.100.216"));
                        volleyProducto.obtenerProductos(ProductoActivity.this,credenciales.getString("ip", "192.168.100.216"),listener);
                    }
                });
                ((ProductoAdapter)productoAdapter).setMode(Attributes.Mode.Single);
                recyclerProductos.setAdapter(productoAdapter);

                recyclerProductos.addOnScrollListener(onScrollListener);

            }
        };

        volleyProducto.obtenerProductos(this,credenciales.getString("ip", "192.168.100.216"),listener);

    }



    private void actualizarLista() {
        listaProductos.clear();

        VolleyProducto volleyProducto = new VolleyProducto();

        if(productoAdapter==null){
            return;
        }else{
            ProductoICallback listener = new ProductoICallback() {

                @Override
                public void onProductoReceived(ArrayList<Producto> lista) {
                    listaProductos = lista;
                    ((ProductoAdapter)productoAdapter ).setListaProductos(listaProductos);

                    productoAdapter.notifyDataSetChanged();
                }
            };

            volleyProducto.obtenerProductos(this, credenciales.getString("ip", "192.168.100.216"), listener);

        }

}

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(ProductoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }

    @Override
    public void onBackPressed() {

    }

    public void filtrar(String dato){
        ArrayList<Producto> filtrado = new ArrayList<>();
        VolleyProducto volleyProducto = new VolleyProducto();
        ProductoICallback listener = new ProductoICallback() {
            @Override
            public void onProductoReceived(ArrayList<Producto> lista) {
                listaProductos = lista;
                for(Producto tu : listaProductos){
                    if(tu.getNombre().toLowerCase().contains(dato.toLowerCase())){
                        filtrado.add(tu);
                    }
                }

                listaProductos = filtrado;
                ((ProductoAdapter)productoAdapter).setListaProductos(listaProductos);

                productoAdapter.notifyDataSetChanged();
            }
        };

        volleyProducto.obtenerProductos(this,credenciales.getString("ip", "192.168.100.216"),listener);


    }
    private void agregarEventos() {
        searchProducto.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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

                Intent intent = new Intent(ProductoActivity.this, FormularioProductoActivity.class);
                intent.putExtra("metodo","agregar");
                launcher.launch(intent);

            }
        });

    }

    private void iniciarWidgets() {
        // cardViewTitulo = findViewById(R.id.contenedor_titulo);
        searchProducto = findViewById(R.id.barra_busqueda_producto);
        searchProducto.clearFocus();
        iconProducto = findViewById(R.id.icon_ver_producto);
        iconAgregar = findViewById(R.id.icon_add_producto);
        int whiteColor = ContextCompat.getColor(this, R.color.white);
        iconProducto .addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(whiteColor)));


    }



}