package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;

import una.ac.cr.supermercadoapp.R;

public class TipoEmpleadoActivity extends AppCompatActivity {

    private SharedPreferences credenciales;

    private LottieAnimationView iconAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_empleado);
        credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        verificarEstadoSesion(cedula);

        iconAgregar = findViewById(R.id.icon_agregar_tipo_empleado);
        iconAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(TipoEmpleadoActivity.this, FormularioTipoEmpleadoActivity.class);
                intent.putExtra("metodo","agregar");
                startActivity(intent);

            }
        });
    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(TipoEmpleadoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }
}