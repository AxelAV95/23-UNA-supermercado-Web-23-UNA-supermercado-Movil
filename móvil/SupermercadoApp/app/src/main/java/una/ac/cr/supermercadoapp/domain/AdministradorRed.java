package una.ac.cr.supermercadoapp.domain;

import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class AdministradorRed {
    private static AdministradorRed INSTANCIA;

    /*Permite que los componentes de la aplicación, como la actividad, observen
    los cambios en los datos de manera reactiva (es decir,  que responde rápidamente a las acciones del
    usuario o a los cambios en los datos) lo que significa que se actualizan automáticamente
    cada vez que se produce un cambio en los datos.*/
    private static final MutableLiveData<Boolean> estadoRedActivaMLD = new MutableLiveData<>();

    public AdministradorRed() {
    }

    /* syncronized garantiza que solo un hilo de ejecución pueda acceder al bloque de código o método
    en un momento dado, mientras que los demás hilos esperan su turno*/
    public static synchronized AdministradorRed getInstance() {
        if (INSTANCIA == null) {

            INSTANCIA = new AdministradorRed();
        }
        return INSTANCIA;
    }

    public void setEstadoConectividad(boolean estadoConectividad) {

        /*Un Looper permite que un hilo de ejecución (como el hilo principal de la aplicación)
        pueda recibir y procesar mensajes o eventos enviados por otros hilos o procesos.*/
        if (Looper.myLooper() == Looper.getMainLooper()) {
            /*Cuando se llama a setValue(), el objeto LiveData notifica automáticamente a todos
            los observadores registrados sobre el cambio y les proporciona el nuevo valor*/
            estadoRedActivaMLD.setValue(estadoConectividad);
        } else {
            /*se utiliza para actualizar el valor de LiveData desde un subproceso (hilo)
            que no es el hilo principal*/
            estadoRedActivaMLD.postValue(estadoConectividad);
        }
    }

    /*Un LiveData es como una "cámara" que transmite el estado de un dato, y que le permite
    * a los observadores(actividades) ver ese estado al mismo tiempo, entonces ante cualquier
    * cambio todos están al tanto*/
    public LiveData<Boolean> getEstadoConectividad() {
        return estadoRedActivaMLD;
    }
}
