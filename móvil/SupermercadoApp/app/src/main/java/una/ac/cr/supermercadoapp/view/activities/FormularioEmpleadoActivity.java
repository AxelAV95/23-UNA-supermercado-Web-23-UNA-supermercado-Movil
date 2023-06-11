package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.r0adkll.slidr.Slidr;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.domain.Empleado;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.network.VolleyEmpleado;
import una.ac.cr.supermercadoapp.network.VolleyTipoEmpleado;
import una.ac.cr.supermercadoapp.view.interfaces.TipoEmpleadoICallback;

public class FormularioEmpleadoActivity extends AppCompatActivity {
    private VolleyTipoEmpleado volleyTipoEmpleado;
    private VolleyEmpleado volleyEmpleado;
    private Button botonMetodo;
    private Empleado empleado;
    private EditText campoCedula, campoNombre, campoApellidos, campoTelefono, campoDireccion,
            campoFechaIngreso, camposFechaSalida;
    private Spinner spinnerEstado, spinnerTipoEmpleado;
    private ArrayList<TipoEmpleado> listaTipoEmpleado;
    private ArrayList<String> listaTipoEmpleadoString;
    private int estado = 0;
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener date, date2;
    private int tipoEmpleadoId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_empleado);
        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula  = credenciales.getString("cedula", null);
        verificarEstadoSesion(cedula);

        volleyEmpleado = new VolleyEmpleado();
        volleyTipoEmpleado = new VolleyTipoEmpleado();
        iniciarComponentes();
        tipoEmpleadoId = 0;

        // Inicializar el calendario
        calendar = Calendar.getInstance();

        // Configurar el formato de fecha
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Configurar el listener para la selección de fecha
        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                // Formatear la fecha al formato de MySQL (YYYY-MM-DD)
                String fechaIngresoSeleccionada = String.format(Locale.getDefault(), "%04d-%02d-%02d",
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH));

                campoFechaIngreso.setText(fechaIngresoSeleccionada);
            }
        };

        // Configurar el listener para la selección de fecha
        date2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);

                String fechaSalidaSeleccionada = String.format(Locale.getDefault(), "%04d-%02d-%02d",
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH));
                camposFechaSalida.setText(fechaSalidaSeleccionada);
            }
        };

        campoFechaIngreso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FormularioEmpleadoActivity.this, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        camposFechaSalida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FormularioEmpleadoActivity.this, date2, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Intent intent = getIntent();
        TipoEmpleadoICallback listener_e = new TipoEmpleadoICallback() {
            @Override
            public void onTiposEmpeladoReceived(ArrayList<TipoEmpleado> lista) {
                listaTipoEmpleado = lista;
                listaTipoEmpleadoString.add("TipoEmpleado");

                for (TipoEmpleado e : listaTipoEmpleado) {
                    listaTipoEmpleadoString.add(e.getTipoEmpleadoDescripcion());
                }

                ArrayAdapter<String> tipoEmpleadoAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, listaTipoEmpleadoString);
                tipoEmpleadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerTipoEmpleado.setAdapter(tipoEmpleadoAdapter);
                if(intent.getExtras().getString("metodo").equals("actualizar")) {
                    if (intent != null && intent.getExtras() != null) {
                        String tipoEmpleado = empleado.getTipoEmpleado().getTipoEmpleadoDescripcion();
                        int position = 0;
                        for (int i = 0; i < listaTipoEmpleadoString.size(); i++) {
                            if (listaTipoEmpleadoString.get(i).replaceAll("\\s", "").equals(tipoEmpleado.replaceAll("\\s", ""))) {
                                position = i;
                                break;
                            }
                        }
                        spinnerTipoEmpleado.setSelection(position);
                    }
                }
            }
        };
        volleyTipoEmpleado.obtenerTipos(this, credenciales.getString("ip", "192.168.100.216"), listener_e);

        if(intent.getExtras().getString("metodo").equals("agregar")){
            botonMetodo.setText("Agregar");
            camposFechaSalida = findViewById(R.id.form_emp_fecsalida);
            camposFechaSalida.setVisibility(View.INVISIBLE);


        }else if(intent.getExtras().getString("metodo").equals("actualizar")){
            empleado = (Empleado) getIntent().getSerializableExtra("empleado");
            botonMetodo.setText("Actualizar");
            campoCedula.setText(String.valueOf(empleado.getCedula()));
            campoNombre.setText(empleado.getNombre());
            campoApellidos.setText(empleado.getApellidos());
            campoTelefono.setText(String.valueOf(empleado.getTelefono()));
            campoDireccion.setText(empleado.getDireccion());
            campoFechaIngreso.setText(String.valueOf(empleado.getFechaIngreso())); //setText(empleado.getFechaIngreso());
            camposFechaSalida.setText(empleado.getFechaSalida());
            //llenar spinner estado
            //llenar spinner tipo de empleado
        }

        botonMetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (campoCedula.getText().toString().isEmpty()) {
                    Toasty.info(getApplicationContext(), "Debe agregar una cédula", Toast.LENGTH_SHORT, true).show();
                    campoCedula.requestFocus();
                    return;
                } else if (!campoCedula.getText().toString().matches("\\d+(\\.\\d+)?")) {
                    campoCedula.setError("La cédula solo puede contener números");
                    campoCedula.requestFocus();
                    return;
                }

                if (campoNombre.getText().toString().isEmpty()) {
                    Toasty.info(getApplicationContext(), "Debe agregar un nombre", Toast.LENGTH_SHORT, true).show();
                    campoNombre.requestFocus();
                    return;
                } else if (!campoNombre.getText().toString().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                    campoNombre.setError("El nombre solo puede contener letras");
                    campoNombre.requestFocus();
                    return;
                }

                if (campoApellidos.getText().toString().isEmpty()) {
                    Toasty.info(getApplicationContext(), "Debe agregar los apellidos", Toast.LENGTH_SHORT, true).show();
                    campoApellidos.requestFocus();
                    return;
                } else if (!campoApellidos.getText().toString().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                    campoApellidos.setError("Los apellidos solo puede contener letras");
                    campoApellidos.requestFocus();
                    return;
                }

                if (campoDireccion.getText().toString().isEmpty()) {
                    Toasty.info(getApplicationContext(), "Debe agregar una dirección", Toast.LENGTH_SHORT, true).show();
                    campoDireccion.requestFocus();
                    return;
                } else if (!campoDireccion.getText().toString().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                    campoDireccion.setError("La dirección solo puede contener letras");
                    campoDireccion.requestFocus();
                    return;
                }

                if (spinnerTipoEmpleado.getSelectedItem().toString().equals("TipoEmpleado")) {
                    Toasty.info(getApplicationContext(), "Debe seleccionar un tipo de empleado", Toast.LENGTH_SHORT, true).show();
                    spinnerTipoEmpleado.requestFocus();
                    return;
                }

                if (campoFechaIngreso.getText().toString().isEmpty()) {
                    Toasty.info(getApplicationContext(), "Debe seleccionar una fecha de ingreso", Toast.LENGTH_SHORT, true).show();
                    campoFechaIngreso.requestFocus();
                    return;
                }

                if (spinnerEstado.getSelectedItem().equals("Disponible")) {
                    estado = 1;
                } else {
                    estado = 0;
                }

                if (listaTipoEmpleado != null && !listaTipoEmpleado.isEmpty()) {
                } else {
                    Toasty.error(FormularioEmpleadoActivity.this, "La lista de tipos de empleado está vacía o nula", Toast.LENGTH_SHORT, true).show();
                }
                    //...Validar demás campos
               // }else{
                    if(intent.getExtras().getString("metodo").equals("agregar")){
                        //método de agregar
                        Empleado mmempleado = new Empleado(1,Integer.parseInt(campoCedula.getText().toString()),
                                campoNombre.getText().toString(),
                                campoApellidos.getText().toString(),
                                Integer.parseInt(campoTelefono.getText().toString()),
                                campoDireccion.getText().toString(),
                                campoFechaIngreso.getText().toString(),
                                "0000-00-00",
                                estado,
                                listaTipoEmpleado.get(spinnerTipoEmpleado.getSelectedItemPosition() - 1).getId());

                        volleyEmpleado.insertarEmpleado(FormularioEmpleadoActivity.this, mmempleado,credenciales.getString("ip", "192.168.100.216"));
                        Intent intent1 = new Intent(FormularioEmpleadoActivity.this, EmpleadoActivity.class);
                        startActivity(intent1);

                    }else if(intent.getExtras().getString("metodo").equals("actualizar")){
                        //método de actualizar
                        Empleado empleadoActualizado = new Empleado(empleado.getId(),
                                Integer.parseInt(campoCedula.getText().toString()),
                                campoNombre.getText().toString(),
                                campoApellidos.getText().toString(),
                                Integer.parseInt(campoTelefono.getText().toString()),
                                campoDireccion.getText().toString(),
                                campoFechaIngreso.getText().toString(),
                                camposFechaSalida.getText().toString(),
                                estado,
                                listaTipoEmpleado.get(spinnerTipoEmpleado.getSelectedItemPosition() - 1).getId());
                        //llamar volley de actualizar y pasar objeto
                      //  Toasty.success(getApplicationContext(), "LLEGO AQUI "+empleado.getId(), Toast.LENGTH_SHORT, true).show();
                        volleyEmpleado.actualizarEmpleado(FormularioEmpleadoActivity.this, empleadoActualizado,credenciales.getString("ip", "192.168.100.216"));
                       // Intent intent2 = new Intent(FormularioEmpleadoActivity.this, EmpleadoActivity.class);
                       // startActivity(intent2);
                    }
               // }
            }
        });
        Slidr.attach(this);

        /*    volleyTipoEmpleado.obtenerTipos(this,credenciales.getString("ip", "192.168.100.216"), new TipoEmpleadoICallback() {
                @Override
                public void onTiposEmpeladoReceived(ArrayList<TipoEmpleado> listaTipoEmpleado) {
                    ArrayList<String> listaDescripcionTipoEmpleado = new ArrayList<>();

                    for (TipoEmpleado tipoEmpleado : listaTipoEmpleado) {
                        listaDescripcionTipoEmpleado.add(tipoEmpleado.getTipoEmpleadoDescripcion());
                    }
                    ArrayAdapter<String> tipoEmpleadoAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_spinner_item, listaDescripcionTipoEmpleado);
                    tipoEmpleadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerTipoEmpleado.setAdapter(tipoEmpleadoAdapter);
                }
            });*/
        }

    private void iniciarComponentes() {
        listaTipoEmpleadoString = new ArrayList<>(); // Inicializar la lista
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
        cargarSpinnerEstado();
    }

    private void cargarSpinnerEstado() {
        ArrayList<String> estadosList = new ArrayList<>();
        estadosList.add("Disponible");
        estadosList.add("No Disponible");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, estadosList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(adapter);
    }
    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(FormularioEmpleadoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }
}