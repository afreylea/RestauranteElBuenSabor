package com.mycompany.restauranteelbuensabor;

public class Datos {

    // ── Constantes de negocio ─────────────────────────────────────────────
    public static final double TASA_IVA            = 0.19;
    public static final double TASA_PROPINA        = 0.10;
    public static final double TASA_DESCUENTO      = 0.05;
    public static final double UMBRAL_PROPINA      = 50000;
    public static final int    MIN_ITEMS_DESCUENTO = 3;

    // ── Constantes del restaurante ────────────────────────────────────────
    public static final String NOMBRE_RESTAURANTE = "El Buen Sabor";
    public static final String DIRECCION          = "Calle 15 #8-32, Valledupar";
    public static final String NIT                = "900.123.456-7";
    public static final String SEPARADOR_LARGO    = "========================================";
    public static final String SEPARADOR_CORTO    = "----------------------------------------";

    // ── Carta del restaurante ─────────────────────────────────────────────
    public static String[] nombres = {
        "Bandeja Paisa",
        "Sancocho de Gallina",
        "Arepa con Huevo",
        "Jugo Natural",
        "Gaseosa",
        "Cerveza Poker",
        "Agua Panela",
        "Arroz con Pollo"
    };

    public static double[] precios = {
        32000, 28000, 8000, 7000, 4500, 6000, 3500, 25000
    };

    public static int[] cantidades = {
        0, 0, 0, 0, 0, 0, 0, 0
    };

    // ── Estado del sistema ────────────────────────────────────────────────
    public static int    numeroMesaActual = 0;
    public static int    estadoMesa       = 0;
    public static double total            = 0;
    public static int    numeroFactura    = 1;
    public static String temporal         = "";
}