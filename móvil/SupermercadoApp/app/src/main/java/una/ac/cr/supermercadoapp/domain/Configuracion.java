package una.ac.cr.supermercadoapp.domain;

public class Configuracion {
    private int id;
    private String nombreSupermercado;
    private int telefono;
    private String correo;
    private String direccion;

    private String logoRuta;

    public Configuracion() {
    }

    public Configuracion(int id, String nombreSupermercado, int telefono, String correo, String direccion, String logoRuta) {
        this.id = id;
        this.nombreSupermercado = nombreSupermercado;
        this.telefono = telefono;
        this.correo = correo;
        this.direccion = direccion;
        this.logoRuta = logoRuta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreSupermercado() {
        return nombreSupermercado;
    }

    public void setNombreSupermercado(String nombreSupermercado) {
        this.nombreSupermercado = nombreSupermercado;
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



    public String getLogoRuta() {
        return logoRuta;
    }

    public void setLogoRuta(String logoRuta) {
        this.logoRuta = logoRuta;
    }
}
