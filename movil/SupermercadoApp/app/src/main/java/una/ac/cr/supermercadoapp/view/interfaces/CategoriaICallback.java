package una.ac.cr.supermercadoapp.view.interfaces;

import java.util.ArrayList;

import una.ac.cr.supermercadoapp.domain.Categoria;


public interface CategoriaICallback {
    void onCategoriaReceived(ArrayList<Categoria> listaCategorias);

}
