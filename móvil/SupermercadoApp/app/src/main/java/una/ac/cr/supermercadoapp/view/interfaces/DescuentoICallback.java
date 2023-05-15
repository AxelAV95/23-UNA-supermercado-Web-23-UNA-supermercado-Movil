package una.ac.cr.supermercadoapp.view.interfaces;

import java.util.ArrayList;

import una.ac.cr.supermercadoapp.domain.Descuento;
import una.ac.cr.supermercadoapp.domain.TipoEmpleado;

public interface DescuentoICallback {

    void onDescuentoReceived(ArrayList<Descuento> listaDescuentos);
}
