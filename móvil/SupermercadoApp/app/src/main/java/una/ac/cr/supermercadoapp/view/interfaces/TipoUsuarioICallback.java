package una.ac.cr.supermercadoapp.view.interfaces;

import java.util.ArrayList;

import una.ac.cr.supermercadoapp.domain.TipoUsuario;

public interface TipoUsuarioICallback {
    void onTiposUsuarioReceived(ArrayList<TipoUsuario> listaTipoUsuarios);
}
