package com.mycompany.restauranteelbuensabor;

/**
 * Encapsula la lógica de cálculo de una factura.
 * Es la dueña de las reglas de negocio: tasas, umbrales y mínimos.
 * Si el IVA cambia, solo hay que tocar esta clase.
 */
public class Factura {

    // ── Reglas de negocio ─────────────────────────────────────────────────
    private static final double TASA_IVA            = 0.19;
    private static final double TASA_PROPINA        = 0.10;
    private static final double TASA_DESCUENTO      = 0.05;
    private static final double UMBRAL_PROPINA      = 50000;
    private static final int    MIN_ITEMS_DESCUENTO = 3;

    // ── Estado ────────────────────────────────────────────────────────────
    private final Pedido pedido;
    private final int    numero;

    public Factura(Pedido pedido, int numero) {
        this.pedido = pedido;
        this.numero = numero;
    }

    public int    getNumero() { return numero; }
    public Pedido getPedido() { return pedido; }

    // ── Métodos de cálculo ────────────────────────────────────────────────

    public double calcularSubtotal() {
        return pedido.calcularSubtotal();
    }

    public double aplicarDescuento(double subtotal) {
        if (pedido.contarItemsDiferentes() > MIN_ITEMS_DESCUENTO) {
            return subtotal * (1 - TASA_DESCUENTO);
        }
        return subtotal;
    }

    public double calcularIVA(double base) {
        return base * TASA_IVA;
    }

    public double calcularPropina(double subtotalConDescuento, double totalConIva) {
        if (subtotalConDescuento > UMBRAL_PROPINA) {
            return totalConIva * TASA_PROPINA;
        }
        return 0.0;
    }

    public double calcularTotal() {
        double subtotalConDescuento = aplicarDescuento(calcularSubtotal());
        double iva                  = calcularIVA(subtotalConDescuento);
        double totalConIva          = subtotalConDescuento + iva;
        double propina              = calcularPropina(subtotalConDescuento, totalConIva);
        return totalConIva + propina;
    }
}