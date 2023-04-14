package una.ac.cr.supermercadoapp.network;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
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


import at.favre.lib.crypto.bcrypt.BCrypt;
import una.ac.cr.supermercadoapp.utils.NetworkUtils;
import una.ac.cr.supermercadoapp.view.activities.MainActivity;
import una.ac.cr.supermercadoapp.view.activities.MenuEmpleadoActivity;

public class VolleyUsuario {

    private RequestQueue queue;
    private JsonArrayRequest jsonArrayRequest;
    private JsonObjectRequest jsonObjectRequest;
    private StringRequest mStringRequest;

    public void iniciarSesionQR(Context context, String cedula, String IP){
        queue = Volley.newRequestQueue(context);
        JSONObject usuario = new JSONObject();

        try {

            usuario.put("metodo", "iniciarSesionQR");
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
                   // Intent intent = new Intent(context, MenuEmpleadoActivity.class);
                    //context.startActivity(intent);

//                    boolean result = BCrypt.verifyer().verify("12345".toCharArray(), jsonObject.get("usuariopassword").toString()).verified;




                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("anyText",error.toString());
                Toast.makeText(context,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        queue.add( jsonObjectRequest  );
    }

    public void iniciarSesionNormal(Context context, String cedula, String password, String IP){
        queue = Volley.newRequestQueue(context);
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
                        Toast.makeText(context,"!ok",Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(context,"Error al iniciar sesión",Toast.LENGTH_SHORT).show();
                    }

                    /*
                    if(resultado){

                        Toast.makeText(context,"Error al iniciar sesión",Toast.LENGTH_SHORT).show();
                    }else{
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
                            context.startActivity(intent);
                            ((Activity) context).finish();
                        }
                    }*/

                    // Intent intent = new Intent(context, MenuEmpleadoActivity.class);
                    //context.startActivity(intent);

//                    boolean result = BCrypt.verifyer().verify("12345".toCharArray(), jsonObject.get("usuariopassword").toString()).verified;




                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("anyText",error.toString());

                Toast.makeText(context,error.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
            }
        });



        queue.add( jsonObjectRequest  );
    }




}
