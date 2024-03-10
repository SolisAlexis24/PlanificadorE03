import java.util.Scanner;
import planificadorrr.Menu;

/**
 *
 * @author Equipo03
 */

public class Main {
    public static void main(String[] args) { 
        System.out.println("SIMULADOR DE ROUND ROBIN");
        Scanner scanner = new Scanner(System.in);
        Menu.menu(scanner); 
    }
}

