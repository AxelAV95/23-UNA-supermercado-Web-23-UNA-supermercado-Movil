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
import una.ac.cr.supermercadoapp.data.MembresiaData;
import una.ac.cr.supermercadoapp.domain.Membresia;
import una.ac.cr.supermercadoapp.network.VolleyMembresia;

public class FormularioMembresiaActivity extends AppCompatActivity {

    private Button botonMetodo;
    private Membresia membresia;
    private EditText campoDescripcion;
    private VolleyMembresia volleyMembresia;

    private MembresiaData membresiaData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_membresia);

        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);
        membresiaData = new MembresiaData(this);

        verificarEstadoSesion(cedula);
        volleyMembresia = new VolleyMembresia();
        iniciarComponentes();
        Intent intent = getIntent();
        if(intent.getExtras().getString("metodo").equals("agregar")){
            botonMetodo.setText("Agregar");
        }else if(intent.getExtras().getString("metodo").equals("actualizar")){
            membresia = (Membresia) getIntent().getSerializableExtra("membresia");
            botonMetodo.setText("Actualizar");
            campoDescripcion.setText(membresia.getDescripcion());
        }

        agregarEventos();
        botonMetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(campoDescripcion.getText().toString().equals("")){
                    campoDescripcion.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese una descripción", Toast.LENGTH_SHORT, true).show();
                }else{
                    String nombre = campoDescripcion.getText().toString().trim();
                    if (nombre.isEmpty()) {
                        campoDescripcion.setError("La descripción no puede estar vacía");
                        campoDescripcion.requestFocus();
                        return;
                    }
                    if (!nombre.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                        campoDescripcion.setError("La descripción solo puede contener letras");
                        campoDescripcion.requestFocus();
                        return;
                    }

                    if(intent.getExtras().getString("metodo").equals("agregar")){
                        Membresia membresia1 = new Membresia(campoDescripcion.getText().toString());
                        //método de agregar
                         volleyMembresia.insertarMembresia(FormularioMembresiaActivity.this, membresia1,credenciales.getString("ip", "192.168.100.216"));


                    }else if(intent.getExtras().getString("metodo").equals("actualizar")){
                        //método de actualizar
                        Membresia membresia1 = new Membresia(membresia.getId(),campoDescripcion.getText().toString());
                        // System.out.println(campoDescripcion.getText().toString()+"P3");
                        volleyMembresia.actualizarMembresia(FormularioMembresiaActivity.this,membresia1,credenciales.getString("ip", "192.168.100.216"));

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
            Intent intent = new Intent(FormularioMembresiaActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }




}