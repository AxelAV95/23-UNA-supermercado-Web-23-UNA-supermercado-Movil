package una.ac.cr.supermercadoapp.domain;

import java.io.Serializable;

public class Cliente implements Serializable {

    private int id;
    private String nombre;
    private String apellidos;
    private int cedula;
    private String direccion;
    private int telefono;
    private String correo;
    private String fechaAfiliacion;
    private int tipoMembresia;

    public Cliente() {
    }

    public Cliente(int id, String nombre, String apellidos, int cedula, String direccion, int telefono, String correo, String fechaAfiliacion, int tipoMembresia) {
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
        this.fechaAfiliacion = fechaAfiliacion;
        this.tipoMembresia = tipoMembresia;
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

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
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

    public String getFechaAfiliacion() {
        return fechaAfiliacion;
    }

    public void setFechaAfiliacion(String fechaAfiliacion) {
        this.fechaAfiliacion = fechaAfiliacion;
    }

    public int getTipoMembresia() {
        return tipoMembresia;
    }

    public void setTipoMembresia(int tipoMembresia) {
        this.tipoMembresia = tipoMembresia;
    }
}
