package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
import com.toptoche.searchablespinnerlibrary.SearchableSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.R;
import una.ac.cr.supermercadoapp.data.ClienteData;
import una.ac.cr.supermercadoapp.domain.Membresia;
import una.ac.cr.supermercadoapp.domain.Cliente;
import una.ac.cr.supermercadoapp.network.VolleyMembresia;
import una.ac.cr.supermercadoapp.network.VolleyCliente;
import una.ac.cr.supermercadoapp.view.interfaces.MembresiaICallback;

public class FormularioClienteActivity extends AppCompatActivity {
    private Button botonMetodo;



    private Cliente cliente;
    private EditText id;

    private EditText campoNombre;
    private EditText campoApellidos;
    private EditText campoCedula;
    private EditText campoDireccion;
    private EditText campoTelefono;
    private EditText campoCorreo;
    private EditText campoFechaAfiliacion;
    private EditText campoTipoMembresia;
    private VolleyCliente volleyCliente;

    private ArrayList<String> listaMebresiaString;
    private ArrayList<Membresia> listaMebresia;
    private SearchableSpinner spinnerMembresia;
    private Spinner spinnermembresia;

    private VolleyMembresia volleyMembresia;
    private SearchableSpinner spinnerEstado;
    private List<String> estadosList = new ArrayList<>();
    private int estado = 0;
    private ClienteData clienteData;

