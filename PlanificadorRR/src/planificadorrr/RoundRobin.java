package planificadorrr;

import java.util.Objects;

/**
 *
 * @author Alexis
 */
public class RoundRobin {

    private Integer quantum, currentTime, timeEjec;

    public RoundRobin() {
        this.quantum = 4;
        this.currentTime = 0;
        this.timeEjec = 0;
    }

    public boolean checkFitOn() {
        Proceso tmp = PlanificadorRR.ProcesosEspera.getInicial();
        if(tmp.getTamano() <= PlanificadorRR.ProcesosListos.getCapacidad()){
            return true;
        }
        return false;
    }

    public void passPLtoPE() { //Encia el proceso del inicio de la cola de procesos listos al final de la cola de procesos en espera
        Proceso tmp = PlanificadorRR.ProcesosEspera.getInicial();
        PlanificadorRR.ProcesosListos.encolar(tmp);
        PlanificadorRR.ProcesosEspera.desencolar();
    }

    public void passPEtoPL() { //Encia el proceso del inicio de la cola de procesos espera al final de la cola de procesos listos
        Proceso tmp = PlanificadorRR.ProcesosListos.getInicial();
        PlanificadorRR.ProcesosEspera.encolar(tmp);
        PlanificadorRR.ProcesosListos.desencolar();
    }

    public void Run() {
        Proceso tmp;
        while(!PlanificadorRR.Procesos.esVacia() || !PlanificadorRR.ProcesosEspera.esVacia() || !PlanificadorRR.ProcesosListos.esVacia() ){
            tmp = PlanificadorRR.Procesos.getPrimero();
            if (tmp != null){
                if(Objects.equals(tmp.getTiempoLlegada(), this.currentTime)){ //Si el tiempo actual es el tiempo en el que llega un proceso
                PlanificadorRR.ProcesosEspera.encolar(tmp);
                System.out.println("El proceso "+   tmp.getNombre()+" ha llegado en el ms " + this.currentTime.toString()+ " y se forma en la cola de procesos en espera");
                PlanificadorRR.Procesos.eliminarPrimero();
                PlanificadorRR.imprColas();
                }        
            }
            if (PlanificadorRR.ProcesosEspera.getInicial().getTamano() <= PlanificadorRR.ProcesosListos.getCapacidad()) { //Revisar si existe espacio dentro de la memoria primaria
                System.out.println("El proceso " + PlanificadorRR.ProcesosEspera.getInicial().getNombre() + " cambia a la cola primaria en el ms " + this.currentTime.toString());
                PlanificadorRR.ProcesosListos.setCapacidad(PlanificadorRR.ProcesosListos.getCapacidad() - PlanificadorRR.ProcesosEspera.getInicial().getTamano());
                this.passPLtoPE();
                PlanificadorRR.imprColas();
            }
            if (!PlanificadorRR.ProcesosListos.esVacia()) { //Si hay elementos en la cola de procesos listos
                if (this.timeEjec < this.quantum && PlanificadorRR.ProcesosListos.getInicial().getTiempoEjecucion() > 0){ //Si el tiempo que el proceso se ha ejecutado el proceso es menor al quantum y el procedso sigue teniendo vida
                    PlanificadorRR.ProcesosListos.getInicial().setTiempoEjecucion(PlanificadorRR.ProcesosListos.getInicial().getTiempoEjecucion() - 1);
                    this.timeEjec++;
                }
                else if(Objects.equals(this.timeEjec, this.quantum)&& PlanificadorRR.ProcesosListos.getInicial().getTiempoEjecucion() > 0){ //Si el tiempo que se ha ejecutado es el mismo que el del quantum y el proceso sigue teniendo vida
                    System.out.println("El proceso " + PlanificadorRR.ProcesosListos.getInicial().getNombre() + " cambia a la cola secundaria en el ms" + this.currentTime.toString());
                    PlanificadorRR.ProcesosListos.setCapacidad(PlanificadorRR.ProcesosListos.getCapacidad() + PlanificadorRR.ProcesosListos.getInicial().getTamano());
                    this.passPLtoPE(); 
                    PlanificadorRR.imprColas();
                }
                else if(Objects.equals(this.timeEjec, this.quantum)&& PlanificadorRR.ProcesosListos.getInicial().getTiempoEjecucion() == 0){ //Si el tiempo que se ha ejecutado es el mismo que el del quantum y el proceso ya no teniene vida
                    System.out.println("El proceso " + PlanificadorRR.ProcesosListos.getInicial().getNombre() + " ha terminado con su tiempo de ejecución en el ms" + this.currentTime.toString());
                    PlanificadorRR.ProcesosListos.setCapacidad(PlanificadorRR.ProcesosListos.getCapacidad() + PlanificadorRR.ProcesosListos.getInicial().getTamano());
                    PlanificadorRR.ProcesosListos.desencolar(); // Sacar el proceso de la cola
                    PlanificadorRR.imprColas();
                }
            }
            
            this.currentTime++;
        }
    }
}
