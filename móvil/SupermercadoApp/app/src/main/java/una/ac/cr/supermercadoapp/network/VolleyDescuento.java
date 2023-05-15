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
import una.ac.cr.supermercadoapp.data.DescuentoData;
import una.ac.cr.supermercadoapp.data.TipoEmpleadoData;
import una.ac.cr.supermercadoapp.domain.Categoria;
import una.ac.cr.supermercadoapp.domain.Descuento;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.interfaces.DescuentoICallback;
import una.ac.cr.supermercadoapp.view.interfaces.TipoEmpleadoICallback;

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



    public void insertarDescuento(Context context, Descuento descuento, String IP){
        JSONObject descuentoJson = new JSONObject();
        try {
            descuentoJson.put("metodo", "insertar");
            descuentoJson.put("descuentotarifa",descuento.getTarifa());
            descuentoJson.put("descuentomembresiaid",descuento.getMembresiaid());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_DESCUENTO,  descuentoJson , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                DescuentoData descuentoData = new DescuentoData(context);
                if(response.optString("statusCode").toString().equals("200")){


                    long resultado = descuentoData.insertarDescuento(new Descuento(descuento.getTarifa(),descuento.getMembresiaid()));
                    if(resultado == -1){
                        Toasty.error(context, "Error al insertar en la base de datos local", Toast.LENGTH_SHORT, true).show();
                    }else{
                        Toasty.success(context, "Se registró con éxito", Toast.LENGTH_SHORT, true).show();
                        Intent returnIntent = new Intent();
                        ((Activity) context).setResult(Activity.RESULT_OK, returnIntent);
                        ((Activity) context).finish();
                    }

                }else{
                    long resultado = descuentoData.insertarDescuento(new Descuento(descuento.getTarifa(),descuento.getMembresiaid()));
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



    public void actualizarDescuento(Context context, Descuento descuento, String IP){
        JSONObject descuentoJson = new JSONObject();
        try {
            descuentoJson.put("metodo", "insertar");
            descuentoJson.put("descuentoid",descuento.getId());
            descuentoJson.put("descuentotarifa",descuento.getTarifa());
            descuentoJson.put("descuentomembresiaid",descuento.getMembresiaid());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_DESCUENTO,  descuentoJson, new Response.Listener<JSONObject>() {
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



    public void eliminarDescuento(Context context,Descuento descuento, String IP){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_DESCUENTO+"?id="+descuento.getId(),  null , new Response.Listener<JSONObject>() {
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


