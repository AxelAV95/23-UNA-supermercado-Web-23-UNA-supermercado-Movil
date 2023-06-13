package una.ac.cr.supermercadoapp.view.interfaces;

import java.util.ArrayList;

import una.ac.cr.supermercadoapp.domain.Cliente;



public interface ClienteICallback {
    void onClientesReceived(ArrayList<Cliente> listaClientes);
}
