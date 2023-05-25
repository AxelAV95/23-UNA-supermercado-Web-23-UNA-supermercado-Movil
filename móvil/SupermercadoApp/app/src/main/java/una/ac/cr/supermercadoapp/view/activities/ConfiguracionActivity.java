package una.ac.cr.supermercadoapp.view.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.domain.Configuracion;
import una.ac.cr.supermercadoapp.network.VolleyConfiguracion;
import una.ac.cr.supermercadoapp.view.interfaces.ConfiguracionICallback;

public class ConfiguracionActivity extends AppCompatActivity {

    private EditText campoNombre, campoTelefono, campoDireccion, campoCorreo;
    private Button botonActualizar;
    private VolleyConfiguracion volleyConfiguracion;
    private ConfiguracionICallback configuracionICallback;
    private SharedPreferences credenciales;
    private Configuracion auxiliar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracion);

        credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

        campoCorreo = findViewById(R.id.form_conf_correo);
        campoDireccion = findViewById(R.id.form_conf_direccion);
        campoTelefono = findViewById(R.id.form_conf_telefono);
        campoNombre = findViewById(R.id.form_conf_nombre);

        botonActualizar = findViewById(R.id.btn_form_conf_actualizar);


        volleyConfiguracion = new VolleyConfiguracion();

        configuracionICallback = new ConfiguracionICallback() {
            @Override
            public void onConfiguracionReceived(Configuracion configuracion) {
                auxiliar = configuracion;
                campoCorreo.setText(configuracion.getCorreo().toString());
                campoDireccion.setText(configuracion.getDireccion().toString());
                campoTelefono.setText(String.valueOf(configuracion.getTelefono()));
                campoNombre.setText(configuracion.getNombreSupermercado().toString());
            }
        };

        volleyConfiguracion.obtenerConfiguracion(this,credenciales.getString("ip", "192.168.100.216"),configuracionICallback);

        botonActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(campoNombre.getText().toString().equals("")){
                    campoNombre.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese un nombre ", Toast.LENGTH_SHORT, true).show();
                }else if(!campoNombre.getText().toString().matches("^[a-zA-Z0-9 ]+$")){
                    campoNombre.requestFocus();
                    Toasty.info(getApplicationContext(), "Debe ingresar un nombre válido (solo letras) ", Toast.LENGTH_SHORT, true).show();
                }else if(campoDireccion.getText().toString().equals("")){
                    campoDireccion.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese una dirección ", Toast.LENGTH_SHORT, true).show();
                }else if (!campoDireccion.getText().toString().matches("^[a-zA-Z0-9 ]+$")) {
                    campoDireccion.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese una dirección válida (solo letras, números y espacios)", Toast.LENGTH_SHORT, true).show();
                } else if(campoTelefono.getText().equals("")){
                    campoTelefono.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese un teléfono", Toast.LENGTH_SHORT, true).show();

                }else if(campoCorreo.getText().toString().equals("")){
                    campoCorreo.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese un correo", Toast.LENGTH_SHORT, true).show();
                }else if (!campoCorreo.getText().toString().matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")) {
                    campoCorreo.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese un correo válido", Toast.LENGTH_SHORT, true).show();
                } else{
                    Configuracion configuracionModificada = new Configuracion(auxiliar.getId(),campoNombre.getText().toString(),Integer.parseInt(campoTelefono.getText().toString()),campoCorreo.getText().toString(),campoDireccion.getText().toString(),auxiliar.getLogoRuta());
                  volleyConfiguracion.actualizarUsuario(ConfiguracionActivity.this,configuracionModificada,credenciales.getString("ip", "192.168.100.216"));
                }
            }
        });


    }

    public boolean esCorreoValido(String email) {
        String regex = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\\\.[A-Za-z]{2,}$";
        return email.matches(regex);
    }

}