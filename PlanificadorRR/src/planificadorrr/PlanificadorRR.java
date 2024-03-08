/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package planificadorrr;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alexis
 */
public class PlanificadorRR {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            Proceso P1 = new Proceso();
            Proceso P2 = new Proceso();
            Proceso P3 = new Proceso();
            P1.setNombre("P1");
            P2.setNombre("P2");
            P3.setNombre("P3");
            Cola C1 = new Cola();
            C1.encolar(P1);
            C1.imprimirCola();
            TimeUnit.SECONDS.sleep(2);
            C1.encolar(P3);
            C1.imprimirCola();
            TimeUnit.SECONDS.sleep(2);
            C1.encolar(P2);
            C1.imprimirCola();
            System.out.println(C1.getNumElementos());
            TimeUnit.SECONDS.sleep(2);
            C1.desencolar();
            C1.imprimirCola();
            TimeUnit.SECONDS.sleep(2);
            C1.desencolar();
            C1.imprimirCola();
            TimeUnit.SECONDS.sleep(2);
            C1.desencolar();
            C1.imprimirCola();
            System.out.println(C1.getNumElementos());
        } catch (InterruptedException ex) {
            Logger.getLogger(PlanificadorRR.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
