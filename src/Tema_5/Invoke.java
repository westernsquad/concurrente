/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tema_5;

import static Tema_5.FutureCallableEjer2.factorial;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author westernsquad
 */
public class Invoke {
    
    public static void main(String[] args) {
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int id = 0;
        int max = 1000;
        List <Callable<String>> task = new ArrayList<>(); 
        for (int i = 0; i < max; i++) {
            id = i ;
            task.add(()-> "task"+ id));
        }
        String res = pool.invokeAny(task);
        
    }
    
    
    public static void tarea (int id){
        System.out.println("Task ID : "+id);
    }
}
