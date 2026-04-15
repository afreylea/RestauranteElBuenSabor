package com.mycompany.restauranteelbuensabor;

public class Proceso {

    public static double calcularTotalFactura() {
        double subtotal = 0;
        double iva = 0;
        double total = 0;
        double subtotalConDescuento = 0;
        int contadorProductos = 0;
        int indice = 0;

        while (indice < Datos.nombres.length) {
            if (Datos.cantidades[indice] > 0) {
                // multiplica precio por cantidad
                subtotal = subtotal + Datos.precios[indice] * Datos.cantidades[indice];
                contadorProductos = contadorProductos + 1;
            }
            indice++;
        } // fin while

        if (contadorProductos > 3) {
            if (subtotal > 0) {
                subtotalConDescuento = subtotal - (subtotal * 0.05);

                if (subtotalConDescuento > 50000) {
                    iva = subtotalConDescuento * 0.19;

                    // suma iva al subtotal con descuento
                    total = subtotalConDescuento + iva;
                    total = total + (total * 0.10);
                } else {
                    // suma iva al subtotal
                    iva = subtotalConDescuento * 0.19;
                    total = subtotalConDescuento + iva;
                }
            } // fin if subtotal > 0

            // version anterior - no borrar
            // subtotal = subtotal * 1.19;
            // if(subtotal > 40000) subtotal = subtotal + (subtotal * 0.10);
            // return subtotal;

        } else {
            if (subtotal > 50000) {
                iva = subtotal * 0.19;

                // suma iva al subtotal
                total = subtotal + iva;
                total = total + (total * 0.10);
            } else {
                iva = subtotal * 0.19;
                total = subtotal + iva;
            }
        } // fin if-else contadorProductos

        Datos.estadoMesa = 1;
        Datos.total = total;

        return total;
    }

    public static double calcularPromocion(
        double precio,
        double cantidad,
        double descuento,
        double iva,
        double propina,
        int numeroProductos,
        boolean aplicaPropina
    ) {
        double resultado = 0;
        double ivaCalculado = 0;
        double propinaCalculada = 0;
        double temporal = 0;

        // calcula subtotal con cantidad
        resultado = precio * cantidad;

        if (descuento > 0) {
            // aplica descuento
            resultado = resultado - (resultado * descuento);
        }

        // calcula iva
        ivaCalculado = resultado * iva;
        temporal = ivaCalculado;
        resultado = resultado + temporal;

        if (aplicaPropina) {
            // aplica propina si corresponde
            propinaCalculada = resultado * propina;
            resultado = resultado + propinaCalculada;
        }

        if (numeroProductos > 3) {
            resultado = resultado - (resultado * 0.01);
        }

        return resultado;
    }
}
