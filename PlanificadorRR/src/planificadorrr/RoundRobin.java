package planificadorrr;

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
        Proceso tmp = PlanificadorRR.ProcesosEspera.getInicial();
        if(tmp.getTamano() <= PlanificadorRR.ProcesosListos.getCapacidad()){
            return true;
        }
        return false;
    }
    public void passPEtoPL() { //Envia el proceso del inicio de la cola de procesos en espera al final de la cola de procesos listos para ejecucion
        Proceso tmp = PlanificadorRR.ProcesosEspera.getInicial();
        PlanificadorRR.ProcesosListos.encolar(tmp);
        PlanificadorRR.ProcesosEspera.desencolar();
    }

    
    public void Run() {
        //Mientras la lista de procesos que el usuario ingresa (los que aun no han llegado), la cola de procesos en espera y la cola de procesos listos para ejecución no estén vacías 
        while(!PlanificadorRR.Procesos.isEmpty() || !PlanificadorRR.ProcesosEspera.esVacia() || !PlanificadorRR.ProcesosListos.esVacia() || this.CPU != null){         
            if (!PlanificadorRR.Procesos.isEmpty()){ //Si hay elementos en la lista de procesos que aun no llegan sigue teniendo elementos 
                auxiliar = PlanificadorRR.Procesos.getFirst();
                while(Objects.equals(auxiliar.getTiempoLlegada(), this.currentTime)){ //Mientras el tiempo de llegada del primer proceso de la lista de procesos aun no llegan sea igual al tiempo actual (Esto para los procesos que llegan al mismo tiempo)
                    PlanificadorRR.ProcesosEspera.encolar(auxiliar); //Se encola el proceso en los procesos en espera
                    System.out.println("El proceso "+   auxiliar.getNombre()+" ha llegado en el ms " + this.currentTime.toString()+ " y se forma en la cola de procesos en espera");
                    PlanificadorRR.Procesos.removeFirst(); //Se remueve el proceso que ya ha sido encolado
                    PlanificadorRR.imprColas();                   
                    if (PlanificadorRR.Procesos.isEmpty()) break; //Si ya no hay procesos en la lista, el while se rompe
                    else auxiliar = PlanificadorRR.Procesos.getFirst(); //Se vuelve a colocar a tmp como el primero de la lista
                }        
            }
            if(!PlanificadorRR.ProcesosEspera.esVacia()){ 
                while (PlanificadorRR.ProcesosEspera.getInicial().getTamano() <= PlanificadorRR.ProcesosListos.getCapacidad()) { //Revisar si existe espacio dentro de la memoria primaria. Se hace con un while para meter a todos los procesos que quepan
                    System.out.println("El proceso " + PlanificadorRR.ProcesosEspera.getInicial().getNombre() + " cambia a la cola primaria en el ms " + this.currentTime.toString());
                    PlanificadorRR.ProcesosListos.setCapacidad(PlanificadorRR.ProcesosListos.getCapacidad() - PlanificadorRR.ProcesosEspera.getInicial().getTamano());
                    this.passPEtoPL();
                    PlanificadorRR.imprColas();
                    if(PlanificadorRR.ProcesosEspera.getInicial() == null)break; //Si ya no hay mas procesos en la cola de espera el while se rompe
                }                
            }
                
            if (!PlanificadorRR.ProcesosListos.esVacia()) { //Si hay elementos en la cola de procesos listos
                if(this.CPU == null){ //Si no hay ningun proceso en CPU
                    PlanificadorRR.ProcesosListos.setCapacidad(PlanificadorRR.ProcesosListos.getCapacidad()+PlanificadorRR.ProcesosListos.getInicial().getTamano()); //Se reestablece el tamano para simular que el proceso salio de RAM
                    this.CPU = PlanificadorRR.ProcesosListos.getInicial(); //Se sube a CPU
                    PlanificadorRR.ProcesosListos.desencolar(); //Se quita al proceso que se ejetuta
                    System.out.println("El proceso " + CPU.getNombre() + " ha subido a CPU para ejecutarse en el ms " + this.currentTime.toString());
                }
            }
            this.currentTime++;
            if(this.CPU!=null){ //Si hay algun proceso en ejecucion
                if (this.timeEjec < this.quantum && this.CPU.getTiempoEjecucion() > 0){ //Si el tiempo que el proceso se ha ejecutado es menor al quantum y el proceso sigue teniendo vida
                    CPU.setTiempoEjecucion(this.CPU.getTiempoEjecucion()-1);
                    this.timeEjec++;
                    System.out.println("El proceso "+ this.CPU.getNombre() + " se ha ejecutado " + this.timeEjec + " ms y le quedan " + this.CPU.getTiempoEjecucion() + " ms");
                }
                else if((this.timeEjec == this.quantum) && this.CPU.getTiempoEjecucion()>0){ //Si el tiempo que se ha ejecutado es el mismo que el del quantum (se acabo su tiempo de ejecutarse en CPU) y el proceso sigue teniendo vida
                    this.currentTime--; //Quitar un segundo de current time para que no afecte el tiempo de subir a cpu del siguiente proceso
                    System.out.println("El proceso " + this.CPU.getNombre() + " cambia a la cola secundaria en el ms " + this.currentTime.toString());
                    PlanificadorRR.ProcesosEspera.encolar(this.CPU);
                    this.CPU = null;
                    this.timeEjec = 0;
                    PlanificadorRR.imprColas();
                }
                else if ((this.timeEjec == this.quantum) && CPU.getTiempoEjecucion() == 0){ //Si el tiempo que se ha ejecutado es el mismo que el del quantum (se acabo su tiempo de ejecutarse en CPU) y el proceso ya no tiene vida
                    this.currentTime--; //Quitar un segundo de current time para que no afecte el tiempo de subir a cpu del siguiente proceso
                    System.out.println("El proceso " + CPU.getNombre() + " ha terminado con su tiempo de ejecución en el ms " + this.currentTime.toString());
                    CPU = null;
                    this.timeEjec = 0;
                    PlanificadorRR.imprColas();
                }  
                else if(CPU.getTiempoEjecucion()==0){
                    this.currentTime--; //Quitar un segundo de current time para que no afecte el tiempo de subir a cpu del siguiente proceso
                    System.out.println("El proceso " + CPU.getNombre() + " ha terminado con su tiempo de ejecución en el ms " + this.currentTime.toString());
                    CPU = null;
                    this.timeEjec = 0;        
                    PlanificadorRR.imprColas();
                }
            }
        }
    }
}
