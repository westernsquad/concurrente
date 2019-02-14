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
public class LectorEscritor {
    
    private static Semaphore emResource;
    private static Semaphore semReader;
    private static int readerCount;
    private static Semaphore queueSem;//semaforo que simula una cola
    
    private static void sleep (int bound){
         try {
            Thread.sleep(bound);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void writer(){
        while (true) {
        try {
            queueSem.acquire();
            emResource.acquire();
            queueSem.release();
            
        }catch (InterruptedException e){
            e.printStackTrace();
          
        } 
        System.out.println("Escritor escribiendo");
        sleep(1000);
        emResource.release();
        
        }
    }
    
    public static void reader(){
        while (true) {
        try{
            queueSem.acquire();
            semReader.acquire();
            readerCount++;
            if(readerCount == 1){//si soy el primero en pedir entonces entro
                
                emResource.acquire();
            }
            queueSem.release();
            semReader.release();
            System.out.println("Lector leyendo");
            sleep(1000);
            semReader.acquire();
            readerCount--;
            if(readerCount==0){
                emResource.release();
            }
            semReader.release();
            
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        }
    }
    
    public static void main(String[] args) {
        
    emResource = new Semaphore(1);
    semReader = new Semaphore(1);
    queueSem = new Semaphore(1);      
    readerCount = 0;
    
    for (int i =0 ;i<10;i++){
        new Thread(()-> reader()).start();
    }
    for (int i =0 ;i<1;i++){
        new Thread(()-> writer()).start();
    }
    
        
    }

    
}
