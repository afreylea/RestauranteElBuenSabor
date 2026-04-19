package com.mycompany.restauranteelbuensabor;

/**
 * Representa un producto dentro del pedido con su cantidad solicitada.
 * Encapsula la relación Producto + cantidad y sabe calcular su propio subtotal.
 */
public class Item {

    private final Producto producto;
    private int cantidad;

    public Item(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Producto getProducto() { return producto; }
    public int      getCantidad() { return cantidad; }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    /** Precio unitario × cantidad pedida. */
    public double calcularSubtotal() {
        return producto.getPrecio() * cantidad;
    }
}