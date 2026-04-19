package com.mycompany.restauranteelbuensabor;

/**
 * Responsable de toda la presentación en pantalla.
 * Usa Util.borrarPantalla() antes de cada sección para dar
 * una experiencia de pantalla limpia entre operaciones.
 * Expone mostrarMenu() para que Main ya no imprima nada directamente.
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

    /** Bienvenida inicial al arrancar el sistema. */
    public static void mostrarBienvenida() {
        Util.borrarPantalla();
        imprimirEncabezado();
    }

    /**
     * Muestra el menú principal con pantalla limpia.
     * Main lo llama al inicio de cada iteración del bucle.
     */
    public static void mostrarMenu() {
        Util.borrarPantalla();
        imprimirEncabezado();
        System.out.println("1. Ver carta");
        System.out.println("2. Agregar producto al pedido");
        System.out.println("3. Ver pedido actual");
        System.out.println("4. Generar factura");
        System.out.println("5. Nueva mesa");
        System.out.println("0. Salir");
        System.out.println(SEPARADOR_LARGO);
        System.out.print("Seleccione una opcion: ");
    }

    /** Muestra la carta de productos con pantalla limpia. */
    public static void mostrarCarta() {
        Util.borrarPantalla();
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

    /** Muestra los ítems del pedido activo con pantalla limpia. */
    public static void mostrarPedido(Pedido pedido) {
        Util.borrarPantalla();
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

    /** Imprime la factura completa con detalle de ítems y totales. */
    public static void imprimirFacturaCompleta(Factura factura) {
        Util.borrarPantalla();
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

    /** Imprime la factura en formato resumido (solo totales, sin detalle). */
    public static void imprimirFacturaResumen(Factura factura) {
        Util.borrarPantalla();
        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", factura.getNumero());
        imprimirTotales(factura);
    }
}
