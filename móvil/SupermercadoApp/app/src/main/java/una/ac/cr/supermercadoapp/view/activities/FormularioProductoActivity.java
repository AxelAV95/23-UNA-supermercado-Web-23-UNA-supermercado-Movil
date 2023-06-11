package una.ac.cr.supermercadoapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
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
import una.ac.cr.supermercadoapp.data.ProductoData;
import una.ac.cr.supermercadoapp.domain.Categoria;
import una.ac.cr.supermercadoapp.domain.Empleado;
import una.ac.cr.supermercadoapp.domain.Producto;
import una.ac.cr.supermercadoapp.domain.Proveedor;
import una.ac.cr.supermercadoapp.network.VolleyCategoria;
import una.ac.cr.supermercadoapp.network.VolleyEmpleado;
import una.ac.cr.supermercadoapp.network.VolleyProducto;
import una.ac.cr.supermercadoapp.network.VolleyProveedor;
import una.ac.cr.supermercadoapp.network.VolleyTipoUsuario;
import una.ac.cr.supermercadoapp.network.VolleyUsuario;
import una.ac.cr.supermercadoapp.view.activities.LoginActivity;
import una.ac.cr.supermercadoapp.view.interfaces.CategoriaICallback;
import una.ac.cr.supermercadoapp.view.interfaces.EmpleadoICallback;
import una.ac.cr.supermercadoapp.view.interfaces.ProveedorICallback;

public class FormularioProductoActivity extends AppCompatActivity {
    private Button botonMetodo;
    private Producto producto;
    private EditText campoNombre;
    private EditText campoPrecio;
    private EditText campoFecha;
    private EditText campoStock;
    private VolleyProducto volleyProducto;
    private VolleyCategoria volleyCategoria;
    private VolleyProveedor volleyProveedor;
    private ArrayList<String> listaCategoriaString;
    private ArrayList<Categoria> listaCategoria;
    private SearchableSpinner spinnerCategoria;
    private ArrayList<String> listaProveedorString;
    private ArrayList<Proveedor> listaProveedores;
    private SearchableSpinner spinnerProveedor;
    private SearchableSpinner spinnerEstado;
    private List<String> estadosList = new ArrayList<>();
    private int estado = 0;
    private ProductoData productoData;

