/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrente;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author westernsquad
 */
public class semaforo_2_Exclusion_Mutua {
    private static Semaphore semME;
    
    public static void process(){
        try {
            semME.acquire();
          
        } catch (InterruptedException ex) {
            Logger.getLogger(semaforo_2_Exclusion_Mutua.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("SC1");
        System.out.println("SC2");
        semME.release();
        System.out.println("SN1");
        System.out.println("SN2");
    }
    public static void main(String[] args) {
        semME = new Semaphore (1);
        for (int i = 0 ; i < 5 ;i++){
            new Thread(()-> process()).start();
        }
        
    }
    
}
/*
test
de secuencial a concurrente
concurrente map 
consultas string builder
*/