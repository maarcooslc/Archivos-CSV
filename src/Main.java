import controller.Controller;
import utils.Utils;

import java.util.Scanner;

public class Main {
    static final Scanner S = new Scanner(System.in);

    public static void main(String[] args) {
        Controller controller = new Controller();
        int op;

        do {
            System.out.print("""
                    1. Mostrar todos los restarurantes cuyo código postal empieze por 6
                    2. Añadir datos al fichero
                    3. Crear fichero .csv excepto aquellos restaurantes cuyo código postal empieze por 6
                    4. Generar CSV desde XML (libreriaConcesionario)
                    5. Salir
                    Introduce una opción:\s""");
            op = Integer.parseInt(S.nextLine());

            switch (op) {
                case 1:
                    System.out.println(controller.mostrarRestaurantesPorCodPostal(6));
                    Utils.esperar();
                    Utils.limpiaPantalla();
                    break;
                case 2:
                    String nombre, direccion, ciudad, estado;
                    int codigoPostal;

                    System.out.println("Dime el nombre del restaurante");
                    nombre = S.nextLine();

                    System.out.print("Dime la direccion del restaurante: ");
                    direccion = S.nextLine();

                    System.out.print("Dime el ciudad del restaurante: ");
                    ciudad = S.nextLine();

                    System.out.print("Dime el estado del restaurante: ");
                    estado = S.nextLine();

                    System.out.print("Dime el codigo del restaurante: ");
                    codigoPostal = Integer.parseInt(S.nextLine());

                    if (controller.aniadeInfo(nombre, direccion, ciudad, estado, codigoPostal)) System.out.println("Información añadida a csv correctamente");
                    Utils.esperar();
                    Utils.limpiaPantalla();
                    break;
                case 3:
                    if (controller.creaCopia()) System.out.println("Se ha creado la copia correctamente");
                    Utils.esperar();
                    Utils.limpiaPantalla();
                    break;
                case 4:
                    controller.generarCsvConcesionario();
                    Utils.esperar();
                    Utils.limpiaPantalla();
                    break;
                case 5:
                    System.out.println("Saliendo del programa");
                    Utils.esperar();
                    break;
                default:
                    System.out.println("Opción incorrecta");
                    break;
            }
        } while (op != 5);
    }

}
