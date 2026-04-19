package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

/**
 * Orquestador puro del sistema.
 * No contiene lógica de negocio ni de presentación — solo coordina.
 * El estado de la sesión (pedido, mesa, contador de facturas) vive aquí.
 */
public class RestauranteElBuenSabor {

    private static final Scanner scanner = new Scanner(System.in);
    private static       Pedido  pedido  = new Pedido();
    private static int           numeroMesa    = 0;
    private static int           numeroFactura = 1;

    public static void main(String[] args) {
        Imprimir.mostrarBienvenida();
        ejecutarMenuPrincipal();
        scanner.close();
    }

    // ── Flujo principal ───────────────────────────────────────────────────

    private static void ejecutarMenuPrincipal() {
        boolean ejecutando = true;

            while (ejecutando) {
                Imprimir.mostrarMenu();
                int opcion = scanner.nextInt();
                ejecutando = procesarOpcion(opcion);
            }
    }

    private static boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> { Imprimir.mostrarCarta();            Util.esperarEnter(); }
            case 2 -> agregarProductoAlPedido();
            case 3 -> { verPedidoActual();                  Util.esperarEnter(); }
            case 4 -> { generarFactura();                   Util.esperarEnter(); }
            case 5 -> { iniciarNuevaMesa();                 Util.esperarEnter(); }
            case 0 -> { System.out.println("Hasta luego!"); return false; }
            default -> { System.out.println("Opcion no valida. Seleccione entre 0 y 5."); Util.esperarEnter(); }
        }
        return true;
    }

    // ── Operaciones del menú ──────────────────────────────────────────────

    private static void agregarProductoAlPedido() {
        System.out.println("--- AGREGAR PRODUCTO ---");
        System.out.print("Numero de producto (1-" + Carta.tamanio() + "): ");
        int indiceProducto = scanner.nextInt();

        System.out.print("Cantidad: ");
        int cantidad = scanner.nextInt();

            if (!esIndiceValido(indiceProducto)) {
                System.out.println("Producto no existe. La carta tiene " + Carta.tamanio() + " productos.");
                return;
            }

            if (cantidad <= 0) {
                System.out.println(cantidad == 0
                    ? "La cantidad no puede ser cero."
                    : "Cantidad invalida. Ingrese un valor positivo.");
                return;
            }

            if (numeroMesa == 0) {
                numeroMesa = pedirNumeroMesa();
            }

        Producto productoElegido = Carta.obtener(indiceProducto - 1);
        pedido.agregarItem(productoElegido, cantidad);

        System.out.println("Producto agregado al pedido.");
        System.out.println("  -> " + productoElegido.getNombre() + " x" + cantidad);
    }

    private static int pedirNumeroMesa() {
        System.out.print("Ingrese numero de mesa: ");
        int mesa = scanner.nextInt();
        return mesa > 0 ? mesa : 1;
    }

    private static void verPedidoActual() {
        if (pedido.tieneProductos()) {
            Imprimir.mostrarPedido(pedido);
        } else {
            System.out.println("No hay productos en el pedido actual.");
            System.out.println("Use la opcion 2 para agregar productos.");
        }
    }

    private static void generarFactura() {
            if (!pedido.tieneProductos()) {
                System.out.println("No se puede generar factura: no hay productos en el pedido.");
                System.out.println("Use la opcion 2 para agregar productos primero.");
                return;
            }

        Factura factura = new Factura(pedido, numeroFactura);
        Imprimir.imprimirFacturaCompleta(factura);

        numeroFactura++;
        pedido     = new Pedido();
        numeroMesa = 0;
    }

    private static void iniciarNuevaMesa() {
        pedido     = new Pedido();
        numeroMesa = 0;
        System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
    }

    // ── Validaciones ──────────────────────────────────────────────────────

    private static boolean esIndiceValido(int indice) {
        return indice >= 1 && indice <= Carta.tamanio();
    }
}