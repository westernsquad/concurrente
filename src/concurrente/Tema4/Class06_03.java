/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrente.Tema4;

import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author westernsquad
 */
public class Class06_03 {
    private static volatile int product;
    private static volatile boolean continuar; //en este caso da un poco igual
    private static BlockingQueue<String> array;
    private static void sleep(int bound){ //rango
        try{
            Thread.sleep(bound);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    private static void producer(int id) throws InterruptedException{
    
           for (int i = 0 ; i<10 ;i++){
               String product = "Productor "+id+"-> producto: "+(i+1);
               array.put(product);
               System.out.println("PRODUCCIENDO" + product);
               try {
                   Thread.sleep((long)(Math.random()*1500));
               } catch (InterruptedException ex) {
                   Logger.getLogger(Class06_03.class.getName()).log(Level.SEVERE, null, ex);
               }
       }
    }
    private static void consumer (int id) throws InterruptedException{
     
        while(true){
            String elem = array.take();
            System.out.println("CONSUMIDO"+id+": "+elem);
             try {
               Thread.sleep((long)(Math.random()*1500));
           } catch (InterruptedException ex) {
               Logger.getLogger(Class06_03.class.getName()).log(Level.SEVERE, null, ex);
           }
        }
       
        
    }
    public static void main(String[] args) {
        array = new ArrayBlockingQueue<>(5);
        for (int i = 0; i < 5; i++) {
            int id = i+1;
            array.add(new Thread(()->producer(id)));
        }
        for (Thread producer :array){
            producer.start();
        }
        
        
        
    }
}   
