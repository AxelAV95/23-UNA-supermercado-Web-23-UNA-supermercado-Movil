package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
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

public class MainActivity extends AppCompatActivity {

    private CardView cardEmpleados, cardProductos, cardCategorias, cardProveedor, cardUsuarios, cardReportes, cardClientes;
    private ImageView iconCuenta, iconConfig, iconLogOut;
    private TapBarMenu tapMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarComponentes();
        agregarAnimaciones();
        agregarEventos();


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
                Toasty.info(getApplicationContext(), "Empleados", Toast.LENGTH_SHORT, true).show();
            }
        });

        cardProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Productos", Toast.LENGTH_SHORT, true).show();
            }
        });

        cardCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Categorías", Toast.LENGTH_SHORT, true).show();
            }
        });

        cardProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Proveedor", Toast.LENGTH_SHORT, true).show();
            }
        });

        cardUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Usuarios", Toast.LENGTH_SHORT, true).show();
            }
        });

        cardReportes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Reportes", Toast.LENGTH_SHORT, true).show();
            }
        });

        cardClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Clientes", Toast.LENGTH_SHORT, true).show();
            }
        });

        iconConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(getApplicationContext(), "Configuración", Toast.LENGTH_SHORT, true).show();
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