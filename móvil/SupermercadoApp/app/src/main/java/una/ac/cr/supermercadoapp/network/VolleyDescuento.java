package una.ac.cr.supermercadoapp.network;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
import java.util.Date;

import at.favre.lib.crypto.bcrypt.BCrypt;
import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.data.BackupData;
import una.ac.cr.supermercadoapp.data.DescuentoData;
import una.ac.cr.supermercadoapp.data.ProductoData;
import una.ac.cr.supermercadoapp.domain.Categoria;
import una.ac.cr.supermercadoapp.domain.Descuento;
import una.ac.cr.supermercadoapp.domain.Producto;
import una.ac.cr.supermercadoapp.domain.Proveedor;

import una.ac.cr.supermercadoapp.utils.NetworkUtils;

import una.ac.cr.supermercadoapp.view.interfaces.DescuentoICallback;
import una.ac.cr.supermercadoapp.view.interfaces.ProductoICallback;

public class VolleyDescuento {
    private ArrayList<Descuento> listaDescuentos;

    public void  obtenerDescuentos(Context context, String IP, DescuentoICallback callback){
        listaDescuentos = new ArrayList<>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_DESCUENTO, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int descuentoid = Integer.parseInt(jsonObject.getString("descuentoid"));
                        float descuentotarifa= Float.parseFloat(jsonObject.getString("descuentotarifa"));
                        int descuentomembresiaid= Integer.parseInt(jsonObject.getString("descuentomembresiaid"));
                        listaDescuentos.add(new Descuento(descuentoid, descuentotarifa,descuentomembresiaid));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                callback.onDescuentoReceived(listaDescuentos);

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




    //agregar un producto
    public void insertarDescuento(Context context,Descuento descuento, String IP){
        JSONObject descuentoJson = new JSONObject();
        try {
            descuentoJson.put("metodo", "insertar");
            descuentoJson.put("tarifa",descuento.getTarifa());
            descuentoJson.put("membresiaid",descuento.getMembresiaid());


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, una.ac.cr.supermercadoapp.utils.NetworkUtils.HTTP+IP+ NetworkUtils.RUTA_DESCUENTO,  descuentoJson , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                DescuentoData descuentoData = new DescuentoData(context);
                if(response.optString("statusCode").toString().equals("200")){

                    JSONObject descuentoRespaldo = new JSONObject();
                    try {

                        descuentoRespaldo.put("tarifa",descuento.getTarifa());
                        descuentoRespaldo.put("membresiaid",descuento.getMembresiaid());

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    //Se llama el método
                    new BackupData().respaldarJson(descuentoRespaldo.toString(), context, "descuento.json");

                    Intent returnIntent = new Intent();
                    ((Activity) context).setResult(Activity.RESULT_OK, returnIntent);
                    ((Activity) context).finish();

                }else{
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
    //modificar un producto
    public void actualizarDescuento(Context context,Descuento descuento, String IP){

        JSONObject descuentoJson = new JSONObject();
        try {
            descuentoJson.put("id",descuento.getId());
            descuentoJson.put("tarifa",descuento.getTarifa());
            descuentoJson.put("membresiaid",descuento.getMembresiaid());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, una.ac.cr.supermercadoapp.utils.NetworkUtils.HTTP+IP+ NetworkUtils.RUTA_DESCUENTO,  descuentoJson , new Response.Listener<JSONObject>() {
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

    //eliminar un producto
    public void eliminarDescuento(Context context,Descuento descuento, String IP){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, una.ac.cr.supermercadoapp.utils.NetworkUtils.HTTP+IP+ NetworkUtils.RUTA_DESCUENTO+"?id="+descuento.getId(),  null , new Response.Listener<JSONObject>() {
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

