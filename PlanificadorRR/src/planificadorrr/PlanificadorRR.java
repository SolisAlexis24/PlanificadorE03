/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package planificadorrr;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexis
 */
public class PlanificadorRR {
    static Cola ProcesosListos = new Cola();
    static Cola ProcesosListosEjec = new Cola();
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("Ingrese la capacidad de la cola de procesos listos para ejecución en kB");
        Integer capacidad = scanner1.nextInt();
        ProcesosListosEjec.setCapacidad(capacidad);
        
        Scanner scanner2 = new Scanner(System.in);
        Menu.showMenu(scanner2);
        ProcesosListos.imprimirCola();
    }
    
}
