/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrente.Tema4;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author westernsquad
 */
public class Syncronize {
    private static int x;
    private static Object xLock = new Object();
    private static Object yLock = new Object();

    private static int y;
    public static void incx(){
        synchronized(xLock){
        x = x+1;
        }
    }
    
     public static void decx(){
        synchronized(xLock){
        x = x-1;
        }
    }
     public static void incy(){
        synchronized(yLock){
        y = y+1;
        }
    }
    
     public static void decy(){
        synchronized(yLock){
        y = y-1;
        }
    }
    
    public static void main(String[] args) throws InterruptedException {
        List<Thread> ths = new ArrayList<>();
         for (int i =0 ;i<10;i++){
             ths.add(new Thread(()->incx()));
            }
         for (int i =0 ;i<10;i++){
             ths.add(new Thread(()->incy()));
            }
         for (int i =0 ;i<10;i++){
             ths.add(new Thread(()->decx()));
            }
          for (int i =0 ;i<10;i++){
             ths.add(new Thread(()->decy()));
            }
    for (Thread th : ths){
        th.join();
    }
    for (Thread th : ths){
        th.join();
    }
        System.err.println(x);
    }
           
          
    
}
