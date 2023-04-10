package una.ac.cr.supermercadoapp.domain;

import java.io.Serializable;

public class TipoEmpleado implements Serializable {
    private int id;
    private String descripcion;

    public TipoEmpleado() {
    }

    public TipoEmpleado(int id, String descripcion) {
        this.id = id;
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
