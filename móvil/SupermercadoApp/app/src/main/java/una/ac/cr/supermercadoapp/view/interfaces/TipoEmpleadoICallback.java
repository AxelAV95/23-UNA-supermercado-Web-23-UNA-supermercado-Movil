package una.ac.cr.supermercadoapp.view.interfaces;

import java.util.ArrayList;

import una.ac.cr.supermercadoapp.domain.TipoEmpleado;
import una.ac.cr.supermercadoapp.domain.TipoUsuario;

public interface TipoEmpleadoICallback {
    void onTiposEmpeladoReceived(ArrayList<TipoEmpleado> listaTipoEmpleado);
}
