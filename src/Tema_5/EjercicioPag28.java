/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tema_5;
import java.util.Random;
import java.util.concurrent.*;
/**
 *
 * @author westernsquad
 */
public class EjercicioPag28 extends RecursiveTask<Integer> {
  
    private int[] v;
    private int start; // Límite inferior del array
    private int end; // Límite superior del array
    public EjercicioPag28(int[] v, int start, int end) {
    this.v = v;
    this.start = start;
    this.end = end;
    }
    @Override
    protected Integer compute() {
        if (end-start <= 2){
            int max = Integer.MIN_VALUE;
            for (int i = start; i < end; i++) {
                max = Math.max(max, v[i]);
            }
            return max;
        }else {
            int mid = (start+end)/2;
            EjercicioPag28 left = new EjercicioPag28(v, start, mid);
            EjercicioPag28 right = new EjercicioPag28(v, mid+1, end);
            left.fork();
            right.fork();
            int m1 = left.join();
            int m2 = right.join();
            return Math.max(m1,m2);
            
            
        }
        
       
    }
    
    public static int [] generar (int size){
        int [] v = new int[size];
        Random rdm = new Random();
        for (int i = 0; i < v.length; i++) {
            v[i]=rdm.nextInt(size*10);
        }
        return v;
    }
    
    private static final int SIZE = 100000000;
    public static void main(String[] args) {
        ForkJoinPool pool = new ForkJoinPool();
        int[] v = generar(SIZE);
        
        int m = pool.invoke(new EjercicioPag28(v, 0, v.length));
        int result = pool.invoke(new EjercicioPag28(v, 0, SIZE));
        System.out.println("MAX: "+result);
        pool.shutdown();
}
    
}
