/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrente.Tema4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author westernsquad
 */
public class EDList {
    public static List<String> l;
    
    public static void process (int id){
        try {
            for (int i =0; i<5 ;i++){
            Thread.sleep((long)(Math.random()*500));
            l.add("TH"+id+"_IT"+i);
        }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
    }
    public static void main(String[] args) throws InterruptedException  {
        l = Collections.synchronizedList(new ArrayList<>());
        List<Thread> ths= new ArrayList<>();
        
        for (int i = 0 ;i <10 ;i++){
            int id = i+1;
            ths.add(new Thread (()->{
                process(id);
            }));
        }
        
        for(Thread th : ths){
            th.start();
        }
        
        for (Thread th :ths){
            th.join();
        }
        System.out.println(l);
    }
}
