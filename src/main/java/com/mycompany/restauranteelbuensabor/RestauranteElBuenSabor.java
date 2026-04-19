package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

public class RestauranteElBuenSabor {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int     opcionMenu                = 0;
        boolean ejecutando                = true;
        int     contadorIntentosInvalidos = 0;

        System.out.println(Datos.SEPARADOR_LARGO);
        System.out.println("    RESTAURANTE " + Datos.NOMBRE_RESTAURANTE);
        System.out.println("    " + Datos.DIRECCION);
        System.out.println("    NIT: " + Datos.NIT);
        System.out.println(Datos.SEPARADOR_LARGO);

        while (ejecutando) {

            System.out.println("1. Ver carta");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Generar factura");
            System.out.println("5. Nueva mesa");
            System.out.println("0. Salir");
            System.out.println(Datos.SEPARADOR_LARGO);
            System.out.print("Seleccione una opcion: ");

            opcionMenu = scanner.nextInt();

            if (opcionMenu == 1) {

                Imprimir.mostrarCarta();
                System.out.println();

            } else if (opcionMenu == 2) {

                System.out.println("--- AGREGAR PRODUCTO ---");
                System.out.print("Numero de producto (1-" + Datos.nombres.length + "): ");
                int numeroProducto = scanner.nextInt();

                System.out.print("Cantidad: ");
                int cantidad = scanner.nextInt();

                if (numeroProducto > 0 && numeroProducto <= Datos.nombres.length) {

                    if (cantidad > 0) {

                        if (Datos.estadoMesa == 0) {
                            System.out.print("Ingrese numero de mesa: ");
                            Datos.numeroMesaActual = scanner.nextInt();

                            if (Datos.numeroMesaActual <= 0) {
                                Datos.numeroMesaActual = 1;
                            }
                            Datos.estadoMesa = 1;
                        }

                        Datos.cantidades[numeroProducto - 1] += cantidad;

                        System.out.println("Producto agregado al pedido.");
                        System.out.println("  -> " + Datos.nombres[numeroProducto - 1] + " x" + cantidad);

                    } else {

                        if (cantidad == 0) {
                            System.out.println("La cantidad no puede ser cero.");
                        } else {
                            System.out.println("Cantidad invalida. Ingrese un valor positivo.");
                        }
                    }

                } else {

                    if (numeroProducto <= 0) {
                        System.out.println("El numero debe ser mayor a cero.");
                    } else {
                        System.out.println("Producto no existe. La carta tiene " + Datos.nombres.length + " productos.");
                    }
                }

                System.out.println();

            } else if (opcionMenu == 3) {

                System.out.println();

                if (Utilidades.hayProductosEnPedido()) {
                    Imprimir.mostrarPedido();
                } else {
                    System.out.println("No hay productos en el pedido actual.");
                    System.out.println("Use la opcion 2 para agregar productos.");
                }

                System.out.println();

            } else if (opcionMenu == 4) {

                System.out.println();

                if (Utilidades.hayProductosEnPedido()) {
                    Imprimir.imprimirFacturaCompleta();
                    Datos.numeroFactura++;
                    Datos.estadoMesa = 0;
                    System.out.println();
                } else {
                    System.out.println("No se puede generar factura.");
                    System.out.println("No hay productos en el pedido.");
                    System.out.println("Use la opcion 2 para agregar productos primero.");
                }

            } else if (opcionMenu == 5) {

                System.out.println();
                Utilidades.reiniciar();
                contadorIntentosInvalidos = 0;
                System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                System.out.println();

            } else if (opcionMenu == 0) {

                ejecutando = false;
                System.out.println("Hasta luego!");

            } else {

                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");
                contadorIntentosInvalidos++;

                if (contadorIntentosInvalidos > 3) {
                    System.out.println("Demasiados intentos invalidos.");
                    contadorIntentosInvalidos = 0;
                }
            }
        }

        scanner.close();
    }
}