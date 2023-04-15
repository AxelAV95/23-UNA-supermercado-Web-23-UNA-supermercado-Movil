package una.ac.cr.supermercadoapp.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {

    private static VolleySingleton sVolleySingleton;
    private RequestQueue queue; //Se encarga de administrar la cola de solicitudes, enviarlas al servidor y procesar las respuestas recibidas
    private JsonArrayRequest jsonArrayRequest; //Se utiliza para solicitar datos en formato JSON en forma de matriz (array)
    private JsonObjectRequest jsonObjectRequest; //Se utiliza para enviar una solicitud de objeto JSON a través de una URL y recibir una respuesta JSON en forma de objeto JSON.
    private StringRequest mStringRequest;
    private static Context context;

    private VolleySingleton(Context ctx) {
        context = ctx;
        queue = getRequestQueue();
    }

    private RequestQueue getRequestQueue() {
        if(queue == null){
            queue = Volley.newRequestQueue(context.getApplicationContext());
        }
        return queue;
    }

    public static synchronized VolleySingleton getVolleySingleton(Context context) {

        if(sVolleySingleton == null){
            sVolleySingleton = new VolleySingleton(context);
        }
        return sVolleySingleton;
    }

    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

}
