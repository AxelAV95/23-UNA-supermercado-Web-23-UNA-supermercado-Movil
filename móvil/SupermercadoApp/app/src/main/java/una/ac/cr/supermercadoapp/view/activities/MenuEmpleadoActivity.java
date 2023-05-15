package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;

public class MenuEmpleadoActivity extends AppCompatActivity {

    private CardView cardProductos, cardCategorias, cardProveedor, cardReportes, cardClientes;
    private ImageView iconCuenta, iconLogOut;
    private TapBarMenu tapMenu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_empleado);

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

        cardProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuEmpleadoActivity.this,ProductoActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cardCategorias.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuEmpleadoActivity.this,CategoriaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        cardProveedor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuEmpleadoActivity.this,ProveedorActivity.class);
                startActivity(intent);
                finish();
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
                Intent intent = new Intent(MenuEmpleadoActivity.this,ClienteActivity.class);
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

                Toasty.info(getApplicationContext(), cedula, Toast.LENGTH_SHORT, true).show();
            }
        });
    }


    private void iniciarComponentes() {

        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);


        verificarEstadoSesion(cedula);
        tapMenu = findViewById(R.id.tapBarMenu_emp);
        cardProductos = findViewById(R.id.cardv_productos_emp);
        cardCategorias = findViewById(R.id.cardv_categorias_emp);
        cardProveedor = findViewById(R.id.cardv_proveedores_emp);
        cardReportes = findViewById(R.id.cardv_reportes_emp);
        cardClientes = findViewById(R.id.cardv_clientes_emp);
        iconLogOut = findViewById(R.id.icon_logout_emp);
        iconCuenta = findViewById(R.id.icon_cuenta_emp);

    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(MenuEmpleadoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }

    private void agregarAnimaciones() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

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
                        .playOn(cardReportes);
                YoYo.with(Techniques.RubberBand)
                        .duration(700)
                        .repeat(0)
                        .playOn(cardClientes);

            }
        },3000);

    }

}