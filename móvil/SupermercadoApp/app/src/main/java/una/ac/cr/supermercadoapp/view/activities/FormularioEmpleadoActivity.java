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
import una.ac.cr.supermercadoapp.domain.Empleado;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;

public class FormularioEmpleadoActivity extends AppCompatActivity {

    private Button botonMetodo;
    private Empleado empleado;
    private EditText campoCedula, campoNombre, campoApellidos, campoTelefono, campoDireccion,
    campoFechaIngreso, camposFechaSalida;
    private Spinner spinnerEstado, spinnerTipoEmpleado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_empleado);
        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);
        verificarEstadoSesion(cedula);

        iniciarComponentes();

        Intent intent = getIntent();
        if(intent.getExtras().getString("metodo").equals("agregar")){
            botonMetodo.setText("Agregar");
        }else if(intent.getExtras().getString("metodo").equals("actualizar")){
            empleado = (Empleado) getIntent().getSerializableExtra("empleado");
            botonMetodo.setText("Actualizar");
            campoCedula.setText(String.valueOf(empleado.getCedula()));
            campoNombre.setText(empleado.getNombre());
            campoApellidos.setText(empleado.getApellidos());
            campoTelefono.setText(String.valueOf(empleado.getTelefono()));
            campoDireccion.setText(empleado.getDireccion());
            campoFechaIngreso.setText(empleado.getFechaIngreso());
            camposFechaSalida.setText(empleado.getFechaSalida());
            //llenar spinner estado
            //llenar spinner tipo de empleado
        }

        botonMetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(campoCedula.getText().toString().equals("")){
                    campoCedula.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese una cédula", Toast.LENGTH_SHORT, true).show();
                }else if(campoNombre.getText().toString().equals("")){
                    campoNombre.requestFocus();
                    Toasty.info(getApplicationContext(), "Ingrese un nombre", Toast.LENGTH_SHORT, true).show();

                    //...Validar demás campos
                }else{
                    if(intent.getExtras().getString("metodo").equals("agregar")){
                        //método de agregar
                        Empleado empleado = new Empleado(Integer.parseInt(campoCedula.getText().toString()),
                                campoNombre.getText().toString(),campoApellidos.getText().toString(),Integer.parseInt(campoTelefono.getText().toString()),
                                campoDireccion.getText().toString(), campoFechaIngreso.getText().toString(),camposFechaSalida.getText().toString(),
                                Integer.parseInt(spinnerEstado.getSelectedItem().toString()),Integer.parseInt(spinnerTipoEmpleado.getSelectedItem().toString())
                                );

                      //llamar volley de insertar y pasar objeto

                    }else if(intent.getExtras().getString("metodo").equals("actualizar")){
                        //método de actualizar
                        Empleado empleadoActualizado = new Empleado(empleado.getId(),
                                campoNombre.getText().toString(),campoApellidos.getText().toString(),Integer.parseInt(campoTelefono.getText().toString()),
                                campoDireccion.getText().toString(), campoFechaIngreso.getText().toString(),camposFechaSalida.getText().toString(),
                                Integer.parseInt(spinnerEstado.getSelectedItem().toString()),Integer.parseInt(spinnerTipoEmpleado.getSelectedItem().toString())
                        );
                        //llamar volley de actualizar y pasar objeto

                    }
                }


            }
        });

        Slidr.attach(this);
    }

    private void iniciarComponentes() {
        botonMetodo = findViewById(R.id.btn_metodo);
        campoCedula = findViewById(R.id.form_emp_cedula);
        campoNombre = findViewById(R.id.form_emp_nombre);
        campoApellidos = findViewById(R.id.form_emp_apellidos);
        campoTelefono = findViewById(R.id.form_emp_telefono);
        campoDireccion = findViewById(R.id.form_emp_direccion);
        campoFechaIngreso = findViewById(R.id.form_emp_fecingreso);
        camposFechaSalida = findViewById(R.id.form_emp_fecsalida);
        spinnerEstado = findViewById(R.id.spinner_estado);
        spinnerTipoEmpleado = findViewById(R.id.spinner_tipo_empleado);
    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(FormularioEmpleadoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }
}