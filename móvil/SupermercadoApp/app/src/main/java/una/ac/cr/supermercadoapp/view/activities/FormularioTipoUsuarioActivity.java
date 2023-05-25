package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.r0adkll.slidr.Slidr;


import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.data.TipoUsuarioData;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.network.VolleyTipoUsuario;

public class FormularioTipoUsuarioActivity extends AppCompatActivity {

    private Button botonMetodo;
    private TipoUsuario mTipoUsuario;
    private EditText campoDescripcion;
    private  VolleyTipoUsuario volleyTipoUsuario;

    private TipoUsuarioData tipoUsuarioData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_tipo_usuario);

        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        tipoUsuarioData = new TipoUsuarioData(this);

        verificarEstadoSesion(cedula);
        volleyTipoUsuario = new VolleyTipoUsuario();
        iniciarComponentes();
        Intent intent = getIntent();
        if(intent.getExtras().getString("metodo").equals("agregar")){
            botonMetodo.setText("Agregar");
        }else if(intent.getExtras().getString("metodo").equals("actualizar")){
            mTipoUsuario = (TipoUsuario) getIntent().getSerializableExtra("tipoUsuario");
            botonMetodo.setText("Actualizar");
            campoDescripcion.setText(mTipoUsuario.getDescripcion());
        }

        agregarEventos();
        botonMetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(campoDescripcion.getText().toString().equals("")){
                    campoDescripcion.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese una descripción", Toast.LENGTH_SHORT, true).show();
                }else if (!campoDescripcion.getText().toString().matches("^[A-Za-z ]+$")) {
                    campoDescripcion.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese una descripción válida", Toast.LENGTH_SHORT, true).show();
                }else{
                    if(intent.getExtras().getString("metodo").equals("agregar")){
                        TipoUsuario tipoUsuario = new TipoUsuario(campoDescripcion.getText().toString());
                        //método de agregar
                        if(verificarConexion()){
                            //agrega a la base de datos del servidor
                            volleyTipoUsuario.insertarUsuario(FormularioTipoUsuarioActivity.this,tipoUsuario,credenciales.getString("ip", "192.168.100.216"));
                        }else{
                            //agrega a la base de datos móvil
                            if(tipoUsuarioData.insertarTipoUsuario(new TipoUsuario(campoDescripcion.getText().toString(),-1)) == -1){
                                Toasty.error(getApplicationContext(), "Error al insertar en la base de datos móvil", Toast.LENGTH_SHORT, true).show();
                            }else{
                                Toasty.success(getApplicationContext(), "Insertado con éxito en la base de datos móvil", Toast.LENGTH_SHORT, true).show();
                            }
                            finish();
                        }

                    }else if(intent.getExtras().getString("metodo").equals("actualizar")){
                        //método de actualizar
                        TipoUsuario tipoUsuario = new TipoUsuario(mTipoUsuario.getId(),campoDescripcion.getText().toString());
                        volleyTipoUsuario.actualizarUsuario(FormularioTipoUsuarioActivity.this,tipoUsuario,credenciales.getString("ip", "192.168.100.216"));

                    }
                }
            }
        });

        Slidr.attach(this);

    }

    private void agregarEventos() {
    }



    private void iniciarComponentes() {
        botonMetodo = findViewById(R.id.btn_metodo);
        campoDescripcion = findViewById(R.id.form_tu_desc);
    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(FormularioTipoUsuarioActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }

    public boolean verificarConexion(){
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkCapabilities capabilities = manager.getNetworkCapabilities(manager.getActiveNetwork());
        return (capabilities != null && capabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET));


    }
}