package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.Observer;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.transition.Explode;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.jaredrummler.android.widget.AnimatedSvgView;
import com.michaldrabik.tapbarmenulib.TapBarMenu;


import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.data.TipoUsuarioData;
import una.ac.cr.supermercadoapp.domain.AdministradorRed;
import una.ac.cr.supermercadoapp.utils.MonitorRedUtils;


public class MainActivity extends AppCompatActivity {

    private CardView cardEmpleados, cardProductos, cardCategorias, cardProveedor, cardUsuarios, cardReportes, cardClientes;
    private ImageView iconCuenta, iconConfig, iconLogOut;
    private TapBarMenu tapMenu;

    private TipoUsuarioData mTipoUsuarioData;

    public MonitorRedUtils monitorRedUtils;

    private final Observer<Boolean> observadoEstadoRed = new Observer<Boolean>() {
        @Override
        public void onChanged(Boolean isConnected) {
            if(isConnected){
                // Toast.makeText(getApplicationContext(),"Sincronizando...",Toast.LENGTH_SHORT).show();
               // sincronizarDB();
                Toast.makeText(getApplicationContext(),"Sincronizando",Toast.LENGTH_SHORT).show();

            }else{
                Toast.makeText(getApplicationContext(),"Sin conexion",Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //mTipoUsuarioData = new TipoUsuarioData(this);
        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);
        verificarEstadoSesion(cedula);
        iniciarComponentes();
        configurarEstadoRed();
        agregarAnimaciones();
        agregarEventos();

    }

    private void configurarEstadoRed() {
        monitorRedUtils = new MonitorRedUtils(this);
        monitorRedUtils.verificarEstadoRed();
        monitorRedUtils.registrarEventosCallbackRed();

        AdministradorRed.getInstance().getEstadoConectividad().observe(this,observadoEstadoRed);
    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }

    private void agregarEventos() {
        tapMenu.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                tapMenu.toggle();
            }
        });

        cardEmpleados.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,EmpleadoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cardProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProductoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cardCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,CategoriaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cardProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ProveedorActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cardUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UsuariosActivity.class);
                startActivity(intent);
                finish();
                //Toasty.info(getApplicationContext(), "Usuarios", Toast.LENGTH_SHORT, true).show();
            }
        });

        cardReportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ReporteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cardClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ClienteActivity.class);
                startActivity(intent);
                finish();
            }
        });

        iconConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ConfiguracionActivity.class);
                startActivity(intent);
                finish();
            }
        });

        iconCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Cuenta del usuario", Toast.LENGTH_SHORT, true).show();
            }
        });

        iconLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
                String cedula  = credenciales.getString("cedula", "");
                SharedPreferences.Editor editor = credenciales.edit();
                editor.clear();
                editor.apply();

                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
                Toasty.info(getApplicationContext(), "Cerrar sesión", Toast.LENGTH_SHORT, true).show();
            }
        });
    }


    private void iniciarComponentes() {
        tapMenu = findViewById(R.id.tapBarMenu);
        cardEmpleados = findViewById(R.id.cardv_empleados);
        cardProductos = findViewById(R.id.cardv_productos);
        cardCategorias = findViewById(R.id.cardv_categorias);
        cardProveedor = findViewById(R.id.cardv_proveedores);
        cardUsuarios = findViewById(R.id.cardv_usuarios);
        cardReportes = findViewById(R.id.cardv_reportes);
        cardClientes = findViewById(R.id.cardv_clientes);
        iconLogOut = findViewById(R.id.icon_logout);
        iconCuenta = findViewById(R.id.icon_cuenta);
        iconConfig = findViewById(R.id.icon_configuracion);
    }

    private void agregarAnimaciones() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                YoYo.with(Techniques.RubberBand)
                        .duration(700)
                        .repeat(0)
                        .playOn(cardEmpleados);
                YoYo.with(Techniques.RubberBand)
                        .duration(700)
                        .repeat(0)
                        .playOn(cardProductos);
                YoYo.with(Techniques.RubberBand)
                        .duration(700)
                        .repeat(0)
                        .playOn(cardCategorias);
                YoYo.with(Techniques.RubberBand)
                        .duration(700)
                        .repeat(0)
                        .playOn(cardCategorias);
                YoYo.with(Techniques.RubberBand)
                        .duration(700)
                        .repeat(0)
                        .playOn(cardProveedor);
                YoYo.with(Techniques.RubberBand)
                        .duration(700)
                        .repeat(0)
                        .playOn(cardUsuarios);
                YoYo.with(Techniques.RubberBand)
                        .duration(700)
                        .repeat(0)
                        .playOn(cardReportes);
                YoYo.with(Techniques.RubberBand)
                        .duration(700)
                        .repeat(0)
                        .playOn(cardClientes);

            }
        },3000);

    }


}