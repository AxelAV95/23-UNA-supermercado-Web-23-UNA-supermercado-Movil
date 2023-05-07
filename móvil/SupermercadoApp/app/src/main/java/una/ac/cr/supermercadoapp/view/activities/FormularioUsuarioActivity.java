package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.daimajia.swipe.util.Attributes;
import com.r0adkll.slidr.Slidr;
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.domain.Empleado;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.domain.Usuario;
import una.ac.cr.supermercadoapp.network.VolleyEmpleado;
import una.ac.cr.supermercadoapp.network.VolleyTipoUsuario;
import una.ac.cr.supermercadoapp.network.VolleyUsuario;
import una.ac.cr.supermercadoapp.view.adapters.TipoUsuarioAdapter;
import una.ac.cr.supermercadoapp.view.interfaces.EmpleadoICallback;
import una.ac.cr.supermercadoapp.view.interfaces.TipoUsuarioICallback;

public class FormularioUsuarioActivity extends AppCompatActivity {

    private Button botonMetodo;
    private Usuario usuario;
    private EditText campoPassword;
    private VolleyUsuario volleyUsuario;
    private VolleyTipoUsuario volleyTipoUsuario;
    private VolleyEmpleado volleyEmpleado;

    private ArrayList<String> listaEmpleadosString;
    private ArrayList<Empleado> listaEmpleados;
    private SearchableSpinner spinnerEmpleado;

