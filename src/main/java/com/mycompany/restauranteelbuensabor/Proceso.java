package com.mycompany.restauranteelbuensabor;

public class Proceso {

    // ── Métodos de cálculo ────────────────────────────────────────────────

    public static double calcularSubtotal() {
        double subtotal = 0;
        for (int i = 0; i < Datos.nombres.length; i++) {
            subtotal += Datos.precios[i] * Datos.cantidades[i];
        }
        return subtotal;
    }

    private static int contarItemsConCantidad() {
        int contador = 0;
        for (int cantidad : Datos.cantidades) {
            if (cantidad > 0) {
                contador++;
            }
        }
        return contador;
    }

    public static double aplicarDescuento(double subtotal) {
        if (contarItemsConCantidad() > Datos.MIN_ITEMS_DESCUENTO) {
            return subtotal - (subtotal * Datos.TASA_DESCUENTO);
        }
        return subtotal;
    }

    public static double calcularIVA(double base) {
        return base * Datos.TASA_IVA;
    }

    /**
     * La propina se activa cuando el subtotal con descuento supera el umbral,
     * pero se calcula sobre el total ya incluido el IVA.
     */
    public static double calcularPropina(double subtotalConDescuento, double totalConIva) {
        if (subtotalConDescuento > Datos.UMBRAL_PROPINA) {
            return totalConIva * Datos.TASA_PROPINA;
        }
        return 0;
    }

    // ── Método orquestador ────────────────────────────────────────────────

    public static double calcularTotal() {
        double subtotal             = calcularSubtotal();
        double subtotalConDescuento = aplicarDescuento(subtotal);
        double iva                  = calcularIVA(subtotalConDescuento);
        double totalConIva          = subtotalConDescuento + iva;
        double propina              = calcularPropina(subtotalConDescuento, totalConIva);
        double total                = totalConIva + propina;

        Datos.total = total;
        return total;
    }
}