    // Calendario para seleccionar la fecha
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_producto);

        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula = credenciales.getString("cedula", null);
        verificarEstadoSesion(cedula);
        productoData = new ProductoData(this);
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

                campoFecha.setText(fechaSeleccionada);
            }

        };

        campoFecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(FormularioProductoActivity.this, date, calendar.get(Calendar.YEAR),
                        calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, estadosList);
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerEstado.setAdapter(estadoAdapter);
        volleyProveedor = new VolleyProveedor();

        volleyProducto = new VolleyProducto();
        volleyCategoria = new VolleyCategoria();
        CategoriaICallback listener = new CategoriaICallback() {
            @Override
            public void onCategoriaReceived(ArrayList<Categoria> lista) {
                listaCategoria = lista;
                listaCategoriaString.add("Categoria");

                for (Categoria tu : listaCategoria) {
                    listaCategoriaString.add(tu.getNombre());
                }
                ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, listaCategoriaString);

                categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinnerCategoria.setAdapter(categoriaAdapter);
            }
        };

        volleyCategoria.obtenerTipos(this, credenciales.getString("ip", "192.168.100.216"), listener);

        Intent intent = getIntent();

        if (intent.getExtras().getString("metodo").equals("agregar")) {
            botonMetodo.setText("Agregar");

            CategoriaICallback listener_e = new CategoriaICallback() {
                @Override
                public void onCategoriaReceived(ArrayList<Categoria> lista) {
                    listaCategoria = lista;

                    listaCategoriaString.add("Categorias");
                    for (Categoria e : listaCategoria) {
                        listaCategoriaString.add(e.getNombre());
                    }

                    ArrayAdapter<String> categoriaAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, listaCategoriaString);
                    categoriaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerCategoria.setAdapter(categoriaAdapter);
                    if (intent != null && intent.getExtras() != null) {
                        String categoria = producto.getCategoria().getNombre();
                        int position = 0;
                        for (int i = 0; i < listaCategoriaString.size(); i++) {
                            if (listaCategoriaString.get(i).replaceAll("\\s", "").equals(categoria.replaceAll("\\s", ""))) {
                                position = i;
                                break;
                            }
                        }

                        spinnerCategoria.setSelection(position);
                    }
                }
            };

            volleyProveedor = new VolleyProveedor();
            ProveedorICallback listener3 = new ProveedorICallback() {
                @Override
                public void onProveedorReceived(ArrayList<Proveedor> lista) {
                    listaProveedores = lista;
                    listaProveedorString.add("Proveedor");

                    for (Proveedor tu : listaProveedores) {
                        listaProveedorString.add(tu.getNombre());
                    }
                    ArrayAdapter<String> proveedorAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, listaProveedorString);

                    proveedorAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnerProveedor.setAdapter(proveedorAdapter);
                }
            };

            volleyProveedor.obtenerProveedores(this, credenciales.getString("ip", "192.168.100.216"), listener3);
        } else if (intent.getExtras().getString("metodo").equals("actualizar")) {
            producto = (Producto) getIntent().getSerializableExtra("producto");
            botonMetodo.setText("Actualizar");
            campoNombre.setText(producto.getNombre());
            campoPrecio.setText(String.valueOf(producto.getPrecio()));
            campoFecha.setText(String.valueOf(producto.getFechaIngreso()));
            campoStock.setText(String.valueOf(producto.getStock()));


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

                if (campoPrecio.getText().toString().isEmpty()) {
                    Toasty.info(getApplicationContext(), "Debe agregar un precio", Toast.LENGTH_SHORT, true).show();
                    campoPrecio.requestFocus();
                    return;
                } else if (!campoPrecio.getText().toString().matches("\\d+(\\.\\d+)?")) {
                    campoPrecio.setError("El precio solo puede contener números");
                    campoPrecio.requestFocus();
                    return;
                }

                if (campoStock.getText().toString().isEmpty()) {
                    Toasty.info(getApplicationContext(), "Debe agregar un stock", Toast.LENGTH_SHORT, true).show();
                    campoStock.requestFocus();
                    return;
                } else if (!campoStock.getText().toString().matches("\\d+")) {
                    campoStock.setError("El stock solo puede contener números enteros");
                    campoStock.requestFocus();
                    return;
                }

                if (spinnerCategoria.getSelectedItem().equals("Categorias")) {
                    Toasty.info(getApplicationContext(), "Debe seleccionar una categoría", Toast.LENGTH_SHORT, true).show();
                    spinnerCategoria.requestFocus();
                    return;
                }

                if (spinnerProveedor.getSelectedItem().toString().equals("Proveedores")) {
                    Toasty.info(getApplicationContext(), "Debe seleccionar un proveedor", Toast.LENGTH_SHORT, true).show();
                    spinnerProveedor.requestFocus();
                    return;
                }

                if (campoFecha.getText().toString().isEmpty()) {
                    Toasty.info(getApplicationContext(), "Debe seleccionar una fecha de ingreso", Toast.LENGTH_SHORT, true).show();
                    campoFecha.requestFocus();
                    return;
                }

                if (spinnerEstado.getSelectedItem().equals("Disponible")) {
                    estado = 1;
                } else {
                    estado = 0;
                }

                if (intent.getExtras().getString("metodo").equals("agregar")) {
                    Producto producto = new Producto(campoNombre.getText().toString(),
                            Double.parseDouble(campoPrecio.getText().toString()), campoFecha.getText().toString(),
                            Integer.parseInt(campoStock.getText().toString()), estado,
                            listaCategoria.get(spinnerCategoria.getSelectedItemPosition() - 1).getId(),
                            listaProveedores.get(spinnerProveedor.getSelectedItemPosition() - 1).getId());
                    volleyProducto.insertarProducto(FormularioProductoActivity.this, producto,
                            credenciales.getString("ip", "192.168.100.216"));

                } else if (intent.getExtras().getString("metodo").equals("actualizar")) {
                    Producto productoActualizado = new Producto(producto.getId(), campoNombre.getText().toString(),
                            Double.parseDouble(campoPrecio.getText().toString()), campoFecha.getText().toString(),
                            Integer.parseInt(campoStock.getText().toString()), estado,
                            listaCategoria.get(spinnerCategoria.getSelectedItemPosition() - 1).getId(),
                            listaProveedores.get(spinnerProveedor.getSelectedItemPosition() - 1).getId());
                    volleyProducto.actualizarProducto(FormularioProductoActivity.this, productoActualizado,
                            credenciales.getString("ip", "192.168.100.216"));
                }
            }
        });
    }

    private void iniciarComponentes() {
        Slidr.attach(this);
        campoNombre = findViewById(R.id.form_prod_nombre);
        campoPrecio = findViewById(R.id.form_prod_precio);
        campoFecha = findViewById(R.id.form_prod_fecingreso);
        campoStock = findViewById(R.id.form_prod_stock);
        spinnerCategoria = findViewById(R.id.spinner_categoria_prod);
        listaCategoriaString = new ArrayList<>();
        spinnerProveedor = findViewById(R.id.spinner_proveedor);
        listaProveedorString = new ArrayList<>();
        spinnerEstado = findViewById(R.id.spinner_estado_prod);
        estadosList.add("Disponible");
        estadosList.add("No Disponible");
        botonMetodo = findViewById(R.id.btn_metodo
        );
    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(FormularioProductoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }
}
