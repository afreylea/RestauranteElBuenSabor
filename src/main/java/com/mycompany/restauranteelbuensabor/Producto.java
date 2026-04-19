package com.mycompany.restauranteelbuensabor;

/**
 * Representa un producto de la carta con nombre y precio fijos.
 * Es inmutable: una vez creado no puede cambiar.
 */
public class Producto {

    private final String nombre;
    private final double precio;

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() { return nombre; }
    public double getPrecio() { return precio; }
}