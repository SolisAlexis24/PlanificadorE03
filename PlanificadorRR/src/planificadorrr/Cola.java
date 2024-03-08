/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package planificadorrr;

/**
 *
 * @author Equipo03
 */
public class Cola {
    Integer NumElementos;
    Integer Capacidad;
    Proceso Inicial;
    Proceso Final;
    /**
     * Constructor de la cola, inicializa los valores por defecto de la cola
     */
    public Cola() {
        this.NumElementos = 0;
        this.Inicial = null;
        this.Final = null;
    }

    public Integer getNumElementos() {
        return NumElementos;
    }

    public Proceso getInicial() {
        return Inicial;
    }

    public Proceso getFinal() {
        return Final;
    }

    public void setCapacidad(Integer Capacidad) {
        this.Capacidad = Capacidad;
    }
    
    
   public boolean esVacia(){
       return this.NumElementos==0;
   }
    /**
     * Proceso que encola o añade un nuevo elemento a la cola
     * @param Pnuevo Este proceso es el que se añadirá a la cola
     */
    public void encolar(Proceso Pnuevo){
        if (esVacia()){
            this.Inicial = this.Final = Pnuevo;
            this.NumElementos++;
        }else{
            this.Final.ProcesoSiguiente = Pnuevo;
            this.Final = Pnuevo;
            this.NumElementos++;
        }
    }
    /**
     * Proceso para desencolar o quitar un elemento a la cola
     */
    public void desencolar(){
        if (!esVacia()){
            if(this.Inicial == this.Final){ //Si sólo tiene un elemento
                this.Inicial = this.Final = null;
                this.NumElementos=0;
            }else{
                Proceso tmp = this.Inicial;
                this.Inicial = this.Inicial.getProcesoSiguiente();
                tmp = null;
                this.NumElementos--;
            }
        }
    }
    /**
     * Proceso para poder imprimir a los procesos de la cola
     */
    public void imprimirCola(){
        System.out.print("{");
        for(Proceso j = this.Inicial;j != null; j = j.getProcesoSiguiente()){
            j.imprimirProceso();
            System.out.print(" ");
        }
        System.out.println("}");
    }
}
