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
import una.ac.cr.supermercadoapp.data.ProveedorData;
import una.ac.cr.supermercadoapp.data.TipoUsuarioData;
import una.ac.cr.supermercadoapp.domain.Proveedor;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.network.VolleyProveedor;
import una.ac.cr.supermercadoapp.network.VolleyTipoUsuario;

public class FormularioProveedorActivity extends AppCompatActivity {

    private Button botonMetodo;
    private Proveedor mProveedor;
    private EditText nombre;
    private EditText direccion;

    private EditText telefono;
    private EditText correo;
    private EditText latitud;
    private EditText longitud;
    private  VolleyProveedor volleyProveedor;

    private ProveedorData proveedorData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_proveedor);


        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        proveedorData = new ProveedorData(this);

        verificarEstadoSesion(cedula);
        volleyProveedor = new VolleyProveedor();
        iniciarComponentes();
        Intent intent = getIntent();
        if(intent.getExtras().getString("metodo").equals("agregar")){
            botonMetodo.setText("Agregar");
        }else if(intent.getExtras().getString("metodo").equals("actualizar")){
            mProveedor = (Proveedor) getIntent().getSerializableExtra("proveedor");
            botonMetodo.setText("Actualizar");
            nombre.setText(mProveedor.getNombre());
            direccion.setText(mProveedor.getDireccion());
            correo.setText(mProveedor.getCorreo());
            telefono.setText(String.valueOf(mProveedor.getTelefono()));
            latitud.setText(mProveedor.getLatitud());
            longitud.setText(mProveedor.getLongitud());

        }

        agregarEventos();
        botonMetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(nombre.getText().toString().equals("")) {
                    nombre.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese un nombre", Toast.LENGTH_SHORT, true).show();
                    } else if(direccion.getText().toString().equals("")){
                            direccion.requestFocus();
                            Toasty.info(getApplicationContext(), "Ingrese una dirección", Toast.LENGTH_SHORT, true).show();


                    }else if(telefono.getText().toString().equals("")){
                        telefono.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese un teléfono", Toast.LENGTH_SHORT, true).show();


                     }else if(correo.getText().toString().equals("")){
                    correo.requestFocus();
                        Toasty.info(getApplicationContext(), "Ingrese un correo", Toast.LENGTH_SHORT, true).show();


                    }else if(latitud.getText().toString().equals("")){
                    latitud.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese una latitud", Toast.LENGTH_SHORT, true).show();


                    }else if(longitud.getText().toString().equals("")){
                    longitud.requestFocus();
                        Toasty.info(getApplicationContext(), "Ingrese una longitud", Toast.LENGTH_SHORT, true).show();


                    }else{
                         if(intent.getExtras().getString("metodo").equals("agregar")){
                        Proveedor proveedor = new Proveedor(nombre.getText().toString(),direccion.getText().toString(),Integer.parseInt(telefono.getText().toString()),
                                correo.getText().toString(),latitud.getText().toString(),longitud.getText().toString());
                        //método de agregar
                        if(verificarConexion()){
                            //agrega a la base de datos del servidor
                            volleyProveedor.insertarProveedor(FormularioProveedorActivity.this,proveedor,credenciales.getString("ip", "192.168.100.16"));
                        }else{
                            //agrega a la base de datos móvil
                            if(proveedorData.insertarProveedor(new Proveedor(nombre.getText().toString(),direccion.getText().toString(),Integer.parseInt(telefono.getText().toString()),correo.getText().toString(),
                                    latitud.getText().toString(),longitud.getText().toString())) == -1){
                                Toasty.error(getApplicationContext(), "Error al insertar en la base de datos móvil", Toast.LENGTH_SHORT, true).show();
                            }else{
                                Toasty.success(getApplicationContext(), "Insertado con éxito en la base de datos móvil", Toast.LENGTH_SHORT, true).show();
                            }
                            finish();
                        }

                    }else if(intent.getExtras().getString("metodo").equals("actualizar")){
                        //método de actualizar
                        Proveedor proveedor = new Proveedor(nombre.getText().toString(),direccion.getText().toString(),Integer.parseInt(telefono.getText().toString()),correo.getText().toString(),
                                latitud.getText().toString(),longitud.getText().toString());
                        volleyProveedor.actualizarProveedor(FormularioProveedorActivity.this,proveedor,credenciales.getString("ip", "192.168.100.216"));

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
        nombre = findViewById(R.id.nombre);
        direccion = findViewById(R.id.direccion);
        correo = findViewById(R.id.correo);
        telefono = findViewById(R.id.telefono);
        latitud = findViewById(R.id.latitud);
        longitud = findViewById(R.id.longitud);
    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(FormularioProveedorActivity.this, LoginActivity.class);
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