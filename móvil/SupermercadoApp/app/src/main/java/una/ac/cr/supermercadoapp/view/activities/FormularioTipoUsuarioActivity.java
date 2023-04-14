package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;



import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;

public class FormularioTipoUsuarioActivity extends AppCompatActivity {

    private Button botonMetodo;
    private TipoUsuario mTipoUsuario;
    private EditText campoDescripcion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_tipo_usuario);
        botonMetodo = findViewById(R.id.btn_metodo);
        campoDescripcion = findViewById(R.id.form_tu_desc);

        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);

        if(cedula == null){
            Intent intent = new Intent(FormularioTipoUsuarioActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }



        Intent intent = getIntent();

        if(intent.getExtras().getString("metodo").equals("agregar")){
            botonMetodo.setText("Agregar");
        }else if(intent.getExtras().getString("metodo").equals("actualizar")){
            mTipoUsuario = (TipoUsuario) getIntent().getSerializableExtra("tipoUsuario");
            botonMetodo.setText("Actualizar");

            campoDescripcion.setText(mTipoUsuario.getDescripcion());
        }

        //String password = "mypassword";
        //String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());

        botonMetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(intent.getExtras().getString("metodo").equals("agregar")){
                    //método de agregar
                    Toast.makeText(getApplicationContext(),"Agregando ", Toast.LENGTH_SHORT).show();
                }else if(intent.getExtras().getString("metodo").equals("actualizar")){
                    //método de actualizar
                    Toast.makeText(getApplicationContext(),"Actualizando "+mTipoUsuario.getId() + " "+mTipoUsuario.getDescripcion(), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}