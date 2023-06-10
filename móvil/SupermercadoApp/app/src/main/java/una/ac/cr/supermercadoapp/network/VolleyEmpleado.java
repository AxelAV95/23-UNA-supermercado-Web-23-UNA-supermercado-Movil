package una.ac.cr.supermercadoapp.network;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.data.BackupData;
import una.ac.cr.supermercadoapp.data.EmpleadoData;
import una.ac.cr.supermercadoapp.domain.Empleado;
import una.ac.cr.supermercadoapp.domain.Proveedor;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.interfaces.EmpleadoICallback;

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

    //********************************************************



    //agregar un producto
    public void insertarEmpleado(Context context, Empleado empleado, String IP){
        JSONObject empleadoJson = new JSONObject();
        try {
            empleadoJson.put("metodo", "insertar");
            empleadoJson.put("id",empleado.getId());
            empleadoJson.put("cedula",empleado.getCedula());
            empleadoJson.put("nombre",empleado.getNombre());
            empleadoJson.put("apellidos",empleado.getApellidos());
            empleadoJson.put("telefono",empleado.getTelefono());
            empleadoJson.put("direccion",empleado.getDireccion());
            empleadoJson.put("fechaIngreso",empleado.getFechaIngreso());
            empleadoJson.put("fechaSalida",empleado.getFechaSalida());
            empleadoJson.put("estado",empleado.getEstado());
            empleadoJson.put("tipoEmpleadoId",empleado.getTipo());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, una.ac.cr.supermercadoapp.utils.NetworkUtils.HTTP+IP+ NetworkUtils.RUTA_EMPLEADO,  empleadoJson , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                EmpleadoData empleadoData = new EmpleadoData(context);
                if(response.optString("statusCode").toString().equals("200")){

                    /*long resultado = empleadoData.insertarEmpleado(new Empleado(empleado.getCedula(),empleado.getNombre(),
                            empleado.getApellidos(),empleado.getTelefono(),empleado.getDireccion(),empleado.getFechaIngreso(),empleado.getFechaSalida(),
                            empleado.getEstado(),empleado.getTipo()));
                    if(resultado == -1){
                        Toasty.error(context, "Error al insertar en la base de datos local", Toast.LENGTH_SHORT, true).show();
                    }else{*/
                        Toasty.success(context, "Se registró con éxito", Toast.LENGTH_SHORT, true).show();
                        Intent returnIntent = new Intent();
                        ((Activity) context).setResult(Activity.RESULT_OK, returnIntent);
                        ((Activity) context).finish();
                   // }
                }else{
                    long resultado = empleadoData.insertarEmpleado(new Empleado(empleado.getCedula(),empleado.getNombre(),
                            empleado.getApellidos(),empleado.getTelefono(),empleado.getDireccion(),empleado.getFechaIngreso(),empleado.getFechaSalida(),
                            empleado.getEstado(),empleado.getTipo()));
                    Toasty.error(context, "Error al insertar", Toast.LENGTH_SHORT, true).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("anyText",error.toString());
                Toasty.error(context, "Error al insertar", Toast.LENGTH_SHORT, true).show();
            }
        });

        VolleySingleton.getVolleySingleton(context).addToRequestQueue(jsonObjectRequest);
    }



    //modificar un empleado
    public void actualizarEmpleado(Context context,Empleado empleado, String IP){
        JSONObject empleadoJson = new JSONObject();
        try {
            empleadoJson.put("id",empleado.getId());
            empleadoJson.put("cedula",empleado.getCedula());
            empleadoJson.put("nombre",empleado.getNombre());
            empleadoJson.put("apellidos",empleado.getApellidos());
            empleadoJson.put("telefono",empleado.getTelefono());
            empleadoJson.put("direccion",empleado.getDireccion());
            empleadoJson.put("fechaIngreso",empleado.getFechaIngreso());
            empleadoJson.put("fechaSalida",empleado.getFechaSalida());
            empleadoJson.put("estado",empleado.getEstado());
            empleadoJson.put("tipoEmpleadoId",empleado.getTipo());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_EMPLEADO,  empleadoJson , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.optString("statusCode").toString().equals("200")){
                    Intent returnIntent = new Intent();
                    ((Activity) context).setResult(200, returnIntent);
                    ((Activity) context).finish();
                }else{
                    Intent returnIntent = new Intent();
                    ((Activity) context).setResult(400, returnIntent);
                    ((Activity) context).finish();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("anyText",error.toString());
                Toasty.error(context, "Error al actualizar el registro con el ID "+empleado.getId()+" "+empleado.getNombre(), Toast.LENGTH_SHORT, true).show();
            }
        });

        VolleySingleton.getVolleySingleton(context).addToRequestQueue(jsonObjectRequest);
    }

    //eliminar un EMPLEADO
    public void eliminarEmpleado(Context context,int id, String IP){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_EMPLEADO+"?id="+id,null , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response.optString("statusCode").toString().equals("200")){
                    Toasty.success(context, "Eliminado con éxito", Toast.LENGTH_SHORT, true).show();
                }else{
                    Toasty.error(context, "Error al eliminar ", Toast.LENGTH_SHORT, true).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("anyText",error.toString());
                Toasty.error(context, error.toString(), Toast.LENGTH_SHORT, true).show();
                Toasty.error(context, "Error al eliminar el registo con el ID "+id, Toast.LENGTH_SHORT, true).show();
            }
        });
        VolleySingleton.getVolleySingleton(context).addToRequestQueue(jsonObjectRequest);
    }







}
