package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.data.TipoEmpleadoData;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.network.VolleyTipoEmpleado;

public class FormularioTipoEmpleadoActivity extends AppCompatActivity {
    private Button botonMetodo;
    private TipoEmpleado tipoEmpleado;
    private EditText campoDescripcion;
    private VolleyTipoEmpleado volleyTipoEmpleado;

    private TipoEmpleadoData tipoEmpleadoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_tipo_empleado);

        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);
        tipoEmpleadoData = new TipoEmpleadoData(this);

        // campoDescripcion = findViewById(R.id.form_te_desc);

        verificarEstadoSesion(cedula);
        volleyTipoEmpleado = new VolleyTipoEmpleado();
        iniciarComponentes();
        Intent intent = getIntent();
        if(intent.getExtras().getString("metodo").equals("agregar")){
            botonMetodo.setText("Agregar");
        }else if(intent.getExtras().getString("metodo").equals("actualizar")){
            tipoEmpleado = (TipoEmpleado) getIntent().getSerializableExtra("tipoempleado");
            botonMetodo.setText("Actualizar");
            campoDescripcion.setText(tipoEmpleado.getTipoEmpleadoDescripcion());
        }

        agregarEventos();
        botonMetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(campoDescripcion.getText().toString().equals("")){
                    campoDescripcion.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese una descripción", Toast.LENGTH_SHORT, true).show();
                }else{
                    if(intent.getExtras().getString("metodo").equals("agregar")){
                        TipoEmpleado tipoEmpleado1 = new TipoEmpleado(campoDescripcion.getText().toString());
                        //método de agregar
                        if(verificarConexion()){
                            //agrega a la base de datos del servidor
                            volleyTipoEmpleado.insertarTipoEmpleado(FormularioTipoEmpleadoActivity.this, tipoEmpleado1,credenciales.getString("ip", "192.168.100.216"));
                        }else{
                            //agrega a la base de datos móvil
                            if(tipoEmpleadoData.insertarTipoEmpleado(new TipoEmpleado(campoDescripcion.getText().toString())) == -1){
                                Toasty.error(getApplicationContext(), "Error al insertar en la base de datos móvil", Toast.LENGTH_SHORT, true).show();
                            }else{
                                Toasty.success(getApplicationContext(), "Insertado con éxito en la base de datos móvil", Toast.LENGTH_SHORT, true).show();
                            }
                            finish();
                        }

                    }else if(intent.getExtras().getString("metodo").equals("actualizar")){
                        //método de actualizar
                        TipoEmpleado tipoEmpleado1 = new TipoEmpleado(tipoEmpleado.getId(),campoDescripcion.getText().toString());
                        // System.out.println(campoDescripcion.getText().toString()+"P3");
                        volleyTipoEmpleado.actualizarTipoEmpleado(FormularioTipoEmpleadoActivity.this,tipoEmpleado1,credenciales.getString("ip", "192.168.100.216"));

                    }
                }
            }
        });
        Slidr.attach(this);
    }


    public boolean verificarConexion(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities capabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());
        return (capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET));
    }


    private void agregarEventos() {
    }

    private void iniciarComponentes() {
        botonMetodo = findViewById(R.id.btn_metodo);
        campoDescripcion = findViewById(R.id.form_te_desc);
    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(FormularioTipoEmpleadoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }



}