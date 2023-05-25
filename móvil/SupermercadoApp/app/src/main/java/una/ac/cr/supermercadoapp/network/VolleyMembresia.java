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
import una.ac.cr.supermercadoapp.data.MembresiaData;
import una.ac.cr.supermercadoapp.domain.Membresia;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.interfaces.MembresiaICallback;

public class VolleyMembresia {

    private ArrayList<Membresia> listaMembresias;
    public void  obtenerMembresias(Context context, String IP, MembresiaICallback callback){
        listaMembresias = new ArrayList<>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_MEMBRESIA, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String membresiaid = jsonObject.getString("membresiaid");
                        String membresiadescripcion = jsonObject.getString("membresiadescripcion");
                        listaMembresias.add(new Membresia(Integer.parseInt(membresiaid), membresiadescripcion));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                callback.onMembresiasReceived(listaMembresias);

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

    public void insertarMembresia(Context context,Membresia membresia, String IP){
        JSONObject membresiaJson = new JSONObject();
        try {
            membresiaJson.put("metodo", "insertar");
            membresiaJson.put("descripcion",membresia.getDescripcion());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_MEMBRESIA,  membresiaJson , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                MembresiaData membresiaData = new MembresiaData(context);
                if(response.optString("statusCode").toString().equals("200")){

                       // Toasty.success(context, "Se registró con éxito", Toast.LENGTH_SHORT, true).show();
                        Intent returnIntent = new Intent();
                        ((Activity) context).setResult(Activity.RESULT_OK, returnIntent);
                        ((Activity) context).finish();


                }else{
                    long resultado = membresiaData.insertarMembresia(new Membresia(membresia.getDescripcion()));
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

    public void actualizarMembresia(Context context,Membresia membresia, String IP){
        JSONObject membresiaJson = new JSONObject();
        try {

            membresiaJson.put("id",membresia.getId());
            membresiaJson.put("descripcion",membresia.getDescripcion());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_MEMBRESIA,  membresiaJson , new Response.Listener<JSONObject>() {
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

    public void eliminarMembresia(Context context,Membresia membresia, String IP){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_MEMBRESIA+"?id="+membresia.getId(),  null , new Response.Listener<JSONObject>() {
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
