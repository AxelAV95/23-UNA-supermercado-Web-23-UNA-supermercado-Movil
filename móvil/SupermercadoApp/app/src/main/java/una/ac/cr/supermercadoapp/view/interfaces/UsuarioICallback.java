package una.ac.cr.supermercadoapp.view.interfaces;

import java.util.ArrayList;

import una.ac.cr.supermercadoapp.domain.TipoUsuario;
import una.ac.cr.supermercadoapp.domain.Usuario;

public interface UsuarioICallback {
    void onUsuarioReceived(ArrayList<Usuario> listaUsuarios);
}
