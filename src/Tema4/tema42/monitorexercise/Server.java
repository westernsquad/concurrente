package tema42.monitorexercise;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {

    private ReentrantLock lock;
    private Condition opMaintenance;
    private Condition opRequest;
    private int numOpMaintenance;
    private int numOpRequest;

    public Server() {
        lock = new ReentrantLock();
        opMaintenance = lock.newCondition();
        opRequest = lock.newCondition();
        numOpMaintenance = 0;
        numOpRequest = 0;
    }

    public void startMaintenance(String name) throws InterruptedException {
        lock.lock();
        try {
            while ((numOpRequest > 0) || (lock.getWaitQueueLength(opRequest) > 0)){ // hay una petición web procesandose o esperando. Tienen prioridad las peticiones web
                System.out.println(name + " esperando ... servidor ocupado");
                opMaintenance.await(); // el mantenimiento tiene que esperar
            }
            numOpMaintenance++; // --incrementa el nu�?mero de operaciones de mantenimiento
            opMaintenance.signal(); // --despierta si hay otra operación de mantenimiento
            System.out.println("Empieza " + name);
        } finally { // Permite empezar las operaciones de mantenimiento
            lock.unlock();
        }
    }

    public void endMaintenance(String name) {
        lock.lock();
        try {
            System.out.println(name + " terminado ... " + ((this.numOpMaintenance== 1)?"Servidor Libre":""));
            numOpMaintenance--; // --disminuye el nro. de operaciones de mantenimiento
            if (numOpMaintenance == 0)
                opRequest.signal(); // -- la última operación de mantenimiento comunica a las petciones web que pueden empezar (si hubiera alguna)
        } finally {
            lock.unlock();
        }
    }

    public void startRequest(String name) throws InterruptedException {
        lock.lock();
        try {
            while ((numOpMaintenance > 0)) {
                System.out.println(name +" esperando ... servidor ocupado"); 	// --Hay operaciones de mantenimiento iniciadas
                opRequest.await(); // --esperar a que termine el mantenimiento
            }
            numOpRequest++; // --incrementa el no. de peticiones web
            opRequest.signal(); // --despierta a otra petición web si hubiera
            System.out.println("Empieza " + name);
        } finally {			// Permite empezar a procesar las peticiones web
            lock.unlock();
        }
    }

    public void endRequest(String name) throws InterruptedException {
        lock.lock();
        try {
            System.out.println(name + " terminado ... " + ((this.numOpRequest == 1)?"Servidor Libre":""));
            numOpRequest--; //--disminuye el num. de peticiones web
            if(numOpRequest == 0)
                opMaintenance.signal(); //-- la última petición web comunica a la operación de mantenimiento que espera (si hubiera) que puede intentar pasa
        } finally {
            lock.unlock();
        }
    }


}
