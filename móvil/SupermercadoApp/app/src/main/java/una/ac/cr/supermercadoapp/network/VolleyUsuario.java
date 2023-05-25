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

import com.android.volley.Network;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import java.util.ArrayList;

import at.favre.lib.crypto.bcrypt.BCrypt;
import es.dmoral.toasty.Toasty;
import una.ac.cr.supermercadoapp.data.BackupData;
import una.ac.cr.supermercadoapp.data.TipoUsuarioData;
import una.ac.cr.supermercadoapp.domain.Empleado;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.domain.Usuario;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.activities.MainActivity;
import una.ac.cr.supermercadoapp.view.activities.MenuEmpleadoActivity;
import una.ac.cr.supermercadoapp.view.interfaces.UsuarioICallback;

public class VolleyUsuario {

    private ArrayList<Usuario> listaUsuarios;

    //obtener usuarios
    public void obtenerUsuarios(Context context, String IP, UsuarioICallback callback){
        listaUsuarios = new ArrayList<>();

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, NetworkUtils.HTTP + IP + NetworkUtils.RUTA_USUARIO+"?metodo=obtener", null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{

                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        //Datos del usuario
                        int usuarioid = jsonObject.getInt("usuarioid");
                        String usuariopassword = jsonObject.getString("usuariopassword");

                        //Datos del empleado
                        int empleadoid = jsonObject.getInt("empleadoid");
                        int empleadocedula = jsonObject.getInt("empleadocedula");
                        String empleadonombre = jsonObject.getString("empleadonombre");
                        String empleadoapellidos = jsonObject.getString("empleadoapellidos");
                        int empleadotelefono = jsonObject.getInt("empleadotelefono");

                        //Datos del tipo usuario
                        int tipousuarioid = jsonObject.getInt("tipoid");
                        String tipodescripcion = jsonObject.getString("tipodescripcion");

                        Empleado empleado = new Empleado(empleadoid,empleadocedula,empleadonombre,empleadoapellidos,empleadotelefono);
                        TipoUsuario tipoUsuario = new TipoUsuario(tipousuarioid,tipodescripcion);


                        listaUsuarios.add(new Usuario(usuarioid,usuariopassword,empleado,tipoUsuario));
                    }

                }catch(JSONException e){
                    e.printStackTrace();
                }

                callback.onUsuarioReceived(listaUsuarios);
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

    public void verificarEmpleado(Context context, String IP, int empleadoid, Button boton){
        listaUsuarios = new ArrayList<>();

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, NetworkUtils.HTTP + IP + NetworkUtils.RUTA_USUARIO+"?metodo=verificarEmpleado&empleadoid="+empleadoid, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {


                if(response.optString("statusCode").toString().equals("409")){
                    Toasty.info(context, "El empleado elegido ya tiene\nun usuario vinculado", Toast.LENGTH_LONG, true).show();
                    boton.setEnabled(false);
                }else{
                    boton.setEnabled(true);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("anyText",error.toString());
                Toasty.error(context, error.toString(), Toast.LENGTH_LONG, true).show();
                Toasty.error(context, "Error al obtener los datos.", Toast.LENGTH_SHORT, true).show();
            }
        });

