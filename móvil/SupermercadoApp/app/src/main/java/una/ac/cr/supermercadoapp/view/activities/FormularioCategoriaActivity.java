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
import una.ac.cr.supermercadoapp.data.CategoriaData;
import una.ac.cr.supermercadoapp.domain.Categoria;
import una.ac.cr.supermercadoapp.network.VolleyCategoria;

public class FormularioCategoriaActivity extends AppCompatActivity {

    private Button botonMetodo;
    private Categoria mCategoria;
    private EditText campoNombre;
    private  VolleyCategoria volleyCategoria;

    private CategoriaData categoriaData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_categoria);

        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        categoriaData = new CategoriaData(this);

        verificarEstadoSesion(cedula);
        volleyCategoria = new VolleyCategoria();
        iniciarComponentes();
        Intent intent = getIntent();
        if(intent.getExtras().getString("metodo").equals("agregar")){
            botonMetodo.setText("Agregar");
        }else if(intent.getExtras().getString("metodo").equals("actualizar")){
            mCategoria = (Categoria) getIntent().getSerializableExtra("categoria");
            botonMetodo.setText("Actualizar");
            campoNombre.setText(mCategoria.getNombre());
        }

        agregarEventos();
        botonMetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(campoNombre.getText().toString().equals("")){
                    campoNombre.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese un nombre", Toast.LENGTH_SHORT, true).show();
                }else{
                    if(intent.getExtras().getString("metodo").equals("agregar")){
                        Categoria categoria = new Categoria(campoNombre.getText().toString());
                        //método de agregar
                        if(verificarConexion()){
                            //agrega a la base de datos del servidor
                            volleyCategoria.insertarCategoria(FormularioCategoriaActivity.this,categoria,credenciales.getString("ip", "192.168.100.216"));
                        }else{
                            //agrega a la base de datos móvil
                            if(categoriaData.insertarCategoria(new Categoria(campoNombre.getText().toString(),-1)) == -1){
                                Toasty.error(getApplicationContext(), "Error al insertar en la base de datos móvil", Toast.LENGTH_SHORT, true).show();
                            }else{
                                Toasty.success(getApplicationContext(), "Insertado con éxito en la base de datos móvil", Toast.LENGTH_SHORT, true).show();
                            }
                            finish();
                        }

                    }else if(intent.getExtras().getString("metodo").equals("actualizar")){
                        //método de actualizar
                        Categoria categoria = new Categoria(mCategoria.getId(),campoNombre.getText().toString());
                        System.out.println(campoNombre.getText().toString()+"P3");
                        volleyCategoria.actualizarCategoria(FormularioCategoriaActivity.this,categoria,credenciales.getString("ip", "192.168.100.216"));

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
        campoNombre = findViewById(R.id.form_tu_desc);
    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(FormularioCategoriaActivity.this, LoginActivity.class);
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