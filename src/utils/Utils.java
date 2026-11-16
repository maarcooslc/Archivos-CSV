package utils;

public class Utils {
    public static void limpiaPantalla(){
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public static void esperar(){
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
