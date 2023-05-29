package una.ac.cr.supermercadoapp.view.interfaces;

import java.util.ArrayList;

import una.ac.cr.supermercadoapp.domain.Producto;

public interface ProductoICallback {
    void onProductoReceived(ArrayList<Producto> listaProductos);

}
