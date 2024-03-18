package planificadorrr;

public class LinkedList {
    private Proceso primero;
    private Integer numElementos;
    
    public LinkedList() {
        this.primero = null;
        this.numElementos = 0;
    }
    
    // Método para determinar si la lista está vacía
    public boolean esVacia() {
        return numElementos == 0;
    }
    
    public void agregarAlInicio(Proceso nuevoProceso) {
        if (primero == null) {
            primero = nuevoProceso;
        } else {
            nuevoProceso.setProcesoSiguiente(primero);
            this.setPrimero(nuevoProceso);
        }
        numElementos++;
    }

    public Proceso getPrimero() {
        return this.primero;
    }


    public void setPrimero(Proceso primero) {
        this.primero = primero;
    }
    
    public void mostrarProcesos() {
        Proceso actual = primero;
        while (actual != null) {
            actual.imprimirProceso();
            actual = actual.getProcesoSiguiente();
        }
        System.out.println();
    }
    
        // Método para eliminar el primer elemento de la lista
    public void eliminarPrimero() {
        if (!esVacia()){
            Proceso tmp = this.primero;
            this.primero = this.primero.getProcesoSiguiente();
            tmp = null;
            this.numElementos--;
        }
    }
    
    
public void ordenarPorTiempoLlegada() {
    if (esVacia() || this.numElementos == 1) {
        return;
    }

    boolean intercambioRealizado;
    do {
        intercambioRealizado = false;
        Proceso actual = this.primero;
        Proceso siguiente = actual.getProcesoSiguiente();

        while (siguiente != null) {
            if (actual.getTiempoLlegada() > siguiente.getTiempoLlegada()) {
                intercambiarNodos(actual, siguiente);
                intercambioRealizado = true;
            }

            actual = siguiente;
            siguiente = actual.getProcesoSiguiente();
        }
    } while (intercambioRealizado);
}

private void intercambiarNodos(Proceso actual, Proceso siguiente) {
    if (actual == this.primero){
        actual.ProcesoSiguiente = actual.ProcesoSiguiente.ProcesoSiguiente;
        siguiente.ProcesoSiguiente = actual;
        this.setPrimero(siguiente);
    }
    else{
        actual.ProcesoSiguiente = actual.ProcesoSiguiente.ProcesoSiguiente;
        siguiente.ProcesoSiguiente = actual;
    }
    }
}

