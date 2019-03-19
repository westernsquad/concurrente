/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tema_5;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 *
 * @author westernsquad
 */
public class DivideYVenceras {
    private static final int TH_SEQ = 2;
    public static int findMax (int[] array , int start , int end){
        if(end-start <= TH_SEQ){
            int max = Integer.MIN_VALUE;
            for (int i = start; i < end; i++) {
                max = Math.max(max, array[i]);
            }
            return max;
        }else{
            int mid = (start+end)/2;
            int m1 = findMax(array,start,mid);
            int m2 = findMax (array,mid+1,end);
            return Math.max(m1, m2);
            
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
    public static void main(String[] args) {
        int [] v = generar(10);
        int m = findMax(v, 0, v.length);
        System.out.println(Arrays.toString(v));
        System.out.println("Max: "+m);
    }
    
}
