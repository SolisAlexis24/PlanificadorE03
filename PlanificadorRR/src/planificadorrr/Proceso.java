package planificadorrr;
/**
 *
 * @author Equipo03
 */

public class Proceso {
    private String ID;
    private String Nombre;
    private Integer Tamano, TiempoEjecucion, TiempoLlegada;
    Proceso ProcesoSiguiente;

    /**
     * Constructor del proceso
     */
    public Proceso() {
        this.ProcesoSiguiente = null;
    }

    
    
    public String getID() {
        return ID;
    }

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

    public Proceso getProcesoSiguiente() {
        return ProcesoSiguiente;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

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
    /**
     * Proceso que imprime el nombre del proceso
     */
    public void imprimirProceso(){
        System.out.print("[");
        System.out.print(this.Nombre);
        System.out.print("]");
    }
    
}
