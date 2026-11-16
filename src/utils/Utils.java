package utils;

public class Utils {
    // Mete 10 saltos de línea para limpiar un poco la consola
    public static void limpiaPantalla(){
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    // Pausa de 3 segundos para que dé tiempo a leer
    public static void esperar(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
