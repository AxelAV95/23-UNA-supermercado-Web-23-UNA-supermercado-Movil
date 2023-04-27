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
import una.ac.cr.supermercadoapp.data.TipoUsuarioData;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.activities.MainActivity;
import una.ac.cr.supermercadoapp.view.activities.MenuEmpleadoActivity;
import una.ac.cr.supermercadoapp.view.interfaces.TipoUsuarioICallback;

public class VolleyTipoUsuario {

    private ArrayList<TipoUsuario> listaTipoUsuarios;
    public void  obtenerTipos(Context context, String IP, TipoUsuarioICallback callback){
        listaTipoUsuarios = new ArrayList<>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_TIPO_USUARIO, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String tipoid = jsonObject.getString("tipoid");
                        String tipodescripcion = jsonObject.getString("tipodescripcion");
                        listaTipoUsuarios.add(new TipoUsuario(Integer.parseInt(tipoid), tipodescripcion));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                callback.onTiposUsuarioReceived(listaTipoUsuarios);

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

    public void insertarUsuario(Context context,TipoUsuario tipoUsuario, String IP){
        JSONObject tipoUsuarioJson = new JSONObject();
        try {
            tipoUsuarioJson.put("metodo", "insertar");
            tipoUsuarioJson.put("descripcion",tipoUsuario.getDescripcion());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_TIPO_USUARIO,  tipoUsuarioJson , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TipoUsuarioData tipoUsuarioData = new TipoUsuarioData(context);
                    if(response.optString("statusCode").toString().equals("200")){


                        long resultado = tipoUsuarioData.insertarTipoUsuario(new TipoUsuario(tipoUsuario.getDescripcion(),0));
                        if(resultado == -1){
                            Toasty.error(context, "Error al insertar en la base de datos local", Toast.LENGTH_SHORT, true).show();
                        }else{
                            Intent returnIntent = new Intent();
                            ((Activity) context).setResult(Activity.RESULT_OK, returnIntent);
                            ((Activity) context).finish();
                        }

                    }else{
                        long resultado = tipoUsuarioData.insertarTipoUsuario(new TipoUsuario(tipoUsuario.getDescripcion(),-1));
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

    public void actualizarUsuario(Context context,TipoUsuario tipoUsuario, String IP){
        JSONObject tipoUsuarioJson = new JSONObject();
        try {
            tipoUsuarioJson.put("metodo", "insertar");
            tipoUsuarioJson.put("id",tipoUsuario.getId());
            tipoUsuarioJson.put("descripcion",tipoUsuario.getDescripcion());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_TIPO_USUARIO,  tipoUsuarioJson , new Response.Listener<JSONObject>() {
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

    public void eliminarUsuario(Context context,TipoUsuario tipoUsuario, String IP){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_TIPO_USUARIO+"?id="+tipoUsuario.getId(),  null , new Response.Listener<JSONObject>() {
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
