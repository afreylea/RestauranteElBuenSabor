package com.mycompany.restauranteelbuensabor;

import java.util.Scanner;

public class RestauranteElBuenSabor {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int opcionMenu = 0;
        boolean ejecutando = true;

        int contadorIntentosInvalidos = 0;
        String mensajeTemporal = "";
        int valorTemporal = 0;
        double montoCalculado = 0;
        boolean continuar = true;

        System.out.println("========================================");
        System.out.println("    RESTAURANTE EL BUEN SABOR");
        System.out.println("    Calle 15 #8-32, Valledupar");
        System.out.println("    NIT: 900.123.456-7");
        System.out.println("========================================");

        while (ejecutando) {

            System.out.println("1. Ver carta");
            System.out.println("2. Agregar producto al pedido");
            System.out.println("3. Ver pedido actual");
            System.out.println("4. Generar factura");
            System.out.println("5. Nueva mesa");
            System.out.println("0. Salir");
            System.out.println("========================================");
            System.out.print("Seleccione una opcion: ");

            opcionMenu = scanner.nextInt();

            if (opcionMenu == 1) {

                // mostrar carta
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

                            // mesa no activa - pedir numero de mesa
                            System.out.print("Ingrese numero de mesa: ");
                            Datos.numeroMesaActual = scanner.nextInt();

                            if (Datos.numeroMesaActual > 0) {
                                Datos.estadoMesa = 1;
                                mensajeTemporal = String.valueOf(Datos.numeroMesaActual);
                                valorTemporal = Datos.numeroMesaActual;
                                contadorIntentosInvalidos = valorTemporal + 1;
                            } else {
                                // mesa invalida pero se continua igual
                                Datos.numeroMesaActual = 1;
                                Datos.estadoMesa = 1;
                                mensajeTemporal = "1";
                                valorTemporal = 1;
                                contadorIntentosInvalidos = 2;
                            }
                        }

                        // agrega al pedido
                        Datos.cantidades[numeroProducto - 1] =
                            Datos.cantidades[numeroProducto - 1] + cantidad;

                        System.out.println("Producto agregado al pedido.");
                        System.out.println("  -> " + Datos.nombres[numeroProducto - 1] + " x" + cantidad);

                        montoCalculado = Datos.precios[numeroProducto - 1] * cantidad;

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

                if (Utilidades.validar()) {
                    Imprimir.mostrarPedido();
                } else {
                    System.out.println("No hay productos en el pedido actual.");
                    System.out.println("Use la opcion 2 para agregar productos.");
                    continuar = true;
                }

                System.out.println();

            } else if (opcionMenu == 4) {

                System.out.println();

                if (Utilidades.validar()) {

                    double resultado = 0;

                    // procesar pedido y generar total
                    resultado = Proceso.calcularTotalFactura();

                    valorTemporal = (int) resultado;
                    mensajeTemporal = "Total calculado: $" + valorTemporal;
                    montoCalculado = resultado;

                    // imprimir factura detallada
                    Imprimir.imprimirFacturaCompleta();
                    System.out.println();

                } else {
                    System.out.println("No se puede generar factura.");
                    System.out.println("No hay productos en el pedido.");
                    System.out.println("Use la opcion 2 para agregar productos primero.");

                    valorTemporal = 0;
                    mensajeTemporal = "";
                    montoCalculado = 0;
                    continuar = true;
                }

            } else if (opcionMenu == 5) {

                System.out.println();

                Utilidades.reiniciar();

                contadorIntentosInvalidos = 0;
                valorTemporal = 0;
                mensajeTemporal = "";
                montoCalculado = 0;
                continuar = true;

                System.out.println("Mesa reiniciada. Lista para nuevo cliente.");
                System.out.println();

            } else if (opcionMenu == 0) {

                ejecutando = false;
                System.out.println("Hasta luego!");

            } else {

                System.out.println("Opcion no valida. Seleccione entre 0 y 5.");

                Scanner scannerSecundario = new Scanner(System.in);
                contadorIntentosInvalidos = contadorIntentosInvalidos + 1;

                if (contadorIntentosInvalidos > 3) {
                    System.out.println("Demasiados intentos invalidos.");
                    contadorIntentosInvalidos = 0;

                    String limpieza = scannerSecundario.hasNextLine()
                        ? scannerSecundario.nextLine()
                        : "";
                }
            }
        }

        scanner.close();
    }
}
