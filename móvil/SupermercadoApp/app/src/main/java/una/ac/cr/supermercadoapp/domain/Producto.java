package una.ac.cr.supermercadoapp.domain;

import java.io.Serializable;

public class Producto implements Serializable {

    private int id;
    private String nombre;
    private float precio;
    private String fechaIngreso;
    private int stock;
    private int estado;
    private int categoriaid;
    private int proveedorid;

    public Producto()  {
    }

    public Producto(int id, String nombre, float precio, String fechaIngreso, int stock, int estado, int categoriaid, int proveedorid) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaIngreso = fechaIngreso;
        this.stock = stock;
        this.estado = estado;
        this.categoriaid = categoriaid;
        this.proveedorid = proveedorid;
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

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public String getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(String fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCategoriaid() {
        return categoriaid;
    }

    public void setCategoriaid(int categoriaid) {
        this.categoriaid = categoriaid;
    }

    public int getProveedorid() {
        return proveedorid;
    }

    public void setProveedorid(int proveedorid) {
        this.proveedorid = proveedorid;
    }
}
