/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tema_5;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 *
 * @author westernsquad
 */
public class FutureCallableEjer2 {
    public static BigInteger factorial (int num){
        BigInteger fac = BigInteger.ONE;
        while (num > 0){
            fac = fac.multiply(BigInteger.valueOf(num));
            num--;
        }
        return fac;
    }
    
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int max = 1000000;
        List <Future<BigInteger>> results = new ArrayList<>(); 
        for (int i = 0; i < max; i++) {
            int n = i ;
            results.add(pool.submit(()-> factorial(n)));
        }
        while (!results.isEmpty()){
            for (Future<BigInteger> result : results){

                if (result.isDone()){
                    System.out.println(result.get());
                    results.remove(result);
                    break;
                }
            }
        }
        pool.shutdown();
    }
}
