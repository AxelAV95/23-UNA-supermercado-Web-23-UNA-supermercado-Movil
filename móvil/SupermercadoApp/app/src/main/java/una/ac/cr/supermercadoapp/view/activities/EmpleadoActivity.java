package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.SearchView;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieProperty;
import com.airbnb.lottie.SimpleColorFilter;
import com.airbnb.lottie.model.KeyPath;
import com.airbnb.lottie.value.LottieValueCallback;
import com.skydoves.powermenu.MenuAnimation;
import com.skydoves.powermenu.OnMenuItemClickListener;
import com.skydoves.powermenu.PowerMenu;
import com.skydoves.powermenu.PowerMenuItem;

import una.ac.cr.supermercadoapp.R;

public class EmpleadoActivity extends AppCompatActivity {

    private LottieAnimationView tipoEmpleado,iconEmpleados;

    private PowerMenu powerMenu;
    private SharedPreferences credenciales;
    private LottieAnimationView iconAgregar;
    private SearchView searchEmpleado; //buscador

    private OnMenuItemClickListener<PowerMenuItem> onMenuItemClickListener = new OnMenuItemClickListener<PowerMenuItem>() {
        @Override
        public void onItemClick(int position, PowerMenuItem item) {

            if(item.title.equals("Tipos de empleado")){

                powerMenu.dismiss();
                Intent intent = new Intent(EmpleadoActivity.this, TipoEmpleadoActivity.class);
                startActivity(intent);

            }else if(item.title.equals("Menú principal")){
                powerMenu.dismiss();
                String cedula = credenciales.getString("cedula", null);
                String tipo = credenciales.getString("tipo",null);
                if (cedula != null ) {

                    if(tipo.equals("Administrador")){
                        Intent intent = new Intent(EmpleadoActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }else if(tipo.equals("Empleado")){
                        Intent intent  = new Intent(EmpleadoActivity.this, MenuEmpleadoActivity.class);
                        startActivity(intent);
                        finish();
                    }


                }
            }


        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empleado);

        credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        verificarEstadoSesion(cedula);


        powerMenu = new PowerMenu.Builder(this)

                .addItem(new PowerMenuItem("Tipos de empleado", false)) // add an item.
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

        tipoEmpleado = findViewById(R.id.icon_ver_tipos_empleados);
        int primaryColor = ContextCompat.getColor(this, R.color.primary);
        tipoEmpleado.addValueCallback(
                new KeyPath("**"),
                LottieProperty.COLOR_FILTER,
                new LottieValueCallback<>(new SimpleColorFilter(primaryColor)));



        iconEmpleados = findViewById(R.id.icon_empleados);

        tipoEmpleado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (powerMenu.isShowing()) {
                    powerMenu.dismiss();
                    return;
                }
                powerMenu.showAsDropDown(view);
            }
        });

        iconAgregar = findViewById(R.id.icon_add_empleado);
        iconAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(EmpleadoActivity.this, FormularioEmpleadoActivity.class);
                intent.putExtra("metodo","agregar");
                startActivity(intent);

            }
        });



    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(EmpleadoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }
}