/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrente;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author westernsquad
 */
public class Concurrente {

    private static int n;
    private static void sleep(){
        try {
            Thread.sleep(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args) {
        // TODO code application logic here
        n=10;
        List<Integer>a = new ArrayList<>();
       /* new Thread(() -> {
            System.out.println("thread 1 " + (n+1));
        }).start();*/
        
        new Thread(()-> n++).start();
        
       /* new Thread(() -> {
            System.out.println("thread 2 " + (n-1));
        }).start();*/
        new Thread(()-> n--).start();
        
        System.out.println(+n);
        
        
        
        new Thread(() -> {
            for (int i = 0 ; i< 5 ; i++){
                System.out.println("XXX");
                sleep();
            }
        }).start();
        new Thread(() -> {
            for (int i = 0 ; i< 5 ; i++){
                System.out.println("---");
                sleep();
            }
        }).start();
        new Thread(() -> {
                System.out.println("***");
                sleep();//para forzar el cambio de contexto
            
        }).start();
        
         
        }
}