        VolleySingleton.getVolleySingleton(context).addToRequestQueue(jsonObjectRequest);
    }
    public void iniciarSesionQR(Context context, String cedula, String IP){
        JSONObject usuario = new JSONObject();
        try {
            usuario.put("metodo", "iniciarSesionQR");
            usuario.put("cedula",cedula);

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        //Parámetros: Método HTTP, URL del archivo php,objeto JSON a enviar, un response listener
        //Response listener: se utiliza para manejar la respuesta de una solicitud de red
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_USUARIO, usuario, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray datosUsuario = response.getJSONArray("response"); //permite obtener la respuesta del servidor
                    JSONObject jsonObject = datosUsuario.getJSONObject(0);

                    SharedPreferences credenciales = context.getSharedPreferences("credenciales", MODE_PRIVATE);
                    SharedPreferences.Editor editor = credenciales.edit();
                    editor.putString("cedula", jsonObject.get("empleadocedula").toString());
                    editor.putString("nombre", jsonObject.get("empleadonombre").toString());
                    editor.putString("apellidos", jsonObject.get("empleadoapellidos").toString());
                    editor.putString("tipo", jsonObject.get("tipodescripcion").toString());
                    editor.putString("password", jsonObject.get("usuariopassword").toString());
                    editor.apply();

                    if(jsonObject.get("tipodescripcion").toString().equals("Administrador")){
                        Intent intent = new Intent(context, MainActivity.class);
                        context.startActivity(intent);
                        ((Activity) context).finish();

                    }else if(jsonObject.get("tipodescripcion").toString().equals("Empleado")){
                        Intent intent = new Intent(context, MenuEmpleadoActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        ((Activity) context).finish();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("anyText",error.toString());
                Toasty.error(context, "Error al iniciar sesión, no se reconoce la cédula o contraseña.", Toast.LENGTH_SHORT, true).show();
            }
        });

        VolleySingleton.getVolleySingleton(context).addToRequestQueue(jsonObjectRequest);
    }

    public void iniciarSesionNormal(Context context, String cedula, String password, String IP, Button boton){

        JSONObject usuario = new JSONObject();

        try {
            usuario.put("metodo", "iniciarSesionNormal");
            usuario.put("cedula",cedula);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_USUARIO, usuario, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray datosUsuario = response.getJSONArray("response");
                    JSONObject jsonObject = datosUsuario.getJSONObject(0);

                    boolean resultado = BCrypt.verifyer().verify(password.toCharArray(), jsonObject.get("usuariopassword").toString()).verified;

                    if(resultado){
                        SharedPreferences credenciales = context.getSharedPreferences("credenciales", MODE_PRIVATE);
                        SharedPreferences.Editor editor = credenciales.edit();
                        editor.putString("cedula", jsonObject.get("empleadocedula").toString());
                        editor.putString("nombre", jsonObject.get("empleadonombre").toString());
                        editor.putString("apellidos", jsonObject.get("empleadoapellidos").toString());
                        editor.putString("tipo", jsonObject.get("tipodescripcion").toString());
                        editor.putString("password", jsonObject.get("usuariopassword").toString());
                        editor.apply();

                        if(jsonObject.get("tipodescripcion").toString().equals("Administrador")){
                            Intent intent = new Intent(context, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            ((Activity) context).finish();


                        }else if(jsonObject.get("tipodescripcion").toString().equals("Empleado")){
                            Intent intent = new Intent(context, MenuEmpleadoActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }

                    }else{
                        boton.setVisibility(View.VISIBLE);
                        Toasty.error(context, "Error al iniciar sesión, no se reconoce la cédula o contraseña.", Toast.LENGTH_SHORT, true).show();
                    }
                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("anyText",error.toString());
                boton.setVisibility(View.VISIBLE);
                Toasty.error(context, "Error al iniciar sesión, no se reconoce la cédula o contraseña.", Toast.LENGTH_SHORT, true).show();
            }
        });
        VolleySingleton.getVolleySingleton(context).addToRequestQueue(jsonObjectRequest);
    }

    //agregar un usuario
    public void insertarUsuario(Context context,Usuario usuario, String IP){
        JSONObject usuarioJson = new JSONObject();
        try {
            usuarioJson.put("metodo", "insertar");
            usuarioJson.put("password",usuario.getPassword());
            usuarioJson.put("empleadoid",usuario.getEmpleadoId());
            usuarioJson.put("tipoid",usuario.getUsuarioTipo());


        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_USUARIO,  usuarioJson , new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                TipoUsuarioData tipoUsuarioData = new TipoUsuarioData(context);
                if(response.optString("statusCode").toString().equals("200")){

                    JSONObject usuarioRespaldo = new JSONObject();
                    try {

                        usuarioRespaldo.put("password",usuario.getPassword());
                        usuarioRespaldo.put("empleadoid",usuario.getEmpleadoId());
                        usuarioRespaldo.put("tipoid",usuario.getUsuarioTipo());

                    } catch (JSONException e) {
                        throw new RuntimeException(e);
                    }

                    //Se llama el método
                    new BackupData().respaldarJson(usuarioRespaldo.toString(), context, "usuario.json");

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
    //modificar un usuario
    public void actualizarUsuario(Context context,Usuario usuario, String IP){

        JSONObject usuarioJson = new JSONObject();
        try {

            usuarioJson.put("usuarioid",usuario.getId());
            usuarioJson.put("usuariopassword",usuario.getPassword());
            usuarioJson.put("tipoid",usuario.getUsuarioTipo());

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.PUT, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_USUARIO,  usuarioJson , new Response.Listener<JSONObject>() {
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

    //eliminar un usuario
    public void eliminarUsuario(Context context,Usuario usuario, String IP){

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.DELETE, NetworkUtils.HTTP+IP+NetworkUtils.RUTA_USUARIO+"?id="+usuario.getId(),  null , new Response.Listener<JSONObject>() {
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
