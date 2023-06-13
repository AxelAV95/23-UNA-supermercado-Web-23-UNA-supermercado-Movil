package una.ac.cr.supermercadoapp.domain;

import java.io.Serializable;

public class Producto implements Serializable {

    private int id;
    private String nombre;
    private double precio;
    private String fechaIngreso;
    private int stock;
    private int estado;
    private int categoriaid;
    private int proveedorid;

    public String getProveedorNombre() {
        return proveedorNombre;
    }

    public Producto(int id, String nombre, double precio, String fechaIngreso, int stock, int estado, String proveedorNombre, String categoriaNombre) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaIngreso = fechaIngreso;
        this.stock = stock;
        this.estado = estado;
        this.proveedorNombre = proveedorNombre;
        this.categoriaNombre = categoriaNombre;
    }

    public void setProveedorNombre(String proveedorNombre) {
        this.proveedorNombre = proveedorNombre;
    }

    public String getCategoriaNombre() {
        return categoriaNombre;
    }

    public void setCategoriaNombre(String categoriaNombre) {
        this.categoriaNombre = categoriaNombre;
    }

    private String proveedorNombre, categoriaNombre;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Proveedor getProveedor() {
        return proveedor;
    }

    public void setProveedor(Proveedor proveedor) {
        this.proveedor = proveedor;
    }

    private Categoria categoria;
    private Proveedor proveedor;
    public Producto()  {
    }

    public Producto(String nombre, double precio, String fechaIngreso, int stock, int estado, int categoriaid, int proveedorid) {
        this.nombre = nombre;
        this.precio = precio;
        this.fechaIngreso = fechaIngreso;
        this.stock = stock;
        this.estado = estado;
        this.categoriaid = categoriaid;
        this.proveedorid = proveedorid;
    }

    public Producto(int id, String nombre, double precio, String fechaIngreso, int stock, int estado, int categoriaid, int proveedorid, Categoria categoria, Proveedor proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaIngreso = fechaIngreso;
        this.stock = stock;
        this.estado = estado;
        this.categoriaid = categoriaid;
        this.proveedorid = proveedorid;
        this.categoria = categoria;
        this.proveedor = proveedor;
    }

    public Producto(int id, String nombre, double precio, String fechaIngreso, int stock, int estado, int categoriaid, int proveedorid, Proveedor proveedor) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaIngreso = fechaIngreso;
        this.stock = stock;
        this.estado = estado;
        this.categoriaid = categoriaid;
        this.proveedorid = proveedorid;
        this.proveedor = proveedor;
    }

    public Producto(int id, String nombre, double precio, String fechaIngreso, int stock, int estado, int categoriaid, int proveedorid, Categoria categoria) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.fechaIngreso = fechaIngreso;
        this.stock = stock;
        this.estado = estado;
        this.categoriaid = categoriaid;
        this.proveedorid = proveedorid;
        this.categoria = categoria;
    }

    public Producto(int id, String nombre, double precio, String fechaIngreso, int stock, int estado, int categoriaid, int proveedorid) {
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
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
