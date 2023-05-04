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
import una.ac.cr.supermercadoapp.data.CategoriaData;
import una.ac.cr.supermercadoapp.domain.Categoria;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.interfaces.CategoriaICallback;

public class VolleyCategoria {

    private ArrayList<Categoria> listaCategorias;
    public void  obtenerTipos(Context context, String IP, CategoriaICallback callback){
        listaCategorias = new ArrayList<>();


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_CATEGORIA, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String categoriaid = jsonObject.getString("categoriaid");
                        String categorianombre = jsonObject.getString("categorianombre");
                        listaCategorias.add(new Categoria(Integer.parseInt(categoriaid), categorianombre));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                callback.onCategoriaReceived(listaCategorias);

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

    public void insertarCategoria(Context context,Categoria categoria, String IP){
        JSONObject categoriaJson = new JSONObject();
        try {
            categoriaJson.put("metodo", "insertar");
            categoriaJson.put("nombre",categoria.getNombre());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_CATEGORIA,  categoriaJson , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                CategoriaData categoriaDataData = new CategoriaData(context);
                if(response.optString("statusCode").toString().equals("200")){


                    long resultado = categoriaDataData.insertarCategoria(new Categoria(categoria.getNombre(),0));
                    if(resultado == -1){
                        Toasty.error(context, "Error al insertar en la base de datos local", Toast.LENGTH_SHORT, true).show();
                    }else{
                        Intent returnIntent = new Intent();
                        ((Activity) context).setResult(Activity.RESULT_OK, returnIntent);
                        ((Activity) context).finish();
                    }

                }else{
                    long resultado = categoriaDataData.insertarCategoria(new Categoria(categoria.getNombre(),-1));
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

    public void actualizarCategoria(Context context,Categoria categoria, String IP){
        JSONObject categoriaJson = new JSONObject();
        try {
            categoriaJson.put("metodo", "insertar");
            categoriaJson.put("id",categoria.getId());
            categoriaJson.put("nombre",categoria.getNombre());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_CATEGORIA,  categoriaJson , new Response.Listener<JSONObject>() {
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

    public void eliminarCategoria(Context context,Categoria categoria, String IP){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_CATEGORIA+"?id="+categoria.getId(),  null , new Response.Listener<JSONObject>() {
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
