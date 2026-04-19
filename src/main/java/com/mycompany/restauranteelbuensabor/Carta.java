package com.mycompany.restauranteelbuensabor;

/**
 * Representa la carta del restaurante: el catálogo fijo de productos disponibles.
 * Es quien conoce qué se vende y a qué precio.
 * Reemplaza los arrays Datos.nombres[], Datos.precios[] y Datos.CARTA.
 */
public class Carta {

    private static final Producto[] PRODUCTOS = {
        new Producto("Bandeja Paisa",       32000),
        new Producto("Sancocho de Gallina", 28000),
        new Producto("Arepa con Huevo",      8000),
        new Producto("Jugo Natural",         7000),
        new Producto("Gaseosa",              4500),
        new Producto("Cerveza Poker",        6000),
        new Producto("Agua Panela",          3500),
        new Producto("Arroz con Pollo",     25000)
    };

    public static int tamanio() {
        return PRODUCTOS.length;
    }

    /**
     * Retorna el producto en la posición indicada (índice base-0).
     * Usar con índices validados previamente.
     */
    public static Producto obtener(int indice) {
        return PRODUCTOS[indice];
    }

    public static Producto[] todos() {
        return PRODUCTOS.clone();
    }
}