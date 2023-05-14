package una.ac.cr.supermercadoapp.view.interfaces;

import java.util.ArrayList;

import una.ac.cr.supermercadoapp.domain.Categoria;
import una.ac.cr.supermercadoapp.domain.Proveedor;

public interface ProveedorICallback {

    void onProveedorReceived(ArrayList<Proveedor> listaProveedores);
}
