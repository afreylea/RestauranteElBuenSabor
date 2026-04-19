package com.mycompany.restauranteelbuensabor;

/**
 * Responsable de toda la presentación en pantalla.
 * Es la dueña de los datos de identidad visual del restaurante
 * (nombre, dirección, NIT, separadores), porque es la única que los necesita.
 */
public class Imprimir {

    // ── Identidad del restaurante ─────────────────────────────────────────
    private static final String NOMBRE_RESTAURANTE = "El Buen Sabor";
    private static final String DIRECCION          = "Calle 15 #8-32, Valledupar";
    private static final String NIT                = "900.123.456-7";
    private static final String SEPARADOR_LARGO    = "========================================";
    private static final String SEPARADOR_CORTO    = "----------------------------------------";

    // ── Métodos privados de apoyo ─────────────────────────────────────────

    private static void imprimirEncabezado() {
        System.out.println(SEPARADOR_LARGO);
        System.out.println("    RESTAURANTE " + NOMBRE_RESTAURANTE);
        System.out.println("    " + DIRECCION);
        System.out.println("    NIT: " + NIT);
        System.out.println(SEPARADOR_LARGO);
    }

    private static void imprimirTotales(Factura factura) {
        double subtotal             = factura.calcularSubtotal();
        double subtotalConDescuento = factura.aplicarDescuento(subtotal);
        double iva                  = factura.calcularIVA(subtotalConDescuento);
        double totalConIva          = subtotalConDescuento + iva;
        double propina              = factura.calcularPropina(subtotalConDescuento, totalConIva);
        double total                = totalConIva + propina;

        System.out.println(SEPARADOR_CORTO);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotalConDescuento);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", iva);

            if (propina > 0) {
                System.out.printf("%-27s $%,.0f%n", "Propina (10%):", propina);
            }

        System.out.println(SEPARADOR_CORTO);
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", total);
        System.out.println(SEPARADOR_LARGO);
    }

    // ── Métodos públicos ──────────────────────────────────────────────────

    public static void mostrarBienvenida() {
        imprimirEncabezado();
    }

    public static void mostrarCarta() {
        imprimirEncabezado();
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(SEPARADOR_LARGO);
        Producto[] productos = Carta.todos();

            for (int i = 0; i < productos.length; i++) {
                System.out.printf(
                    "%d. %-22s $%,.0f%n",
                    (i + 1),
                    productos[i].getNombre(),
                    productos[i].getPrecio()
                );
            }

        System.out.println(SEPARADOR_LARGO);
    }

    public static void mostrarPedido(Pedido pedido) {
        System.out.println("--- PEDIDO ACTUAL ---");

            for (Item item : pedido.getItems()) {
                System.out.printf(
                    "%-20s x%-6d $%,.0f%n",
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal()
                );
            }

        System.out.println(SEPARADOR_CORTO);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", pedido.calcularSubtotal());
    }

    public static void imprimirFacturaCompleta(Factura factura) {
        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d%n", factura.getNumero());
        System.out.println(SEPARADOR_CORTO);

            for (Item item : factura.getPedido().getItems()) {
                System.out.printf(
                    "%-20s x%-6d $%,.0f%n",
                    item.getProducto().getNombre(),
                    item.getCantidad(),
                    item.calcularSubtotal()
                );
            }

        imprimirTotales(factura);
        System.out.println("Gracias por su visita!");
        System.out.println(NOMBRE_RESTAURANTE + " - Valledupar");
        System.out.println(SEPARADOR_LARGO);
    }

    public static void imprimirFacturaResumen(Factura factura) {
        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", factura.getNumero());
        imprimirTotales(factura);
    }
}