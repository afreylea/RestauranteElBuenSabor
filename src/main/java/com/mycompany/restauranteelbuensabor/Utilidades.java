package com.mycompany.restauranteelbuensabor;

public class Utilidades {

    /**
     * Calcula el monto final para un ítem aplicando descuento, IVA y propina.
     * Solo calcula — no imprime ni modifica estado global.
     */
    public static double calcular(
        double precio,
        double cantidad,
        double descuento,
        double iva,
        double propina,
        int    numeroItems,
        boolean aplicaPropina
    ) {
        double resultado = precio * cantidad;

        if (descuento > 0) {
            resultado -= resultado * descuento;
        }

        resultado += resultado * iva;

        if (aplicaPropina) {
            resultado += resultado * propina;
        }

        return resultado;
    }

    /**
     * Devuelve true si hay al menos un producto con cantidad mayor a cero.
     * Solo consulta — no modifica ningún estado global.
     */
    public static boolean hayProductosEnPedido() {
        for (int cantidad : Datos.cantidades) {
            if (cantidad > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * Reinicia el estado del pedido para preparar la mesa para un nuevo cliente.
     */
    public static void reiniciar() {
        for (int i = 0; i < Datos.cantidades.length; i++) {
            Datos.cantidades[i] = 0;
        }
        Datos.total            = 0;
        Datos.estadoMesa       = 0;
        Datos.numeroMesaActual = 0;
        Datos.temporal         = "";
    }
}