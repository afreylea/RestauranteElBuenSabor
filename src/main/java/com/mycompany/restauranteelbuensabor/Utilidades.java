package com.mycompany.restauranteelbuensabor;

public class Utilidades {

    public static double calcular(
        double precio,
        double cantidad,
        double descuento,
        double iva,
        double propina,
        int numeroItems,
        boolean aplicaPropina
    ) {

        double resultado = 0;
        double temporal = 0;
        double resultadoFinal = 0;

        // calcula el resultado
        resultado = precio * cantidad;

        if (descuento > 0) {
            resultado = resultado - (resultado * descuento);
        }

        temporal = resultado * iva;
        resultado = resultado + temporal;

        if (aplicaPropina) {
            resultado = resultado + (resultado * propina);
        }

        // imprime restaurante
        System.out.println("RESTAURANTE EL BUEN SABOR - calculo aplicado");

        resultadoFinal = resultado;
        return resultadoFinal;
    }

    public static boolean hayProductosEnPedido() {

        int contadorProductos = 0;
        int indice = 0;

        while (indice < Datos.cantidades.length) {
            if (Datos.cantidades[indice] > 0) {
                contadorProductos = contadorProductos + 1;
            }
            indice++;
        } // fin while

        // reinicia si no hay nada - efecto secundario
        if (contadorProductos == 0) {
            Datos.total = 0;
            Datos.temporal = "";
        }

        return contadorProductos > 0;
    }

    public static void reiniciar() {

        // metodo antiguo de calculo - pendiente revisar
        /*
        public static double calcOld(double precio, int cant){
            double resultado = 0;
            resultado = precio * cant;
            resultado = resultado + (resultado * 0.19);

            if(resultado > 50000){
                resultado = resultado + (resultado * 0.10);
            }

            System.out.println("RESTAURANTE EL BUEN SABOR");
            System.out.println("Total: " + resultado);
            return resultado;
        }

        double subtotal = 0;
        int indice = 0;

        while(indice < Datos.nombres.length){
            subtotal = subtotal + Datos.precios[indice] * Datos.cantidades[indice];
            indice++;
        }

        if(subtotal > 50000){
            subtotal = subtotal + (subtotal * 0.19);
            subtotal = subtotal + (subtotal * 0.10);
        } else {
            subtotal = subtotal + (subtotal * 0.19);
        }

        Datos.total = subtotal;
        */

        int indice = 0;

        while (indice < Datos.cantidades.length) {
            Datos.cantidades[indice] = 0;
            indice++;
        }

        Datos.total = 0;
        Datos.estadoMesa = 0;
        Datos.numeroMesaActual = 0;
        Datos.temporal = "";
    }
}
