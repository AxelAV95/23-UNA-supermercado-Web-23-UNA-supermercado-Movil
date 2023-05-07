package una.ac.cr.supermercadoapp.network;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.domain.Empleado;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.domain.Usuario;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.interfaces.EmpleadoICallback;
import una.ac.cr.supermercadoapp.view.interfaces.UsuarioICallback;

public class VolleyEmpleado {


    private ArrayList<Empleado> listaEmpleados;


    public void obtenerEmpleados(Context context, String IP, EmpleadoICallback callback){
        listaEmpleados = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, NetworkUtils.HTTP + IP + NetworkUtils.RUTA_EMPLEADO, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        //Datos del empleado
                        int empleadoid = jsonObject.getInt("empleadoid");
                        int empleadocedula = jsonObject.getInt("empleadocedula");
                        String empleadonombre = jsonObject.getString("empleadonombre");
                        String empleadoapellidos = jsonObject.getString("empleadoapellidos");
                        int empleadotelefono = jsonObject.getInt("empleadotelefono");
                        String empleadodireccion = jsonObject.getString("empleadodireccion");
                        String empleadofechaingreso = jsonObject.getString("empleadofechaingreso");
                        String empleadofechasalida = jsonObject.getString("empleadofechasalida");
                        int empleadoestado = jsonObject.getInt("empleadoestado");
                        int empleadotipo = jsonObject.getInt("empleadotipoid");
                        String tipoempleadodescripcion = jsonObject.getString("tipodescripcion");


                        TipoEmpleado tipoEmpleado = new TipoEmpleado(empleadotipo, tipoempleadodescripcion);
                        Empleado empleado = new Empleado(empleadoid,empleadocedula,empleadonombre,empleadoapellidos,empleadotelefono,empleadodireccion,empleadofechaingreso,empleadofechasalida,empleadoestado,tipoEmpleado);
                        listaEmpleados.add(empleado);
                    }

                }catch(JSONException e){
                    e.printStackTrace();
                }

                callback.onEmpleadoReceived(listaEmpleados);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("anyText",error.toString());
                Toasty.error(context, error.toString(), Toast.LENGTH_LONG, true).show();
                Toasty.error(context, "Error al obtener los datos.", Toast.LENGTH_SHORT, true).show();
            }
        });

        VolleySingleton.getVolleySingleton(context).addToRequestQueue(jsonArrayRequest);
    }
}
