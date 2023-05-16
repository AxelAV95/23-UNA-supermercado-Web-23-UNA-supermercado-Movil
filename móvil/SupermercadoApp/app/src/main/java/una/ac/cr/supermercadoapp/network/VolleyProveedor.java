package una.ac.cr.supermercadoapp.network;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import una.ac.cr.supermercadoapp.data.ProveedorData;
import una.ac.cr.supermercadoapp.data.TipoUsuarioData;
import una.ac.cr.supermercadoapp.domain.Proveedor;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.activities.MainActivity;
import una.ac.cr.supermercadoapp.view.activities.MenuEmpleadoActivity;
import una.ac.cr.supermercadoapp.view.interfaces.ProveedorICallback;
import una.ac.cr.supermercadoapp.view.interfaces.TipoUsuarioICallback;

public class VolleyProveedor {

    private ArrayList<Proveedor> listaProveedores;
    public void  obtenerProveedores(Context context, String IP, ProveedorICallback callback){
        listaProveedores = new ArrayList<>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_PROVEEDOR, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String proveedorid = jsonObject.getString("proveedorid");
                        String proveedornombre = jsonObject.getString("proveedornombre");
                        String proveedordireccion = jsonObject.getString("proveedordireccion");
                        int proveedortelefono = jsonObject.getInt("proveedortelefono");
                        String proveedorcorreo = jsonObject.getString("proveedorcorreo");
                        String proveedorlatitud = jsonObject.getString("proveedorlat");
                        String proveedorlongitud = jsonObject.getString("proveedorlong");
                        listaProveedores.add(new Proveedor( Integer.parseInt(proveedorid),proveedornombre,proveedordireccion,proveedortelefono,proveedorcorreo,
                                proveedorlatitud,proveedorlongitud));

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                callback.onProveedorReceived(listaProveedores);

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

    public void insertarProveedor(Context context,Proveedor proveedor, String IP){
        JSONObject proveedorJson = new JSONObject();
        try {
            proveedorJson.put("metodo", "insertar");
            proveedorJson.put("nombre",proveedor.getNombre());
            proveedorJson.put("direccion",proveedor.getDireccion());
            proveedorJson.put("telefono",proveedor.getTelefono());
            proveedorJson.put("correo",proveedor.getCorreo());
            proveedorJson.put("latitud",proveedor.getLatitud());
            proveedorJson.put("longitud",proveedor.getLongitud());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_PROVEEDOR,  proveedorJson , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ProveedorData proveedorData = new ProveedorData(context);
                if(response.optString("statusCode").toString().equals("200")){


                    long resultado = proveedorData.insertarProveedor(new Proveedor(proveedor.getNombre(),proveedor.getDireccion(),
                            proveedor.getTelefono(),proveedor.getCorreo(),proveedor.getLatitud(),proveedor.getLongitud()));
                    if(resultado == -1){
                        Toasty.error(context, "Error al insertar en la base de datos local", Toast.LENGTH_SHORT, true).show();
                    }else{
                    Toasty.success(context, "Se registró con éxito", Toast.LENGTH_SHORT, true).show();
                    Intent returnIntent = new Intent();
                    ((Activity) context).setResult(Activity.RESULT_OK, returnIntent);
                    ((Activity) context).finish();
                }

                }else{
                    long resultado = proveedorData.insertarProveedor(new Proveedor(proveedor.getNombre(),proveedor.getDireccion(),proveedor.getTelefono(),
                            proveedor.getCorreo(),proveedor.getLatitud(),proveedor.getLongitud()));
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

    public void actualizarProveedor(Context context,Proveedor proveedor, String IP){
        JSONObject proveedorJson = new JSONObject();
        try {
            proveedorJson.put("metodo", "insertar");
            proveedorJson.put("proveedorid",proveedor.getId());
            proveedorJson.put("proveedornombre",proveedor.getNombre());
            proveedorJson.put("proveedordireccion",proveedor.getDireccion());
            proveedorJson.put("proveedorcorreo",proveedor.getCorreo());
            proveedorJson.put("proveedortelefono",proveedor.getTelefono());

          //  proveedorJson.put("latitud",proveedor.getLatitud());
          //  proveedorJson.put("longitud",proveedor.getLongitud());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_PROVEEDOR,  proveedorJson , new Response.Listener<JSONObject>() {
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
                Toasty.error(context, "Error al actualizar el proveedor", Toast.LENGTH_SHORT, true).show();
            }
        });

        VolleySingleton.getVolleySingleton(context).addToRequestQueue(jsonObjectRequest);
    }

    public void eliminarProveedor(Context context,Proveedor proveedor, String IP){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_PROVEEDOR+"?id="+proveedor.getId(),  null , new Response.Listener<JSONObject>() {
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

