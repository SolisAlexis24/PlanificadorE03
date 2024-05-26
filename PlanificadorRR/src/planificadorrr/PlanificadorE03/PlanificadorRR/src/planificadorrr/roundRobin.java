import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class roundRobin {

    private static final int TIEMPO_CUANTO = 4; // Quantum de tiempo

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Lista de procesos
        ArrayList<Proceso> procesos = new ArrayList<>();

        // Leer la cantidad de procesos
        System.out.print("Ingrese la cantidad de procesos: ");
        int n = scanner.nextInt();

        // Leer datos de cada proceso
        for (int i = 0; i < n; i++) {
            System.out.print("Proceso " + (i + 1) + ":\n");
            System.out.print("  Tiempo de llegada: ");
            int tiempoLlegada = scanner.nextInt();
            System.out.print("  Tiempo de ejecución: ");
            int tiempoEjecucion = scanner.nextInt();

            procesos.add(new Proceso(i + 1, tiempoLlegada, tiempoEjecucion));
        }

        // Ordenar los procesos por tiempo de llegada
        Collections.sort(procesos, (p1, p2) -> p1.tiempoLlegada - p2.tiempoLlegada);

        // Simular la ejecución
        int tiempoActual = 0;
        int procesoActual = 0;
        while (!procesos.isEmpty()) {
            Proceso proceso = procesos.get(procesoActual);

            // Si el proceso ha llegado
            if (tiempoActual >= proceso.tiempoLlegada) {
                // Si el proceso aún tiene tiempo de ejecución
                if (proceso.tiempoEjecucion > 0) {
                    // Ejecutar el proceso durante el quantum de tiempo
                    int tiempoEjecutado = Math.min(proceso.tiempoEjecucion, TIEMPO_CUANTO);
                    proceso.tiempoEjecucion -= tiempoEjecutado;

                    // Mostrar información del proceso
                    System.out.println("Tiempo " + tiempoActual + ": " + proceso + " (" + tiempoEjecutado + " unidades)");

                    tiempoActual += tiempoEjecutado;
                } else {
                    // El proceso ha terminado
                    procesos.remove(procesoActual);
                    procesoActual--; // Retroceder el índice para evitar omitir un proceso
                }
            }

            // Pasar al siguiente proceso
            procesoActual = (procesoActual + 1) % procesos.size();
        }

        // Mostrar el tiempo total de ejecución
        System.out.println("Tiempo total de ejecución: " + tiempoActual);
    }
}

class Proceso {

    int id;
    int tiempoLlegada;
    int tiempoEjecucion;

    public Proceso(int id, int tiempoLlegada, int tiempoEjecucion) {
        this.id = id;
        this.tiempoLlegada = tiempoLlegada;
        this.tiempoEjecucion = tiempoEjecucion;
    }

    @Override
    public String toString() {
        return "Proceso " + id + " (Llegada: " + tiempoLlegada + ", Tiempo de ráfaga faltante: " + tiempoEjecucion + ")";
    }
}
