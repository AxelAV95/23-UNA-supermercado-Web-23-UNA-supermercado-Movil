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
import una.ac.cr.supermercadoapp.data.ProductoData;
import una.ac.cr.supermercadoapp.domain.Categoria;
import una.ac.cr.supermercadoapp.domain.Producto;
import una.ac.cr.supermercadoapp.domain.Proveedor;

import una.ac.cr.supermercadoapp.utils.NetworkUtils;

import una.ac.cr.supermercadoapp.view.interfaces.ProductoICallback;

public class VolleyProducto {
    private ArrayList<Producto> listaProductos;

    //obtener productos
    public void obtenerProductos(Context context, String IP, ProductoICallback callback){
        listaProductos = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, una.ac.cr.supermercadoapp.utils.NetworkUtils.HTTP + IP + una.ac.cr.supermercadoapp.utils.NetworkUtils.RUTA_PRODUCTO+"?metodo=obtener", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        //Datos del producto
                        int productoid  = jsonObject.getInt("productoid");
                        String productonombre = jsonObject.getString("productonombre");
                        double productoprecio  = jsonObject.getDouble("productoprecio");
                        String productofechaingreso  = jsonObject.getString("productofechaingreso");
                        int productostock  = jsonObject.getInt("productostock");
                        int productoestado  = jsonObject.getInt("productoestado");

                        String categoriaid  = jsonObject.getString("categorianombre");
                        String proveedorid = jsonObject.getString("proveedornombre");



                        listaProductos.add(new Producto(productoid,productonombre,
                                productoprecio,productofechaingreso,productostock,productoestado
                                ,categoriaid,proveedorid));
                    }

                }catch(JSONException e){
                    e.printStackTrace();
                }

                callback.onProductoReceived(listaProductos);
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
    public void insertarProducto(Context context,Producto producto, String IP){
        JSONObject productoJson = new JSONObject();
        try {
            productoJson.put("metodo", "insertar");
            productoJson.put("nombre",producto.getNombre());
            productoJson.put("precio",producto.getPrecio());
            productoJson.put("estado",producto.getEstado());
            productoJson.put("fechaIngreso",producto.getFechaIngreso());
            productoJson.put("stock",producto.getStock());
            productoJson.put("categoria",producto.getCategoriaid());
            productoJson.put("productoproveedor",producto.getProveedorid());


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, una.ac.cr.supermercadoapp.utils.NetworkUtils.HTTP+IP+ NetworkUtils.RUTA_PRODUCTO,  productoJson , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                ProductoData productoData = new ProductoData(context);
                if(response.optString("statusCode").toString().equals("200")){

                    JSONObject productoRespaldo = new JSONObject();
                    try {

                        productoRespaldo.put("nombre",producto.getNombre());
                        productoRespaldo.put("precio",producto.getPrecio());
                        productoRespaldo.put("estado",producto.getEstado());
                        productoRespaldo.put("fechaIngreso",producto.getFechaIngreso());
                        productoRespaldo.put("stock",producto.getStock());
                        productoRespaldo.put("categoria",producto.getCategoriaid());
                        productoRespaldo.put("productoproveedor",producto.getProveedorid());

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    //Se llama el método
                    new BackupData().respaldarJson(productoRespaldo.toString(), context, "producto.json");

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
    public void actualizarProducto(Context context,Producto producto, String IP){

        JSONObject productoJson = new JSONObject();
        try {

            productoJson.put("productoid",producto.getId());
            productoJson.put("nombre",producto.getNombre());
            productoJson.put("precio",producto.getPrecio());
            productoJson.put("estado",producto.getEstado());
            productoJson.put("fechaIngreso",producto.getFechaIngreso());
            productoJson.put("stock",producto.getStock());
            productoJson.put("categoria",producto.getCategoriaid());
            productoJson.put("productoproveedor",producto.getProveedorid());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, una.ac.cr.supermercadoapp.utils.NetworkUtils.HTTP+IP+ NetworkUtils.RUTA_PRODUCTO,  productoJson , new Response.Listener<JSONObject>() {
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
    public void eliminarProducto(Context context,Producto producto, String IP){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, una.ac.cr.supermercadoapp.utils.NetworkUtils.HTTP+IP+ NetworkUtils.RUTA_PRODUCTO+"?id="+producto.getId(),  null , new Response.Listener<JSONObject>() {
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
