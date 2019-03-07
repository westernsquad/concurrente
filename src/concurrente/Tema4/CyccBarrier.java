/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrente.Tema4;

/**
 *
 * @author westernsquad
 */
import java.util.concurrent.BrokenBarrierException;

public class CyccBarrier {
    private static final int NPROC = 4;
    private static CyclicBarrier barrier;
    
    public static void writer (int id) {
        try{
            while(true){
                for (int i = 0 ; i <id;i++){

                    System.out.println("\t");
                }
                System.out.println("A");
                Thread.sleep(1000);
                barrier.await();
            }
        }catch(InterruptedException e | BrokenBarrierException e){
            e.printStackTrace();
        }
    }
    
    
    public static void main(String[] args) {
        barrier = new  CyclicBarrier(NPROC, () -> System.out.println("*"););
        for (int i = 0 ; i <NPROC ;i++){
            int id = i;
            new Thread(() -> writer(id)).start();
        }
    }
}
