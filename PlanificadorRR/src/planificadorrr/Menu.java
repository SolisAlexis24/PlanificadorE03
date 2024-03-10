package planificadorrr;

import java.util.Scanner;

public class Menu {

    public static void menu(Scanner scanner) {

        boolean continuar = true;
        int contador = 0;


        while (continuar) {
            System.out.println("MENU");
            System.out.println("Escoja una opcion: ");
            System.out.println("1. Ingresar nuevo proceso");
            System.out.println("2. Empezar planificador de procesos");
            System.out.println("3. Salir");

            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    contador+=1;
                    Proceso proceso = new Proceso(contador);
                    
                    scanner.nextLine(); //Se necesita leer este salto de línea para que pueda almacenarse nombre
                    
                    System.out.println("Ingrese el nombre del proceso: ");
                    String nombre = scanner.nextLine();
                    proceso.setNombre(nombre);
                    
                    System.out.println("Ingrese el tamano del proceso");
                    Integer tamano = scanner.nextInt();
                    proceso.setTamano(tamano);
                    
                    System.out.println("Ingrese el tiempo de ejecucion del proceso");
                    Integer tiempoEjecucion = scanner.nextInt();
                    proceso.setTiempoEjecucion(tiempoEjecucion);
                    
                    System.out.println("Ingrese el tiempo de llegada del proceso");
                    Integer tiempoLlegada = scanner.nextInt();
                    proceso.setTiempoLlegada(tiempoLlegada);
                    
                    
                    proceso.imprimirProceso();
                    System.out.println("");
                  
                    break;
                case 2:
                    System.out.println("Empezando política RR para planificador de procesos.");
                    // Colas y política rr
                    break;
                case 3:
                    System.out.println("Fin del programa");
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción no válida");
                    break;
            }
        }

        scanner.close();
    }
}
