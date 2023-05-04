package una.ac.cr.supermercadoapp.domain;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id;
    private String password;
    private int empleadoId;
    private int usuarioTipo;

    public Usuario() {
    }

    public Usuario(int id, String password, int empleadoId, int usuarioTipo) {
        this.id = id;
        this.password = password;
        this.empleadoId = empleadoId;
        this.usuarioTipo = usuarioTipo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getEmpleadoId() {
        return empleadoId;
    }

    public void setEmpleadoId(int empleadoId) {
        this.empleadoId = empleadoId;
    }

    public int getUsuarioTipo() {
        return usuarioTipo;
    }

    public void setUsuarioTipo(int usuarioTipo) {
        this.usuarioTipo = usuarioTipo;
    }
}
