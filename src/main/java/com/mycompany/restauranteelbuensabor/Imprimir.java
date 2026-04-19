package com.mycompany.restauranteelbuensabor;

public class Imprimir {

    // ── Métodos privados de apoyo ─────────────────────────────────────────

    private static void imprimirEncabezado() {
        System.out.println(Datos.SEPARADOR_LARGO);
        System.out.println("    RESTAURANTE " + Datos.NOMBRE_RESTAURANTE);
        System.out.println("    " + Datos.DIRECCION);
        System.out.println("    NIT: " + Datos.NIT);
        System.out.println(Datos.SEPARADOR_LARGO);
    }

    private static void imprimirTotales(
        double subtotalConDescuento,
        double iva,
        double propina,
        double total
    ) {
        System.out.println(Datos.SEPARADOR_CORTO);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotalConDescuento);
        System.out.printf("%-27s $%,.0f%n", "IVA (19%):", iva);
        if (propina > 0) {
            System.out.printf("%-27s $%,.0f%n", "Propina (10%):", propina);
        }
        System.out.println(Datos.SEPARADOR_CORTO);
        System.out.printf("%-27s $%,.0f%n", "TOTAL:", total);
        System.out.println(Datos.SEPARADOR_LARGO);
    }

    // ── Métodos públicos ──────────────────────────────────────────────────

    public static void mostrarCarta() {
        imprimirEncabezado();
        System.out.println("    --- NUESTRA CARTA ---");
        System.out.println(Datos.SEPARADOR_LARGO);
        for (int i = 0; i < Datos.nombres.length; i++) {
            System.out.printf(
                "%d. %-22s $%,.0f%n",
                (i + 1),
                Datos.nombres[i],
                Datos.precios[i]
            );
        }
        System.out.println(Datos.SEPARADOR_LARGO);
    }

    public static void mostrarPedido() {
        double subtotal = 0;

        System.out.println("--- PEDIDO ACTUAL ---");

        for (int i = 0; i < Datos.nombres.length; i++) {
            if (Datos.cantidades[i] > 0) {
                System.out.printf(
                    "%-20s x%-6d $%,.0f%n",
                    Datos.nombres[i],
                    Datos.cantidades[i],
                    (Datos.precios[i] * Datos.cantidades[i])
                );
                subtotal += Datos.precios[i] * Datos.cantidades[i];
            }
        }

        System.out.println(Datos.SEPARADOR_CORTO);
        System.out.printf("%-27s $%,.0f%n", "Subtotal:", subtotal);
    }

    public static void imprimirFacturaCompleta() {
        double subtotal             = Proceso.calcularSubtotal();
        double subtotalConDescuento = Proceso.aplicarDescuento(subtotal);
        double iva                  = Proceso.calcularIVA(subtotalConDescuento);
        double totalConIva          = subtotalConDescuento + iva;
        double propina              = Proceso.calcularPropina(subtotalConDescuento, totalConIva);
        double total                = totalConIva + propina;

        Datos.total = total;

        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d%n", Datos.numeroFactura);
        System.out.println(Datos.SEPARADOR_CORTO);

        for (int i = 0; i < Datos.nombres.length; i++) {
            if (Datos.cantidades[i] > 0) {
                System.out.printf(
                    "%-20s x%-6d $%,.0f%n",
                    Datos.nombres[i],
                    Datos.cantidades[i],
                    (Datos.precios[i] * Datos.cantidades[i])
                );
            }
        }

        imprimirTotales(subtotalConDescuento, iva, propina, total);

        System.out.println("Gracias por su visita!");
        System.out.println(Datos.NOMBRE_RESTAURANTE + " - Valledupar");
        System.out.println(Datos.SEPARADOR_LARGO);
    }

    public static void imprimirFacturaResumen() {
        double subtotal             = Proceso.calcularSubtotal();
        double subtotalConDescuento = Proceso.aplicarDescuento(subtotal);
        double iva                  = Proceso.calcularIVA(subtotalConDescuento);
        double totalConIva          = subtotalConDescuento + iva;
        double propina              = Proceso.calcularPropina(subtotalConDescuento, totalConIva);
        double total                = totalConIva + propina;

        imprimirEncabezado();
        System.out.printf("FACTURA No. %03d (RESUMEN)%n", Datos.numeroFactura);

        imprimirTotales(subtotalConDescuento, iva, propina, total);
    }
}