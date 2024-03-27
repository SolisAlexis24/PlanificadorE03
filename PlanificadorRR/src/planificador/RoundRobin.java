package planificador;

import java.util.Objects;

/**
 *
 * @author Alexis
 */
public class RoundRobin {

    private Integer quantum, currentTime, timeEjec;
    private Proceso CPU, auxiliar;

    public RoundRobin() {
        this.quantum = 4;
        this.currentTime = 0;
        this.timeEjec = 0;
        this.CPU = null; //Proceso que indica cual es el proceso que se esta ejecutando actualmente
        this.auxiliar = null; //Proceso temporal para evitar escribir cada vez la linea PlanificadorRR.Procesos.getFirst()
    }

    public boolean checkFitOn() {
        Proceso tmp = Planificador.ProcesosEspera.getInicial();
        if(tmp.getTamano() <= Planificador.ProcesosListos.getCapacidad()){
            return true;
        }
        return false;
    }
    public void passPEtoPL() { //Envia el proceso del inicio de la cola de procesos en espera al final de la cola de procesos listos para ejecucion
        Proceso tmp = Planificador.ProcesosEspera.getInicial();
        Planificador.ProcesosListos.encolar(tmp);
        Planificador.ProcesosEspera.desencolar();
    }
    
    // Método para obtener el índice de un proceso por su nombre
    public int find(String nombre){
        Proceso tmp;
        for(int i = 0; i<Planificador.ProcesosAux.size(); i++){
            tmp = Planificador.ProcesosAux.get(i);
            if(tmp.getNombre().equals(nombre)) return i;
        }
        return -1;
    }
    
    public void calculateTimes(){
        Integer suma = 0;
        float total = 0;
        //Calcular tiempo promedio de espera
        for(Proceso tmp : Planificador.ProcesosAux){
            suma +=  tmp.getTiempoEspera();
        }
        total = suma/Planificador.ProcesosAux.size();
        System.out.println("Tiempo promedio de espera: " + total + " ms");
        suma = 0;
        total = 0;
        //Calcular tiempo promedio de ejecucion
        for(Proceso tmp : Planificador.ProcesosAux){
            suma +=  tmp.getTiempoEjecucion();
        }
        total = suma/Planificador.ProcesosAux.size();
        System.out.println("Tiempo promedio de ejecucion: " + total + " ms");
        suma = 0;
        total = 0;
        //Calcular tiempo promedio de respuesta
        for(Proceso tmp : Planificador.ProcesosAux){
            suma +=  tmp.getTiempoRespuesta();
        }
        total = suma/Planificador.ProcesosAux.size();
        System.out.println("Tiempo promedio de respuesta: " + total + " ms");
        suma = 0;
        total = 0;
    }
    
