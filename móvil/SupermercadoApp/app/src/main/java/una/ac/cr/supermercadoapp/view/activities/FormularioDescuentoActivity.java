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
import una.ac.cr.supermercadoapp.data.DescuentoData;
import una.ac.cr.supermercadoapp.data.ProductoData;
import una.ac.cr.supermercadoapp.domain.Categoria;
import una.ac.cr.supermercadoapp.domain.Descuento;
import una.ac.cr.supermercadoapp.domain.Empleado;
import una.ac.cr.supermercadoapp.domain.Membresia;
import una.ac.cr.supermercadoapp.domain.Producto;
import una.ac.cr.supermercadoapp.domain.Proveedor;
import una.ac.cr.supermercadoapp.network.VolleyCategoria;
import una.ac.cr.supermercadoapp.network.VolleyDescuento;
import una.ac.cr.supermercadoapp.network.VolleyEmpleado;
import una.ac.cr.supermercadoapp.network.VolleyMembresia;
import una.ac.cr.supermercadoapp.network.VolleyProducto;
import una.ac.cr.supermercadoapp.network.VolleyProveedor;
import una.ac.cr.supermercadoapp.network.VolleyTipoUsuario;
import una.ac.cr.supermercadoapp.network.VolleyUsuario;
import una.ac.cr.supermercadoapp.view.activities.LoginActivity;
import una.ac.cr.supermercadoapp.view.interfaces.CategoriaICallback;
import una.ac.cr.supermercadoapp.view.interfaces.EmpleadoICallback;
import una.ac.cr.supermercadoapp.view.interfaces.MembresiaICallback;
import una.ac.cr.supermercadoapp.view.interfaces.ProveedorICallback;

public class FormularioDescuentoActivity extends AppCompatActivity {
    private Button botonMetodo;
    private Descuento descuento;

    private EditText tarifa;

    private VolleyDescuento volleyDescuento;
    private Membresia mem;
    private VolleyMembresia volleyMembresia;

    private ArrayList<String> listaMembresiaString;
    private ArrayList<Membresia> listaMembresias;
    private SearchableSpinner spinnermembresias;

    private DescuentoData descuentoData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_descuento);

        SharedPreferences credenciales = getSharedPreferences("credenciales", Context.MODE_PRIVATE);
        String cedula = credenciales.getString("cedula", null);
        verificarEstadoSesion(cedula);
        descuentoData = new DescuentoData(this);
        iniciarComponentes();


        volleyMembresia = new VolleyMembresia();
        volleyDescuento = new VolleyDescuento();

        Intent intent = getIntent();

        if (intent.getExtras().getString("metodo").equals("agregar")) {
            botonMetodo.setText("Agregar");

            volleyMembresia = new VolleyMembresia();
            MembresiaICallback listener3 = new MembresiaICallback() {
                @Override
                public void onMembresiasReceived(ArrayList<Membresia> lista) {
                    listaMembresias = lista;
                    listaMembresiaString.add("Membresia");

                    for (Membresia tu : listaMembresias) {
                        listaMembresiaString.add(tu.getDescripcion());
                    }
                    ArrayAdapter<String> membresiaAdapter = new ArrayAdapter<String>(getApplicationContext(), R.layout.simple_spinner_item, listaMembresiaString);
                    membresiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    spinnermembresias.setAdapter(membresiaAdapter);
                if(intent.getExtras().getString("metodo").equals("actualizar")) {
                        if (intent != null && intent.getExtras() != null) {
                            String membresia = descuento.getMembresia().getDescripcion();
                            int position = 0;
                            for (int i = 0; i < listaMembresiaString.size(); i++) {
                                if (listaMembresiaString.get(i).replaceAll("\\s", "").equals(membresia.replaceAll("\\s", ""))) {
                                    position = i;
                                    break;
                                }
                            }
                            spinnermembresias.setSelection(position);
                        }
                    }
                }


            };

            volleyMembresia.obtenerMembresias(this, credenciales.getString("ip", "192.168.100.216"), listener3);
        } else if (intent.getExtras().getString("metodo").equals("actualizar")) {
            descuento = (Descuento) getIntent().getSerializableExtra("descuento");
            botonMetodo.setText("Actualizar");
            tarifa.setText(String.valueOf(descuento.getTarifa()));

        }


        botonMetodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (tarifa.getText().toString().isEmpty()) {
                    Toasty.info(getApplicationContext(), "Debe agregar una tarifa", Toast.LENGTH_SHORT, true).show();
                    tarifa.requestFocus();
                    return;
                } else if (!tarifa.getText().toString().matches("\\d+(\\.\\d+)?")) {
                    tarifa.setError("El precio solo puede contener números");
                    tarifa.requestFocus();
                    return;
                }


                if (spinnermembresias.getSelectedItem().toString().equals("Membresias")) {
                    Toasty.info(getApplicationContext(), "Debe seleccionar una membresia", Toast.LENGTH_SHORT, true).show();
                    spinnermembresias.requestFocus();
                    return;
                }


                if (intent.getExtras().getString("metodo").equals("agregar")) {
                    Descuento descuento = new Descuento(
                            Double.parseDouble(tarifa.getText().toString()),
                            listaMembresias.get(spinnermembresias.getSelectedItemPosition() - 1).getId());
                    volleyDescuento.insertarDescuento(FormularioDescuentoActivity.this, descuento,
                            credenciales.getString("ip", "192.168.100.216"));

                } else if (intent.getExtras().getString("metodo").equals("actualizar")) {
                    Descuento descuentoActualizado = new Descuento(descuento.getId(),
                            Double.parseDouble(tarifa.getText().toString()),
                            listaMembresias.get(spinnermembresias.getSelectedItemPosition() - 1).getId());
                    volleyDescuento.actualizarDescuento(FormularioDescuentoActivity.this, descuentoActualizado,
                            credenciales.getString("ip", "192.168.100.216"));
                }
            }
        });
    }


    private void iniciarComponentes() {
        Slidr.attach(this);
        tarifa = findViewById(R.id.form_des_tarifa);
        spinnermembresias = findViewById(R.id.spinner_membresia);
        listaMembresiaString = new ArrayList<>();
        botonMetodo = findViewById(R.id.btn_metodo
        );
    }

    private void verificarEstadoSesion(String cedula) {
        if(cedula == null){
            Intent intent = new Intent(FormularioDescuentoActivity.this, LoginActivity.class);
            startActivity(intent);;
            finish();
        }
    }
}
