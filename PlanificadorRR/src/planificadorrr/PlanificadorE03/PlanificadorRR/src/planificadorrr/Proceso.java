package planificadorrr;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author Equipo03
 */

public class Proceso {

    private String ID;
    private String Nombre;
    private Integer Tamano, TiempoEjecucion, TiempoLlegada;
    Proceso ProcesoSiguiente;

    
    //CONSTRUCTOR
    public Proceso(int contador) {  //Constructor público del proceso
        this.ProcesoSiguiente = null;
        generarID(contador);
    }

    
    //GETTERS
    public String getNombre() {
        return Nombre;
    }

    public Integer getTamano() {
        return Tamano;
    }

    public Integer getTiempoEjecucion() {
        return TiempoEjecucion;
    }

    public Integer getTiempoLlegada() {
        return TiempoLlegada;
    }

    public String getID() { //Getter publico para retornar el valor del id
        return ID;
    }

    public Proceso getProcesoSiguiente() {
        return ProcesoSiguiente;
    }
    
    
    
    //SETTERS
    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public void setTamano(Integer Tamano) {
        this.Tamano = Tamano;
    }

    public void setTiempoEjecucion(Integer TiempoEjecucion) {
        this.TiempoEjecucion = TiempoEjecucion;
    }

    public void setTiempoLlegada(Integer TiempoLlegada) {
        this.TiempoLlegada = TiempoLlegada;
    }

    public void setProcesoSiguiente(Proceso ProcesoSiguiente) {
        this.ProcesoSiguiente = ProcesoSiguiente;   
    }
    
    //MÉTODOS
    private void generarID(Integer contador) { //método privado
    //Declaramos una variable tipo Date donde almacenamos la fecha
        Date todayDate = new Date();

    //Usamos el método SimpleDateFormat recuperamos día, mes y año 
        SimpleDateFormat diaActual = new SimpleDateFormat("dd");
        SimpleDateFormat mesActual = new SimpleDateFormat("MM");
        SimpleDateFormat anioActual = new SimpleDateFormat("yyyy");

    //Almacenamos día, mes y año en sus variables String correspondientes
        String dia = diaActual.format(todayDate);
        String mes = mesActual.format(todayDate);
        String anio = anioActual.format(todayDate);

        /* Generamos un identificador único con UUID
        UUID uuid = UUID.randomUUID();
        String uuidID = uuid.toString().replace("-", "").substring(0, 6); // Tomar solo los primeros 6 caracteres
         */
    // Creamos el ID del proceso concatenando año, mes, día e identificador
        this.ID = anio + mes + dia + contador;
    }

    
    public void imprimirInfoProceso() {
        System.out.println("");
        
        System.out.print("ID del proceso");
        System.out.println("[" + this.ID+"]");

        System.out.print("Nombre del proceso");
        System.out.println("["+this.Nombre+"]");

        System.out.print("Tamano del proceso");
        System.out.println("["+this.Tamano+"]");

        System.out.print("Tiempo de ejecucion del proceso");
        System.out.println("["+this.TiempoEjecucion+"]");

        System.out.print("Tiempo de llegada del proceso");
        System.out.println("["+this.TiempoLlegada+"]");
        
        System.out.println("");
    }

    public void imprimirProceso(){
        System.out.print("[");
        System.out.print(this.Nombre);
        System.out.print("]");

}
}
