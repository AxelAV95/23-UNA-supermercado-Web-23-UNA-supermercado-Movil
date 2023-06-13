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
import una.ac.cr.supermercadoapp.data.ClienteData;
import una.ac.cr.supermercadoapp.domain.Cliente;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.interfaces.ClienteICallback;

public class VolleyCliente {

    private ArrayList<Cliente> listaClientes;
    public void  obtenerClientes(Context context, String IP, ClienteICallback callback){
        listaClientes = new ArrayList<>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_CLIENTE, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);

                        String clienteid  = jsonObject.getString("clienteid");
                        String clientenombre = jsonObject.getString("clientenombre");
                        String clienteapellidos = jsonObject.getString("clienteapellidos");
                        String clientecedula  = jsonObject.getString("clientecedula");
                        String clientedireccion  = jsonObject.getString("clientedireccion");
                        String clientetelefono  = jsonObject.getString("clientetelefono");
                        String clientecorreo = jsonObject.getString("clientecorreo");
                        String clientefechaafiliacion = jsonObject.getString("clientefechaafiliacion");
                        String clientetipomembresia = jsonObject.getString("clientetipomembresia");


                        listaClientes.add(new Cliente(Integer.parseInt(clienteid), clientenombre,clienteapellidos,Integer.parseInt(clientecedula),clientedireccion,Integer.parseInt(clientetelefono),clientecorreo,clientefechaafiliacion, Integer.parseInt(clientetipomembresia)));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                callback.onClientesReceived(listaClientes);

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

    public void insertarCliente(Context context,Cliente cliente, String IP){
        JSONObject clienteJson = new JSONObject();
        try {
            clienteJson.put("metodo", "insertar");
            clienteJson.put("nombre",cliente.getNombre());
            clienteJson.put("apellidos",cliente.getApellidos());
            clienteJson.put("cedula",cliente.getCedula());
            clienteJson.put("direccion",cliente.getDireccion());
            clienteJson.put("telefono",cliente.getTelefono());
            clienteJson.put("correo",cliente.getCorreo());
            clienteJson.put("fechaafiliacion",cliente.getFechaAfiliacion());
            clienteJson.put("tipomembresia",cliente.getTipoMembresia());



        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_CLIENTE,  clienteJson , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
               ClienteData clienteData = new ClienteData(context);
                if(response.optString("statusCode").toString().equals("200")){

                    // Toasty.success(context, "Se registró con éxito", Toast.LENGTH_SHORT, true).show();
                    Intent returnIntent = new Intent();
                    ((Activity) context).setResult(Activity.RESULT_OK, returnIntent);
                    ((Activity) context).finish();


                }else{
                    long resultado = clienteData.insertarCliente(new Cliente(cliente.getNombre(),cliente.getApellidos(),cliente.getCedula(),cliente.getDireccion(),cliente.getTelefono(),cliente.getCorreo(),cliente.getFechaAfiliacion(),cliente.getTipoMembresia()));
                    Toasty.error(context, "Error al insertar", Toast.LENGTH_SHORT, true).show();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("anyText",error.toString());
                Toasty.error(context, "Error al insertar vacio", Toast.LENGTH_SHORT, true).show();
            }
        });

        VolleySingleton.getVolleySingleton(context).addToRequestQueue(jsonObjectRequest);
    }

    public void modificarCliente(Context context,Cliente cliente, String IP){
        JSONObject clienteJson = new JSONObject();
        try {

            clienteJson.put("id",cliente.getId());
            clienteJson.put("nombre",cliente.getNombre());
            clienteJson.put("apellidos",cliente.getApellidos());
            clienteJson.put("cedula",cliente.getCedula());
            clienteJson.put("direccion",cliente.getDireccion());
            clienteJson.put("telefono",cliente.getTelefono());
            clienteJson.put("correo",cliente.getCorreo());
            clienteJson.put("fechaafiliacion",cliente.getFechaAfiliacion());
            clienteJson.put("tipomembresia",cliente.getTipoMembresia());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_CLIENTE,  clienteJson , new Response.Listener<JSONObject>() {
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
                Toasty.error(context, "Error al actualizar", Toast.LENGTH_SHORT, true).show();
            }
        });

        VolleySingleton.getVolleySingleton(context).addToRequestQueue(jsonObjectRequest);
    }

    public void eliminarCliente(Context context,Cliente cliente, String IP){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_CLIENTE+"?id="+cliente.getId(),  null , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if(response.optString("statusCode").toString().equals("200")){

                    Toasty.success(context, "Eliminado con éxito", Toast.LENGTH_SHORT, true).show();

                }else{
                    Toasty.error(context, "Error al eliminar", Toast.LENGTH_SHORT, true).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("anyText",error.toString());
                Toasty.error(context, error.toString(), Toast.LENGTH_SHORT, true).show();
                Toasty.error(context, "Error al eliminar", Toast.LENGTH_SHORT, true).show();
            }
        });

        VolleySingleton.getVolleySingleton(context).addToRequestQueue(jsonObjectRequest);
    }


}
