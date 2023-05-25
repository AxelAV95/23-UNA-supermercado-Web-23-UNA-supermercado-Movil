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
import una.ac.cr.supermercadoapp.domain.Configuracion;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.interfaces.ConfiguracionICallback;
import una.ac.cr.supermercadoapp.view.interfaces.TipoUsuarioICallback;

public class VolleyConfiguracion {

    private Configuracion configuracion;

    public void  obtenerConfiguracion(Context context, String IP, ConfiguracionICallback callback){
       // listaTipoUsuarios = new ArrayList<>();

        configuracion = new Configuracion();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, NetworkUtils.HTTP+IP+ NetworkUtils.RUTA_CONFIGURACION, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int configuracionid = jsonObject.getInt("supermercadoid");
                        String supermercadonombre = jsonObject.getString("supermercadonombre");
                        int supermercadotelefono = jsonObject.getInt("supermercadotelefono");
                        String supermercadocorreo = jsonObject.getString("supermercadocorreo");
                        String supermercadodireccion = jsonObject.getString("supermercadodireccion");
                        String supermercadologo = jsonObject.getString("supermercadologo");

                        configuracion.setId(configuracionid);
                        configuracion.setNombreSupermercado(supermercadonombre);
                        configuracion.setCorreo(supermercadocorreo);
                        configuracion.setTelefono(supermercadotelefono);
                        configuracion.setDireccion(supermercadodireccion);
                        configuracion.setLogoRuta(supermercadologo);




                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                callback.onConfiguracionReceived(configuracion);

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


    public void actualizarUsuario(Context context,Configuracion configuracion, String IP){
        JSONObject configuracionJson = new JSONObject();
        try {
            configuracionJson.put("id",configuracion.getId());
            configuracionJson.put("nombre", configuracion.getNombreSupermercado());
            configuracionJson.put("telefono", configuracion.getTelefono());
            configuracionJson.put("correo",configuracion.getCorreo());
            configuracionJson.put("logo", configuracion.getLogoRuta());
            configuracionJson.put("direccion", configuracion.getDireccion());


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_CONFIGURACION,  configuracionJson , new Response.Listener<JSONObject>() {
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
}
