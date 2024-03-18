/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package planificadorrr;
import java.util.Scanner;

/**
 *
 * @author Alexis
 */
public class PlanificadorRR {
    static Cola ProcesosEspera = new Cola();
    static Cola ProcesosListos = new Cola();
    static LinkedList Procesos = new LinkedList();
    /**
     * @param args the command line arguments
     */
    public static void imprColas(){
        System.out.println("Cola de procesos en espera");
        ProcesosEspera.imprimirCola();
        System.out.println("Cola de procesos listos para ejecucion");
        ProcesosListos.imprimirCola();
    }
    
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Ingrese la capacidad de la cola de procesos listos para ejecución en kB");
        Integer capacidad = scanner1.nextInt();
        ProcesosListos.setCapacidad(capacidad);
        
        Scanner scanner2 = new Scanner(System.in);
        Menu2.showMenu(scanner2);
    }
    
}
