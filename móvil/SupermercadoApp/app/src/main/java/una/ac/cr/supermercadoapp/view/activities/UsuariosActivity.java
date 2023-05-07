package una.ac.cr.supermercadoapp.view.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.domain.Usuario;
import una.ac.cr.supermercadoapp.network.VolleyTipoUsuario;
import una.ac.cr.supermercadoapp.network.VolleyUsuario;
import una.ac.cr.supermercadoapp.view.adapters.TipoUsuarioAdapter;
import una.ac.cr.supermercadoapp.view.adapters.UsuarioAdapter;
import una.ac.cr.supermercadoapp.view.interfaces.TipoUsuarioICallback;
import una.ac.cr.supermercadoapp.view.interfaces.UsuarioICallback;

public class UsuariosActivity extends AppCompatActivity {

    private RecyclerView recyclerUsuarios;

    //Adaptador
    private RecyclerView.Adapter  usuarioAdapter;
    private ArrayList<Usuario> listaUsuarios;
    private SearchView searchUsuario;

    private LottieAnimationView tipoUsuarios,iconUsuarios,iconAgregar; ;
    private PowerMenu powerMenu;

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

    private OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener = new OnMenuItemClickListener<PowerMenuItem>() {
        @Override
        public void onItemClick(int position, PowerMenuItem item) {

            if(item.title.equals("Tipos de usuario")){
             //   powerMenu.setSelectedPosition(position); // change selected item
                powerMenu.dismiss();
                Intent intent = new Intent(UsuariosActivity.this, TipoUsuarioActivity.class);
                startActivity(intent);

            }else if(item.title.equals("Menú principal")){
                powerMenu.dismiss();
                Intent intent = new Intent(UsuariosActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
            //Toast.makeText(getBaseContext(), item.title, Toast.LENGTH_SHORT).show();
        }
    };

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
    private SharedPreferences credenciales;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuarios);
        listaUsuarios = new ArrayList<>();
        credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        if(cedula == null){
            Intent intent = new Intent(UsuariosActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }

        verificarEstadoSesion(cedula);

        powerMenu = new PowerMenu.Builder(this)

                .addItem(new PowerMenuItem("Tipos de usuario", false)) // add an item.
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

        tipoUsuarios = findViewById(R.id.icon_ver_tipos_usuario);
        int primaryColor = ContextCompat.getColor(this, R.color.primary);
        tipoUsuarios.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(primaryColor)));



        iconUsuarios = findViewById(R.id.icon_usuarios);
        int whiteColor = ContextCompat.getColor(this, R.color.white);
        iconUsuarios .addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(whiteColor)));

        iconAgregar = findViewById(R.id.icon_add_usuario);

        iconAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UsuariosActivity.this, FormularioUsuarioActivity.class);
                intent.putExtra("metodo","agregar");
                launcher.launch(intent);
            }
        });


        tipoUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (powerMenu.isShowing()) {
                    powerMenu.dismiss();
                    return;
                }
                powerMenu.showAsDropDown(view);
            }
        });

        recyclerUsuarios = findViewById(R.id.recycler_view_usuario);
        recyclerUsuarios .setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        recyclerUsuarios.addItemDecoration(new HorizontalDividerItemDecoration.Builder(this)
                .drawable(R.drawable.divider)
                .size(3)
                .margin(18,18)
                .build());

        //Aquí se tendría que llenar la lista auxiliar con lo que hay en la BD del server
        VolleyUsuario volleyUsuario = new VolleyUsuario();

        UsuarioICallback listener = new UsuarioICallback() {
            @Override
            public void onUsuarioReceived(ArrayList<Usuario> lista) {
                listaUsuarios = lista;
                usuarioAdapter = new UsuarioAdapter(launcher,listaUsuarios,getApplicationContext());
                ((UsuarioAdapter)usuarioAdapter ).setOnItemClickListener(new TipoUsuarioAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(int position) {

                        UsuarioICallback listener = new UsuarioICallback() {
                            @Override
                            public void onUsuarioReceived(ArrayList<Usuario> lista) {
                                listaUsuarios.clear();
                                listaUsuarios.addAll(lista);
                                usuarioAdapter.notifyDataSetChanged();
                            }
                        };

                        volleyUsuario.eliminarUsuario(UsuariosActivity.this,listaUsuarios.get(position),credenciales.getString("ip", "192.168.100.216"));
                        volleyUsuario.obtenerUsuarios(UsuariosActivity.this, credenciales.getString("ip", "192.168.100.216"), listener);
                      //  actualizarLista();
                    }
                });

                ((UsuarioAdapter)usuarioAdapter ).setMode(Attributes.Mode.Single);
                recyclerUsuarios.setAdapter(usuarioAdapter);
                recyclerUsuarios.addOnScrollListener(onScrollListener);



            }
        };

        volleyUsuario.obtenerUsuarios(this,credenciales.getString("ip", "192.168.100.216"),listener);

    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(UsuariosActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }

    private void actualizarLista() {
        VolleyUsuario volleyUsuario = new VolleyUsuario();

        UsuarioICallback listener = new UsuarioICallback() {
            @Override
            public void onUsuarioReceived(ArrayList<Usuario> lista) {
                listaUsuarios.clear();
                listaUsuarios.addAll(lista);
                usuarioAdapter.notifyDataSetChanged();
            }
        };

        volleyUsuario.obtenerUsuarios(this, credenciales.getString("ip", "192.168.100.216"), listener);
    }


    @Override
    public void onBackPressed() {

    }

}