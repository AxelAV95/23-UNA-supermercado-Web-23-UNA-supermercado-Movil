package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.domain.Descuento;
import una.ac.cr.supermercadoapp.domain.Empleado;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;

public class FormularioDescuentoActivity extends AppCompatActivity {

    private Button botonMetodo;
    private Descuento descuento;
    private EditText tarifa,membresia;

    private Spinner  spinnermembresia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_descuento);
        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);
        verificarEstadoSesion(cedula);

        iniciarComponentes();

        Intent intent = getIntent();
        if(intent.getExtras().getString("metodo").equals("agregar")){
            botonMetodo.setText("Agregar");
        }else if(intent.getExtras().getString("metodo").equals("actualizar")){
            descuento = (Descuento) getIntent().getSerializableExtra("descuento");
            botonMetodo.setText("Actualizar");
            tarifa.setText(String.valueOf(descuento.getTarifa()));
            membresia.setText(descuento.getMembresiaid());
            spinnermembresia = findViewById(R.id.spinner_membresia);

        }

        botonMetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(tarifa.getText().toString().equals("")){
                    tarifa.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese una tarifa", Toast.LENGTH_SHORT, true).show();
                }else if(membresia.getText().toString().equals("")){
                    membresia.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese el tipo membresía", Toast.LENGTH_SHORT, true).show();


                }else{
                    if(intent.getExtras().getString("metodo").equals("agregar")){
                        //método de agregar
                        Descuento descuento = new Descuento(Integer.parseInt(tarifa.getText().toString()),

                                Integer.parseInt(spinnermembresia.getSelectedItem().toString()));

                    }else if(intent.getExtras().getString("metodo").equals("actualizar")){
                        //método de actualizar
                        Descuento descuentoActualizado = new Descuento(descuento.getId(),
                                Float.parseFloat(tarifa.getText().toString()),
                                Integer.parseInt(spinnermembresia.getSelectedItem().toString()));


                    }
                }


            }
        });

        Slidr.attach(this);
    }

    private void iniciarComponentes() {
        botonMetodo = findViewById(R.id.btn_metodo);
        tarifa = findViewById(R.id.form_des_tarifa);
        //membresia = findViewById(R.id.spinner_membresia);
        spinnermembresia = findViewById(R.id.spinner_membresia);

    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(FormularioDescuentoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }
}
