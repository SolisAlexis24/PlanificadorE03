/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package planificadorrr;
import java.util.Scanner;
import java.util.LinkedList;

/**
 *
 * @author Equipo03
 */
public class PlanificadorRR {
    static Cola ProcesosEspera = new Cola(); //Cola de procesos en espera, cola auxiliar con espacio de memoria infinita.
    static Cola ProcesosListos = new Cola(); //Cola de procesos listos para la ejecucion, esta cola tiene un espacio definido por el usuario
    static LinkedList<Proceso> Procesos = new LinkedList<Proceso>(); //Lista de procesos que el usuario ingresa. Esta lista en algun momento se ordenará por tiempo de llegada. Al final de la ejecución esta lista queda vacía
    static LinkedList<Proceso> ProcesosAux = new LinkedList<Proceso>(); //Lista de procesos que el usuario ingresa. Esta lista nunca se ordenará, pues se hará uso de ella para calcular tiempos

    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Ingrese la capacidad de la cola de procesos listos para ejecución en kiB");
        Integer capacidad = scanner1.nextInt();
        ProcesosListos.setCapacidad(capacidad);
        System.out.println("Su RAM tendrá una capacidad de " + ProcesosListos.getCapacidad().toString()+"kiB");
        Scanner scanner2 = new Scanner(System.in);
        Menu.showMenu(scanner2);
    }
     /**
      * Metodo para imprimir las colas, la de espera y la de listos. Esto es usado cada vez que se hacen modificaciones a estas colas
      */
    public static void imprColas(){
        System.out.println("Cola de procesos en espera");
        ProcesosEspera.imprimirCola();
        System.out.println("Cola de procesos listos para ejecucion con capacidad " + ProcesosListos.getCapacidad() + "kiB");
        ProcesosListos.imprimirCola();
        System.out.println("");
    }
    
}
