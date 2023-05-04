package una.ac.cr.supermercadoapp.domain;

import java.io.Serializable;

public class Membresia implements Serializable {

    private int id;
    private String descripcion;

    public Membresia(int id, String descripcion) {
        this.id = id;
        this.descripcion = descripcion;
    }

    public Membresia() {
    }


}
