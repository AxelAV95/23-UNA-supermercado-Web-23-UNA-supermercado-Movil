package una.ac.cr.supermercadoapp.view.activities;

import androidx.activity.result.ActivityResultLauncher;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.journeyapps.barcodescanner.ScanContract;
import com.journeyapps.barcodescanner.ScanOptions;

import java.lang.reflect.Field;
import java.util.ArrayList;

import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.domain.CapturaQR;
import una.ac.cr.supermercadoapp.network.VolleyUsuario;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;

public class LoginActivity extends AppCompatActivity {

    private EditText campoCedula, campoPassword;
    private Button btnIniciarSesion;
    public SharedPreferences credenciales;
    private LottieAnimationView iconQr;

    private ImageView iconLogo;
    private String IP_DEFAULT;

    ActivityResultLauncher<ScanOptions> qrLauncher = registerForActivityResult(new ScanContract(), result->
    {
        if(result.getContents() !=null) {
            String cedula = result.getContents();
            VolleyUsuario volleyUsuario = new VolleyUsuario();
            IP_DEFAULT = credenciales.getString("ip", "192.168.100.216");
            volleyUsuario.iniciarSesionQR(this,cedula, IP_DEFAULT);

        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        credenciales = getSharedPreferences("credenciales", MODE_PRIVATE);

        String cedula = credenciales.getString("cedula", null);
        String tipo = credenciales.getString("tipo",null);
        if (cedula != null ) {

            if(tipo.equals("Administrador")){
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                finish();
            }else if(tipo.equals("Empleado")){
                Intent intent  = new Intent(this, MenuEmpleadoActivity.class);
                startActivity(intent);
                finish();
            }


        } else {
            setContentView(R.layout.activity_login);
        }

        campoCedula = findViewById(R.id.form_log_cedula);
        campoPassword = findViewById(R.id.form_log_pass);
        iconQr = findViewById(R.id.icon_qr);
        iconLogo = findViewById(R.id.icon_logo_ip);
        btnIniciarSesion = findViewById(R.id.btn_iniciar_sesion);

        btnIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                IP_DEFAULT = credenciales.getString("ip", "192.168.100.216");
                VolleyUsuario volleyUsuario = new VolleyUsuario();
                volleyUsuario.iniciarSesionNormal(LoginActivity.this, campoCedula.getText().toString(),campoPassword.getText().toString(),IP_DEFAULT);
            }
        });

        iconQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                escanearQR();
            }
        });

        iconLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mostrarInputDialog();
            }
        });


    }


    private void mostrarInputDialog() {
        final EditText editText = new EditText(this);
        editText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL | InputType.TYPE_CLASS_TEXT);
        editText.setText(credenciales.getString("ip", "192.168.100.216"));
        new AlertDialog.Builder(this)
                .setMessage("Ingrese la IP:")
                .setView(editText)
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String ip = editText.getText().toString().trim();
                        guardarIp(ip);
                    }
                })
                .setNegativeButton("Cancelar", null)
                .show();
    }

    private void guardarIp(String ip) {

        SharedPreferences.Editor editor = credenciales.edit();
        editor.putString("ip", ip);
        editor.apply();

        Toast.makeText(this, "IP guardada con éxito", Toast.LENGTH_SHORT).show();
    }


    private void escanearQR() {
        ScanOptions options = new ScanOptions();
        options.setPrompt("Tecla de volumen arriba para activar flash");
        options.setBeepEnabled(true);
        options.setOrientationLocked(true);
        options.setCaptureActivity(CapturaQR.class);
        qrLauncher.launch(options);
    }
}