    public void Run() {
        //Mientras la lista de procesos que el usuario ingresa (los que aun no han llegado), la cola de procesos en espera y la cola de procesos listos para ejecución no estén vacías 
        while(!Planificador.Procesos.isEmpty() || !Planificador.ProcesosEspera.esVacia() || !Planificador.ProcesosListos.esVacia() || this.CPU != null){         
            if (!Planificador.Procesos.isEmpty()){ //Si hay elementos en la lista de procesos que aun no llegan sigue teniendo elementos 
                auxiliar = Planificador.Procesos.getFirst();
                while(Objects.equals(auxiliar.getTiempoLlegada(), this.currentTime)){ //Mientras el tiempo de llegada del primer proceso de la lista de procesos aun no llegan sea igual al tiempo actual (Esto para los procesos que llegan al mismo tiempo)
                    Planificador.ProcesosEspera.encolar(auxiliar); //Se encola el proceso en los procesos en espera
                    System.out.println("El proceso "+   auxiliar.getNombre()+" ha llegado en el ms " + this.currentTime.toString()+ " y se forma en la cola de procesos en espera");
                    Planificador.Procesos.removeFirst(); //Se remueve el proceso que ya ha sido encolado
                    Planificador.imprColas();                   
                    if (Planificador.Procesos.isEmpty()) break; //Si ya no hay procesos en la lista, el while se rompe
                    else auxiliar = Planificador.Procesos.getFirst(); //Se vuelve a colocar a tmp como el primero de la lista
                }        
            }
            if(!Planificador.ProcesosEspera.esVacia()){ 
                while (Planificador.ProcesosEspera.getInicial().getTamano() <= Planificador.ProcesosListos.getCapacidad()) { //Revisar si existe espacio dentro de la memoria primaria. Se hace con un while para meter a todos los procesos que quepan
                    System.out.println("El proceso " + Planificador.ProcesosEspera.getInicial().getNombre() + " cambia a la cola primaria en el ms " + this.currentTime.toString());
                    Planificador.ProcesosListos.setCapacidad(Planificador.ProcesosListos.getCapacidad() - Planificador.ProcesosEspera.getInicial().getTamano());
                    this.passPEtoPL();
                    Planificador.imprColas();
                    if(Planificador.ProcesosEspera.getInicial() == null)break; //Si ya no hay mas procesos en la cola de espera el while se rompe
                }                
            }
                
            if (!Planificador.ProcesosListos.esVacia()) { //Si hay elementos en la cola de procesos listos
                if(this.CPU == null){ //Si no hay ningun proceso en CPU
                    Planificador.ProcesosListos.setCapacidad(Planificador.ProcesosListos.getCapacidad()+Planificador.ProcesosListos.getInicial().getTamano()); //Se reestablece el tamano para simular que el proceso salio de RAM
                    this.CPU = Planificador.ProcesosListos.getInicial(); //Se sube a CPU
                    Planificador.ProcesosListos.desencolar(); //Se quita al proceso que se ejetuta
                    int idx = this.find(CPU.getNombre());
                    auxiliar = Planificador.ProcesosAux.get(idx);
                    if (this.CPU.getTiempoTotalEjecucion() == auxiliar.getTiempoTotalEjecucion()){ //Si es la primera vez que se ejecuta entonces su tiempo sera el mismo en los dos lugares. Este codigo solo se debe ejecutar una vez por proceso
                        auxiliar.setTiempoPrimEjec(this.currentTime);
                        auxiliar.setTiempoRespuesta((auxiliar.getTiempoPrimEjec()-auxiliar.getTiempoLlegada())); //Tiempo de respuesta = Tiempo de primera ejecucion - Tiempo de llegada
                    }
                    System.out.println("El proceso " + CPU.getNombre() + " ha subido a CPU para ejecutarse en el ms " + this.currentTime.toString());
                    Planificador.imprColas();
                }
            }
            this.currentTime++;
            if(this.CPU!=null){ //Si hay algun proceso en ejecucion
                if (this.timeEjec < this.quantum && this.CPU.getTiempoTotalEjecucion() > 0){ //Si el tiempo que el proceso se ha ejecutado es menor al quantum y el proceso sigue teniendo vida
                    CPU.setTiempoTotalEjecucion(this.CPU.getTiempoTotalEjecucion()-1);
                    this.timeEjec++;
                    System.out.println("El proceso "+ this.CPU.getNombre() + " se ha ejecutado " + this.timeEjec + " ms y le quedan " + this.CPU.getTiempoTotalEjecucion() + " ms");
                }
                else if((this.timeEjec == this.quantum) && this.CPU.getTiempoTotalEjecucion()>0){ //Si el tiempo que se ha ejecutado es el mismo que el del quantum (se acabo su tiempo de ejecutarse en CPU) y el proceso sigue teniendo vida
                    this.currentTime--; //Quitar un segundo de current time para que no afecte el tiempo de subir a cpu del siguiente proceso
                    System.out.println("El proceso " + this.CPU.getNombre() + " cambia a la cola secundaria en el ms " + this.currentTime.toString());
                    Planificador.ProcesosEspera.encolar(this.CPU);
                    this.CPU = null;
                    this.timeEjec = 0;
                    Planificador.imprColas();
                }
                else if ((this.timeEjec == this.quantum) && CPU.getTiempoTotalEjecucion() == 0){ //Si el tiempo que se ha ejecutado es el mismo que el del quantum (se acabo su tiempo de ejecutarse en CPU) y el proceso ya no tiene vida
                    this.currentTime--; //Quitar un segundo de current time para que no afecte el tiempo de subir a cpu del siguiente proceso
                    int idx = this.find(CPU.getNombre());
                    auxiliar = Planificador.ProcesosAux.get(idx);
                    auxiliar.setTiempoUltEjec(this.currentTime-this.timeEjec); // El tiempo de inicio de la ultima ejecucion es el tiempo actual menos el tiempo que se ejecuto el proceso
                    auxiliar.setTiempoEspera(auxiliar.getTiempoUltEjec()-auxiliar.getTiempoLlegada()-(auxiliar.getTiempoTotalEjecucion()-this.timeEjec));  // Tiempo de espera = Tiempo de inicio de la ultima ejecucion - Tiempo de llegada - Tiempo de ejecucion antes de la ultima rafaga
                    auxiliar.setTiempoEjecucion(this.currentTime-auxiliar.getTiempoLlegada()); // Tiempo de ejecucion = Tiempo de termino de ultima ejecucion - Tiempo de llegada
                    System.out.println("El proceso " + CPU.getNombre() + " ha terminado con su tiempo de ejecución en el ms " + this.currentTime.toString());
                    CPU = null;
                    this.timeEjec = 0;
                    Planificador.imprColas();     
                }  
                else if(CPU.getTiempoTotalEjecucion()==0){
                    this.currentTime--; //Quitar un segundo de current time para que no afecte el tiempo de subir a cpu del siguiente proceso
                    int idx = this.find(CPU.getNombre());
                    auxiliar = Planificador.ProcesosAux.get(idx);
                    auxiliar.setTiempoUltEjec(this.currentTime-this.timeEjec); // El tiempo de inicio de la ultima ejecucion es el tiempo actual menos el tiempo que se ejecuto el proceso
                    auxiliar.setTiempoEspera(auxiliar.getTiempoUltEjec()-auxiliar.getTiempoLlegada()-(auxiliar.getTiempoTotalEjecucion()-this.timeEjec));  // Tiempo de espera = Tiempo de inicio de la ultima ejecucion - Tiempo de llegada - Tiempo de ejecucion antes de la ultima rafaga
                    auxiliar.setTiempoEjecucion(this.currentTime-auxiliar.getTiempoLlegada()); // Tiempo de ejecucion = Tiempo de termino de ultima ejecucion - Tiempo de llegada
                    System.out.println("El proceso " + CPU.getNombre() + " ha terminado con su tiempo de ejecución en el ms " + this.currentTime.toString());
                    CPU = null;
                    this.timeEjec = 0;        
                    Planificador.imprColas();
                }
            }
        }
        this.calculateTimes();
    }
}
