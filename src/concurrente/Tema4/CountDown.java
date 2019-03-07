/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrente.Tema4;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author westernsquad
 */
public class CountDown {
    private static int NRUNNERS = 10;
    private static CountDownLatch latch = new CountDownLatch(4);
    
    public static void runner (int id ){
        System.out.println("READY: "+id);
        try {
            latch.await();
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("RUNNING: "+id);
        
            
            
    }
    
    public static void judge() {
        for (int i=3; i >= 0; i--) {
            
            try {
                Thread.sleep(1000);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
            System.out.println("COUNTER "+ i);
            latch.countDown();
       }
}
    
    
    public static void main(String[] args) {
        new Thread(()-> judge()).start();
        for (int i = 0 ; i < NRUNNERS ; i++){
            int id = i+1;
            new Thread (()-> runner(id)).start();
        }
    }
}
