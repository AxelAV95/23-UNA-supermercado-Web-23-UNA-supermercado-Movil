package una.ac.cr.supermercadoapp.domain;

import java.io.Serializable;

public class TipoUsuario implements Serializable {

    private int id;
    private String descripcion;

    public TipoUsuario(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public TipoUsuario() {
    }

    public TipoUsuario(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
