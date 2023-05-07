package una.ac.cr.supermercadoapp.domain;

import java.io.Serializable;

public class Usuario implements Serializable {
    private int id;
    private String password;
    private int empleadoId;
    private Empleado empleado;
    private TipoUsuario tipoUsuario;
    private int usuarioTipo;

    public Usuario() {
    }

    public Usuario(int id, String password, int empleadoId, int usuarioTipo) {
        this.id = id;
        this.password = password;
        this.empleadoId = empleadoId;
        this.usuarioTipo = usuarioTipo;
    }

    public Usuario(String password, int empleadoId, int tipoUsuario) {
        this.password = password;
        this.empleadoId = empleadoId;
        this.usuarioTipo= tipoUsuario;
    }



    public Usuario(String password, Empleado empleado, TipoUsuario tipoUsuario) {
        this.password = password;
        this.empleado = empleado;
        this.tipoUsuario = tipoUsuario;
    }

    public Usuario(int id, String password, Empleado empleado, TipoUsuario tipoUsuario) {
        this.id = id;
        this.password = password;
        this.empleado = empleado;
        this.tipoUsuario = tipoUsuario;
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



    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public TipoUsuario getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(TipoUsuario tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }
}
