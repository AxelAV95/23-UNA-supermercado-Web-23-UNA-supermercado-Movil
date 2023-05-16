package una.ac.cr.supermercadoapp.view.interfaces;

import java.util.ArrayList;

import una.ac.cr.supermercadoapp.domain.Membresia;


public interface MembresiaICallback {
    void onMembresiasReceived(ArrayList<Membresia> listaMembresias);
}
