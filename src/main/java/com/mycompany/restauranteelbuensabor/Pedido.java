package com.mycompany.restauranteelbuensabor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Representa el pedido activo de una mesa.
 * Administra la lista de ítems: agrega, acumula cantidades si el producto
 * ya existe, y expone consultas sobre el estado del pedido.
 */
public class Pedido {

    private final List<Item> items = new ArrayList<>();

    // ── Mutadores ────────────────────────────────────────────────────────

    /**
     * Agrega cantidad unidades de producto al pedido.
     * Si el producto ya está, acumula la cantidad en lugar de duplicar la línea.
     */
    public void agregarItem(Producto producto, int cantidad) {
        Item itemExistente = buscarItem(producto);

        if (itemExistente != null) {
            itemExistente.setCantidad(itemExistente.getCantidad() + cantidad);
        } else {
            items.add(new Item(producto, cantidad));
        }
    }

    /** Elimina todos los ítems del pedido. */
    public void limpiar() {
        items.clear();
    }

    // ── Consultas ─────────────────────────────────────────────────────────

    public boolean tieneProductos() {
        return !items.isEmpty();
    }

    public int contarItemsDiferentes() {
        return items.size();
    }

    /** Suma de precio × cantidad de cada ítem. */
    public double calcularSubtotal() {
        double subtotal = 0.0d;
        for (Item item : items) {
            subtotal += item.calcularSubtotal();
        }
        return subtotal;
    }

    /**
     * Devuelve una vista de solo lectura de los ítems para que
     * Imprimir pueda recorrerlos sin modificar el pedido.
     */
    public List<Item> getItems() {
        return Collections.unmodifiableList(items);
    }

    // ── Auxiliar privado ──────────────────────────────────────────────────

    private Item buscarItem(Producto producto) {
        for (Item item : items) {
            if (item.getProducto().getNombre().equals(producto.getNombre())) {
                return item;
            }
        }
        return null;
    }
}