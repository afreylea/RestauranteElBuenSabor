package com.mycompany.restauranteelbuensabor;

import java.io.IOException;

/**
 * Utilidades de consola de bajo nivel.
 *
 * Dos responsabilidades únicas, sin estado:
 *   - borrarPantalla(): limpia la terminal usando códigos ANSI.
 *   - esperarEnter():   pausa la ejecución hasta que el usuario presione Enter.
 *
 * ¿Quién las usa?
 *   - Imprimir llama a borrarPantalla() antes de cada sección nueva,
 *     porque limpiar la pantalla es una decisión de presentación.
 *   - Main llama a esperarEnter() entre operaciones del menú,
 *     porque pausar el flujo es una decisión de orquestación,
 *     no de impresión.
 */
public class Util {

    private Util() {
        // Clase de utilidades: no se instancia
    }

    /**
     * Limpia la terminal.
     * Usa la secuencia de escape ANSI estándar, compatible con
     * Linux, macOS y terminales de Windows con soporte ANSI activo.
     * Si el entorno no soporta ANSI (IDE embebido), imprime
     * líneas en blanco como alternativa visual.
     */
    public static void borrarPantalla() {
        String so = System.getProperty("os.name").toLowerCase();

        try {
            if (so.contains("win")) {
                new ProcessBuilder("cmd", "/c", "cls")
                    .inheritIO()
                    .start()
                    .waitFor();
            } else {
                // Secuencia ANSI: mover cursor a inicio + borrar pantalla
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            // Alternativa si el entorno no soporta el comando
            for (int i = 0; i < 40; i++) {
                System.out.println();
            }
        }
    }

    /**
     * Pausa la ejecución hasta que el usuario presione Enter.
     * Consume cualquier carácter previo en el buffer de entrada
     * antes de esperar, para evitar saltos automáticos no deseados.
     */
    public static void esperarEnter() {
        System.out.println("\nPresione Enter para continuar...");
        try {
            // Drena el buffer (puede haber un '\n' de nextInt() previo)
            while (System.in.available() > 0) {
                System.in.read();
            }
            System.in.read();
        } catch (IOException e) {
            // Si la lectura falla, simplemente continuamos
        }
    }
}
