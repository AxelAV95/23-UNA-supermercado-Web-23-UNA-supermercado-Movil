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
import una.ac.cr.supermercadoapp.data.TipoEmpleadoData;
import una.ac.cr.supermercadoapp.domain.Categoria;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.interfaces.TipoEmpleadoICallback;

public class VolleyTipoEmpleado {
    private ArrayList<TipoEmpleado> listaTipoEmpelados;

    public void  obtenerTipos(Context context, String IP, TipoEmpleadoICallback callback){
        listaTipoEmpelados = new ArrayList<>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_TIPO_EMPLEADO, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        int tipoempleadoid = Integer.parseInt(jsonObject.getString("tipoid"));
                        String tipodescripcion= jsonObject.getString("tipodescripcion");
                        listaTipoEmpelados.add(new TipoEmpleado(tipoempleadoid, tipodescripcion));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                callback.onTiposEmpeladoReceived(listaTipoEmpelados);

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



    public void insertarTipoEmpleado(Context context, TipoEmpleado tipoEmpleado, String IP){
        JSONObject tipoEmpleadoJson = new JSONObject();
        try {
            tipoEmpleadoJson.put("metodo", "insertar");
            tipoEmpleadoJson.put("descripcion",tipoEmpleado.getTipoEmpleadoDescripcion());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_TIPO_EMPLEADO,  tipoEmpleadoJson , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TipoEmpleadoData tipoEmpleadoData = new TipoEmpleadoData(context);
                if(response.optString("statusCode").toString().equals("200")){


                    long resultado = tipoEmpleadoData.insertarTipoEmpleado(new TipoEmpleado(tipoEmpleado.getTipoEmpleadoDescripcion()));
                    if(resultado == -1){
                        Toasty.error(context, "Error al insertar en la base de datos local", Toast.LENGTH_SHORT, true).show();
                    }else{
                        Toasty.success(context, "Se registró con éxito", Toast.LENGTH_SHORT, true).show();
                        Intent returnIntent = new Intent();
                        ((Activity) context).setResult(Activity.RESULT_OK, returnIntent);
                        ((Activity) context).finish();
                    }

                }else{
                    long resultado = tipoEmpleadoData.insertarTipoEmpleado(new TipoEmpleado(tipoEmpleado.getTipoEmpleadoDescripcion()));
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



    public void actualizarTipoEmpleado(Context context, TipoEmpleado tipoEmpleado, String IP){
        JSONObject tipoEmpleadoJson = new JSONObject();
        try {
            tipoEmpleadoJson.put("metodo", "insertar");
            tipoEmpleadoJson.put("tipoid",tipoEmpleado.getId());
            tipoEmpleadoJson.put("tipodescripcion",tipoEmpleado.getTipoEmpleadoDescripcion());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_TIPO_EMPLEADO,  tipoEmpleadoJson, new Response.Listener<JSONObject>() {
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



    public void eliminarTipoEmpleado(Context context,TipoEmpleado tipoEmpleado, String IP){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_TIPO_EMPLEADO+"?id="+tipoEmpleado.getId(),  null , new Response.Listener<JSONObject>() {
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
