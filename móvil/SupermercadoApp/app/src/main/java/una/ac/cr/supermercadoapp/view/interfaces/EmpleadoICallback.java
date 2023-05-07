package una.ac.cr.supermercadoapp.view.interfaces;

import java.util.ArrayList;

import una.ac.cr.supermercadoapp.domain.Empleado;


public interface EmpleadoICallback {
    void onEmpleadoReceived(ArrayList<Empleado> lista);
}
