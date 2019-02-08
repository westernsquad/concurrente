/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrente;

import java.util.concurrent.Semaphore;

/**
 *
 * @author westernsquad
 */
public class semaforo1 {
    
    private static Semaphore semSync;
    
    public static void procesoA(){
        System.out.println("PA1");
        semSync.release();
        System.out.println("PA2");
    }
    public static void procesoB(){
        System.out.println("PB1");
        
        try {
            semSync.acquire();
        }catch (InterruptedException e){
            e.printStackTrace();
        }
            
        System.out.println("PB2");
    }
    
    
    public static void main(String[] args) {
        semSync = new Semaphore(1);
        new Thread (()-> procesoA()).start();
        new Thread (()-> procesoB()).start();
    }
    
}
