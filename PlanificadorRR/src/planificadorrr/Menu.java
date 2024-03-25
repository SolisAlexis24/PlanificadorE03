package planificadorrr;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;

public class Menu {
    
    private static Boolean continuar = true; //Atributo para que se determine si se sigue con la impresion del menu
    private static Integer contador = 0;  //Contador para poder crear los ID de los procesos

    /**
     * Metodo para imprimir el menu con ayuda de un switch
     * @param scanner 
     */
    public static void showMenu(Scanner scanner) {

        while (continuar) {
            System.out.println("MENU");
            System.out.println("Escoja una opcion: ");
            System.out.println("1. Ingresar nuevo proceso");
            System.out.println("2. Empezar planificador de procesos");
            System.out.println("3. Salir");

            Integer opcion = scanner.nextInt();

            switch (opcion) {
                case 1 -> {
                    contador++;
                    Proceso proceso = new Proceso(contador);
                    
                    scanner.nextLine(); //Se necesita leer este salto de línea para que pueda almacenarse nombre
                    
                    System.out.println("Ingrese el nombre del proceso: ");
                    String nombre = scanner.nextLine();
                    proceso.setNombre(nombre);
                    
                    System.out.println("Ingrese el tamano del proceso en kB:");
                    Integer tamano = scanner.nextInt();
                    proceso.setTamano(tamano);
                    
                    System.out.println("Ingrese el tiempo de ejecucion del proceso en ms:");
                    Integer tiempoEjecucion = scanner.nextInt();
                    proceso.setTiempoEjecucion(tiempoEjecucion);
                    
                    System.out.println("Ingrese el tiempo de llegada del proceso en ms:");
                    Integer tiempoLlegada = scanner.nextInt();
                    proceso.setTiempoLlegada(tiempoLlegada);

                    PlanificadorRR.Procesos.add(proceso);
                    System.out.println("");
                }
                case 2 -> {
                    PlanificadorRR.ProcesosAux = PlanificadorRR.Procesos;
                    System.out.println("Empezando política RR para planificador de procesos.");
                    // Este bloque de código ordena la lista de procesos en PlanificadorRR.Procesos según el tiempo de llegada de cada proceso.
                    // Se utiliza el método estático sort() de la clase Collections para ordenar la lista.
                    Collections.sort(PlanificadorRR.Procesos, new Comparator<Proceso>() {
                        // Aquí se implementa la interfaz Comparator para especificar cómo se deben comparar dos objetos Proceso.
                        @Override
                        public int compare(Proceso p1, Proceso p2) {
                            // Este método compare() compara dos procesos p1 y p2 basándose en su tiempo de llegada.
                            // Devuelve un valor negativo si p1 debe ir antes que p2, un valor positivo si p2 debe ir antes que p1,
                            // o cero si los tiempos de llegada son iguales y no hay preferencia.
                            return p1.getTiempoLlegada().compareTo(p2.getTiempoLlegada());
                        }
                    });
                    
                    RoundRobin RR = new RoundRobin();
                    RR.Run();
                    continuar = false;
                }
                case 3 -> {
                    System.out.println("Fin del programa");
                    continuar = false;
                }
                default -> System.out.println("Opción no válida");
            }
        }

        scanner.close();
    }
}
