package una.ac.cr.supermercadoapp.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.net.NetworkRequest;

import androidx.annotation.NonNull;

import una.ac.cr.supermercadoapp.domain.AdministradorRed;

public class MonitorRedUtils extends ConnectivityManager.NetworkCallback{

    private final NetworkRequest solicitudRed;
    private final ConnectivityManager administradorConectividad;
    private final AdministradorRed administradorRed;

    public MonitorRedUtils (Context context) {
        solicitudRed = new NetworkRequest.Builder()
                .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
                .addTransportType(NetworkCapabilities.TRANSPORT_ETHERNET)
                .build();

        administradorConectividad = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        administradorRed = AdministradorRed.getInstance();
    }

    @Override
    public void onAvailable(@NonNull Network network) {
        super.onAvailable(network);
        administradorRed.setEstadoConectividad(true);
    }

    @Override
    public void onLost(@NonNull Network network) {
        super.onLost(network);
        administradorRed.setEstadoConectividad(false);
    }

    public void registrarEventosCallbackRed() {
        administradorConectividad.registerNetworkCallback(solicitudRed, this);
    }

    public void verificarEstadoRed() {
        try {
            Network network = administradorConectividad.getActiveNetwork();
            NetworkCapabilities características = administradorConectividad.getNetworkCapabilities(network);
            administradorRed.setEstadoConectividad(características != null
                    && características.hasCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
                    && características.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

}
