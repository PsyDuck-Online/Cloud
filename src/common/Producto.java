package common;

import java.io.File;
import java.io.Serializable;

/**
 *
 * @author david
 */
public class Producto implements Serializable {

    private static int numProductos = 0;
    private int id;
    private int cantidad;
    private String nombre;
    private float precio;
    private File img;

    public Producto(Producto p) {
        this.id = p.getId();
        this.cantidad = 1;
        this.nombre = p.getNombre();
        this.precio = p.getPrecio();
        this.img = p.getImg();
    }

    public Producto(String nombre, float precio, int cantidad, File img) {
        numProductos++;
        this.id = numProductos;
        this.cantidad = cantidad;
        this.nombre = nombre;
        this.precio = precio;
        this.img = img;
    }

    public void agregar(int cantidad) {
        this.cantidad += cantidad;
    }

    public void disminuir(int cantidad) {
        this.cantidad -= cantidad;
    }

    public Object[] getObjectString() {
        return new Object[]{id, nombre, precio, cantidad, img.getAbsolutePath()};
    }

    // Getters
    public static int getNumProductos() {
        return numProductos;
    }

    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public String getNombre() {
        return nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public File getImg() {
        return img;
    }

    // Setters
    public static void setNumProductos(int numProductos) {
        Producto.numProductos = numProductos;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public void setImg(File img) {
        this.img = img;
    }

}
