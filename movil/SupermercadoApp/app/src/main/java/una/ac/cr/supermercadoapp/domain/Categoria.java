package una.ac.cr.supermercadoapp.domain;

import java.io.Serializable;

public class Categoria implements Serializable {

    private int id;
    private String nombre;
    private int estadosync;

    public int getEstadosync() {
        return estadosync;
    }
    public void setEstadosync(int estadosync) {
        this.estadosync = estadosync;
    }

    public Categoria(String nombre) {
        this.nombre = nombre;
    }

    public Categoria(String nombre, int estadosync) {
        this.nombre = nombre;
        this.estadosync = estadosync;
    }

    public Categoria(int id, String nombre, int estadosync) {
        this.id = id;
        this.nombre = nombre;
        this.estadosync = estadosync;
    }

    public Categoria() {
    }

    public Categoria(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