    private ArrayList<String> listaTipoUsuarioString;
    private ArrayList<TipoUsuario> listaTipoUsuarios;
    private SearchableSpinner spinnerTipoUsuario;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_usuario);

        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);
        verificarEstadoSesion(cedula);

        botonMetodo = findViewById(R.id.btn_metodo);
        campoPassword = findViewById(R.id.form_u_password);
        spinnerEmpleado = findViewById(R.id.spinner_empleados);
        spinnerTipoUsuario = findViewById(R.id.spinner_tipo_usuario);

        listaEmpleados = new ArrayList<>();
        listaTipoUsuarios = new ArrayList<>();
        listaEmpleadosString = new ArrayList<>();
        listaTipoUsuarioString = new ArrayList<>();

        spinnerEmpleado.setTitle("Seleccione una opción");
        spinnerEmpleado.setPositiveButton("OK");
        spinnerEmpleado.setSelection(0);

        spinnerTipoUsuario.setTitle("Seleccione una opción");
        spinnerTipoUsuario.setPositiveButton("OK");
        spinnerTipoUsuario.setSelection(0);

        Intent intent = getIntent();

        volleyUsuario = new VolleyUsuario();
        volleyTipoUsuario = new VolleyTipoUsuario();
        TipoUsuarioICallback listener = new TipoUsuarioICallback() {
            @Override
            public void onTiposUsuarioReceived(ArrayList<TipoUsuario> lista) {
                listaTipoUsuarios = lista;

                listaTipoUsuarioString.add("Tipos de usuario");
                for (TipoUsuario tu: listaTipoUsuarios) {
                    listaTipoUsuarioString.add(tu.getDescripcion());
                }

                ArrayAdapter<String> tipoUsuarioAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, listaTipoUsuarioString);
                tipoUsuarioAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerTipoUsuario.setAdapter(tipoUsuarioAdapter);

            }
        };
        volleyTipoUsuario.obtenerTipos(this,credenciales.getString("ip", "192.168.100.216"),listener);

        volleyEmpleado = new VolleyEmpleado();


        //Para validación
        /*if(spinnerEmpleado.getSelectedItem().equals("-")){
            Toasty.info(getApplicationContext(), "Debe seleccionar un empleado", Toast.LENGTH_SHORT, true).show();
        }else if(spinnerTipoUsuario.getSelectedItem().toString().equals("-")){
            Toasty.info(getApplicationContext(), "Debe seleccionar un tipo de usuario", Toast.LENGTH_SHORT, true).show();
        }*/

        spinnerEmpleado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String item = (String) parent.getItemAtPosition(position);

                if(!item.equals("Empleados")){
                    if(!intent.getExtras().getString("metodo").equals("actualizar")){
                        volleyUsuario.verificarEmpleado(getApplicationContext(),credenciales.getString("ip", "192.168.100.216"),listaEmpleados.get(position-1).getId(), botonMetodo);
                    }

                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // Do nothing
            }
        });



        if(intent.getExtras().getString("metodo").equals("agregar")){
            botonMetodo.setText("Agregar");

            EmpleadoICallback listener_e = new EmpleadoICallback() {
                @Override
                public void onEmpleadoReceived(ArrayList<Empleado> lista) {
                    listaEmpleados = lista;

                    listaEmpleadosString.add("Empleados");
                    for (Empleado e: listaEmpleados) {
                        listaEmpleadosString.add(e.getNombre()+ " " +e.getApellidos());
                    }

                    ArrayAdapter<String> empleadoAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, listaEmpleadosString);
                    empleadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerEmpleado.setAdapter(empleadoAdapter);

                }
            };

            volleyEmpleado.obtenerEmpleados(this,credenciales.getString("ip", "192.168.100.216"),listener_e);


        }else if(intent.getExtras().getString("metodo").equals("actualizar")){
            usuario = (Usuario) getIntent().getSerializableExtra("usuario");
            botonMetodo.setText("Actualizar");
            campoPassword.setHint("Nueva contraseña");
            spinnerEmpleado.setEnabled(false);


            EmpleadoICallback listener_e = new EmpleadoICallback() {
                @Override
                public void onEmpleadoReceived(ArrayList<Empleado> lista) {
                    listaEmpleados = lista;

                    listaEmpleadosString.add("Empleados");
                    for (Empleado e: listaEmpleados) {
                        listaEmpleadosString.add(e.getNombre()+ " " +e.getApellidos());
                    }

                    ArrayAdapter<String> empleadoAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, listaEmpleadosString);
                    empleadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerEmpleado.setAdapter(empleadoAdapter);


                    if (intent != null && intent.getExtras() != null) {
                        String empleado = usuario.getEmpleado().getNombre()+" "+usuario.getEmpleado().getApellidos();
                        int position = 0;
                        for (int i = 0; i < listaEmpleadosString.size(); i++) {
                            if (listaEmpleadosString.get(i).replaceAll("\\s","").equals(empleado.replaceAll("\\s",""))) {
                                position = i;
                                break;
                            }
                        }

                        spinnerEmpleado.setSelection(position);
                    }


                }
            };

            volleyEmpleado.obtenerEmpleados(this,credenciales.getString("ip", "192.168.100.216"),listener_e);
            botonMetodo.setEnabled(true);


        }

        botonMetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(spinnerEmpleado.getSelectedItem().equals("Empleados")){
                    Toasty.info(getApplicationContext(), "Debe seleccionar un empleado", Toast.LENGTH_SHORT, true).show();
                    spinnerEmpleado.requestFocus();
                }else if(spinnerTipoUsuario.getSelectedItem().toString().equals("Tipos de usuario")){
                    Toasty.info(getApplicationContext(), "Debe seleccionar un tipo de usuario", Toast.LENGTH_SHORT, true).show();
                    spinnerEmpleado.requestFocus();
                }else if(campoPassword.getText().toString().equals("")){
                    Toasty.info(getApplicationContext(), "Debe agregar una contraseña", Toast.LENGTH_SHORT, true).show();
                    campoPassword.requestFocus();
                }else{
                    //Toasty.info(getApplicationContext(), "Insertando..", Toast.LENGTH_SHORT, true).show();
                    if(intent.getExtras().getString("metodo").equals("agregar")){
                        Usuario usuario = new Usuario(campoPassword.getText().toString(),listaEmpleados.get(spinnerEmpleado.getSelectedItemPosition()-1).getId(),listaTipoUsuarios.get(spinnerTipoUsuario.getSelectedItemPosition()-1).getId());
                        volleyUsuario.insertarUsuario(FormularioUsuarioActivity.this,usuario,credenciales.getString("ip", "192.168.100.216"));

                    }else if(intent.getExtras().getString("metodo").equals("actualizar")){
                        usuario = (Usuario) getIntent().getSerializableExtra("usuario");
                        //Toasty.info(getApplicationContext(), String.valueOf(usuario.getId()), Toast.LENGTH_SHORT, true).show();
                        //Toasty.info(getApplicationContext(), String.valueOf(listaEmpleados.get(spinnerEmpleado.getSelectedItemPosition()-1).getId()), Toast.LENGTH_SHORT, true).show();
                        //Toasty.info(getApplicationContext(), String.valueOf(listaTipoUsuarios.get(spinnerTipoUsuario.getSelectedItemPosition()-1).getId()), Toast.LENGTH_SHORT, true).show();
                        Usuario usuarioActualizado = new Usuario(usuario.getId(),campoPassword.getText().toString(),listaEmpleados.get(spinnerEmpleado.getSelectedItemPosition()-1).getId(),listaTipoUsuarios.get(spinnerTipoUsuario.getSelectedItemPosition()-1).getId());
                        volleyUsuario.actualizarUsuario(FormularioUsuarioActivity.this,usuarioActualizado,credenciales.getString("ip", "192.168.100.216"));
                    }
                }
            }
        });


        Slidr.attach(this);



    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(FormularioUsuarioActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }
}