/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tema_5;

import java.math.BigInteger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;




/**
 *
 * @author westernsquad
 */
public class ejerc1 {
    private static final int size =1000;
    
    public static void execute (int id){
        Random rnd = new Random();
        System.out.println("Starting Thread");
        List <BigInteger> l = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            l.add(BigInteger.probablePrime(256, rnd));
        }
        BigInteger max = BigInteger.ZERO;
        for (int i = 0; i < size; i++) {
            BigInteger next = l.get(i);
            if (next.compareTo(max)>0){
                max = next;
            }
        }
        System.out.println("Max value in thread"+id+": "+ max);
    }
    public static void main(String[] args) {
        long timeIni= System.currentTimeMillis();
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime()).ava
        for (int i=0; i <20;i++){
            int id =i+1;
            pool.execute(()->execute(id));
        }
        pool.shutdown();
        pool.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        double secs = (System.currentTimeMillis()-timeIni);
        
   }
    
}
