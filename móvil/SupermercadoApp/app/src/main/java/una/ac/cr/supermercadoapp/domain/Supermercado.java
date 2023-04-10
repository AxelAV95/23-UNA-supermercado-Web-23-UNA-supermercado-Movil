package una.ac.cr.supermercadoapp.domain;

import java.io.Serializable;

public class Supermercado implements Serializable {
    private int id;
    private String nombre;
    private int telefono;
    private String correo;
    private String direccion;
    private String rutaLogo;

    public Supermercado() {
    }

    public Supermercado(int id, String nombre, int telefono, String correo, String direccion, String rutaLogo) {
        this.id = id;
        this.nombre = nombre;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.rutaLogo = rutaLogo;
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

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getRutaLogo() {
        return rutaLogo;
    }

    public void setRutaLogo(String rutaLogo) {
        this.rutaLogo = rutaLogo;
    }
}