    // Calendario para seleccionar la fecha
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_cliente);

        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula = credenciales.getString("cedula", null);
        verificarEstadoSesion(cedula);
        clienteData = new ClienteData(this);
        iniciarComponentes();

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
                String fechaSeleccionada = String.format(Locale.getDefault(), "%04d-%02d-%02d",
                        calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH) + 1,
                        calendar.get(Calendar.DAY_OF_MONTH));

                campoFechaAfiliacion.setText(fechaSeleccionada);
            }

        };

        campoFechaAfiliacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FormularioClienteActivity.this, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });



        volleyCliente = new VolleyCliente();
        volleyMembresia = new VolleyMembresia();
        MembresiaICallback listener = new MembresiaICallback() {
            @Override
            public void onMembresiasReceived(ArrayList<Membresia> lista) {
                listaMebresia = lista;
                listaMebresiaString.add("Membresia");

                for (Membresia tu : listaMebresia) {
                    listaMebresiaString.add(tu.getDescripcion());
                }
                ArrayAdapter<String> membresiaAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, listaMebresiaString);

                membresiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerMembresia.setAdapter(membresiaAdapter);
            }
        };

        volleyMembresia.obtenerMembresias(this, credenciales.getString("ip", "192.168.100.216"), listener);

        Intent intent = getIntent();

        if (intent.getExtras().getString("metodo").equals("agregar")) {
            botonMetodo.setText("Agregar");

            MembresiaICallback listener_e = new MembresiaICallback() {
                @Override
                public void onMembresiasReceived(ArrayList<Membresia> lista) {
                    listaMebresia = lista;

                    listaMebresiaString.add("Membresias");
                    for (Membresia e : listaMebresia) {
                        listaMebresiaString.add(e.getDescripcion());
                    }

                    ArrayAdapter<String> membresiaAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, listaMebresiaString);
                    membresiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerMembresia.setAdapter(membresiaAdapter);
                    if (intent != null && intent.getExtras() != null) {
                        String membresia = cliente.getMembresia().getDescripcion();
                        int position = 0;
                        for (int i = 0; i < listaMebresiaString.size(); i++) {
                            if (listaMebresiaString.get(i).replaceAll("\\s", "").equals(membresia.replaceAll("\\s", ""))) {
                                position = i;
                                break;
                            }
                        }

                        spinnerMembresia.setSelection(position);
                    }
                }
            };


        } else if (intent.getExtras().getString("metodo").equals("actualizar")) {
            cliente = (Cliente) getIntent().getSerializableExtra("cliente");
            botonMetodo.setText("Actualizar");

            campoNombre.setText(cliente.getNombre());
            campoApellidos.setText(cliente.getApellidos());
            campoCedula.setText(String.valueOf(cliente.getCedula()));
            campoDireccion.setText(cliente.getDireccion());
            campoTelefono.setText(String.valueOf(cliente.getTelefono()));
            campoCorreo.setText(cliente.getCorreo());
            campoTipoMembresia.setText(String.valueOf(cliente.getTipoMembresia()));
            id.setText(String.valueOf(cliente.getId()));
        }

        botonMetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                if (campoCedula.getText().toString().isEmpty()) {
                    Toasty.info(getApplicationContext(), "Debe agregar una Cédula", Toast.LENGTH_SHORT, true).show();
                    campoCedula.requestFocus();
                    return;
                } else if (!campoCedula.getText().toString().matches("^\\d{9}$")) {
                    campoCedula.setError("La cédula debe contener solo números y ser exactamente 9");
                    campoCedula.requestFocus();
                    return;
                }

                if (campoDireccion.getText().toString().isEmpty()) {
                    Toasty.info(getApplicationContext(), "Debe agregar un Dirección", Toast.LENGTH_SHORT, true).show();
                    campoDireccion.requestFocus();
                    return;
                } else if (!campoDireccion.getText().toString().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
                    campoDireccion.setError("La dirección solo puede contener letras");
                    campoDireccion.requestFocus();
                    return;
                }

                if (campoTelefono.getText().toString().isEmpty()) {
                    Toasty.info(getApplicationContext(), "Debe agregar un Teléfono", Toast.LENGTH_SHORT, true).show();
                    campoTelefono.requestFocus();
                    return;
                } else if (!campoTelefono.getText().toString().matches("^\\d{8}$")) {
                    campoTelefono.setError("El Teléfono debe ser solo números y contener exactamente 8");
                    campoTelefono.requestFocus();
                    return;
                }


                if (campoCorreo.getText().toString().isEmpty()) {
                    Toasty.info(getApplicationContext(), "Debe agregar un Correo", Toast.LENGTH_SHORT, true).show();
                    campoCorreo.requestFocus();
                    return;
                } else if (!campoCorreo.getText().toString().matches("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")) {
                    campoCorreo.setError("El Correo no es válido");
                    campoCorreo.requestFocus();
                    return;
                }

                if (spinnerMembresia.getSelectedItem().equals("Membresia")) {
                    Toasty.info(getApplicationContext(), "Debe seleccionar una membresia", Toast.LENGTH_SHORT, true).show();
                    spinnerMembresia.requestFocus();
                    return;
                }


                if (campoFechaAfiliacion.getText().toString().isEmpty()) {
                    Toasty.info(getApplicationContext(), "Debe seleccionar una fecha de ingreso", Toast.LENGTH_SHORT, true).show();
                    campoFechaAfiliacion.requestFocus();
                    return;
                }

                String fechaIngresada = campoFechaAfiliacion.getText().toString();
                String formatoEsperado = "\\d{4}-\\d{2}-\\d{2}"; // Expresión regular para el formato YYYY-MM-DD

                Pattern pattern = Pattern.compile(formatoEsperado);
                Matcher matcher = pattern.matcher(fechaIngresada);

                if (!matcher.matches()) {
                    Toasty.error(getApplicationContext(), "El formato de fecha debe ser Año-Mes-Día", Toast.LENGTH_SHORT, true).show();
                    campoFechaAfiliacion.requestFocus();
                    return;
                }


                if (intent.getExtras().getString("metodo").equals("agregar")) {
                    Cliente cliente = new Cliente(campoNombre.getText().toString(),campoApellidos.getText().toString(),
                            Integer.parseInt(campoCedula.getText().toString()),campoDireccion.getText().toString(),
                            Integer.parseInt(campoTelefono.getText().toString()),campoCorreo.getText().toString(),
                            campoFechaAfiliacion.getText().toString(), listaMebresia.get(spinnerMembresia.getSelectedItemPosition() - 1).getId());

                    volleyCliente.insertarCliente(FormularioClienteActivity.this, cliente,
                            credenciales.getString("ip", "192.168.100.216"));
                } else if (intent.getExtras().getString("metodo").equals("actualizar")) {

                    Cliente clienteActualizado = new Cliente(Integer.parseInt(id.getText().toString()),campoNombre.getText().toString(),campoApellidos.getText().toString(),
                            Integer.parseInt(campoCedula.getText().toString()),campoDireccion.getText().toString(),
                            Integer.parseInt(campoTelefono.getText().toString()),campoCorreo.getText().toString(),
                            campoFechaAfiliacion.getText().toString(), listaMebresia.get(spinnerMembresia.getSelectedItemPosition() - 1).getId());
                    volleyCliente.modificarCliente(FormularioClienteActivity.this, clienteActualizado,
                            credenciales.getString("ip", "192.168.100.216"));
                }
            }
        });
    }

    private void iniciarComponentes() {
        Slidr.attach(this);

        campoNombre = findViewById(R.id.form_cliente_nombre);
        campoApellidos = findViewById(R.id.form_cliente_apellidos);
        campoCedula = findViewById(R.id.form_cliente_cedula);
        campoDireccion = findViewById(R.id.form_cliente_direccion);
        campoTelefono = findViewById(R.id.form_cliente_telefono);
        campoCorreo = findViewById(R.id.form_cliente_correo);
        campoFechaAfiliacion = findViewById(R.id.form_cliente_fecha);
        spinnerMembresia= findViewById(R.id.spinner_membresia3);
        listaMebresiaString = new ArrayList<>();

        botonMetodo = findViewById(R.id.btn_metodo
        );
    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(FormularioClienteActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }
}
