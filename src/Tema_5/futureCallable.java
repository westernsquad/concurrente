/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tema_5;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author westernsquad
 */

public class futureCallable {
    public static void countVowel(String word){
        int vowels = 0;
        for (int i = 0; i< word.length();i++){
            vowels+= (isVowel(word.charAt(i)))?1:0;
        }
    }
    public static boolean isVowel (char letter){
        return (letter == 'a')|| (letter == 'A')||(letter == 'e')|| (letter == 'E')||(letter == 'i')|| (letter == 'I')
                ||(letter == 'o')|| (letter == 'O')||(letter == 'u')|| (letter == 'U');
    }
    public static void main(String[] args) throws InterruptedException, ExecutionException  {
        int vowels = 0;
        ExecutorService pool = Executors.newCachedThreadPool();
        String path = "prueba";
        List<Future<Integer>> results = new ArrayList<>();
        try (BufferedReader bf = new BufferedReader(new FileReader(path))){
            String line = null;
            while ((line = bf.readLine())!= null){
                String[] words = line.split("");
                for (String word : words){
                    Future <Integer>counter = (Future <Integer>) pool.submit(()-> countVowel(word));
                    results.add(counter);
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        for (Future<Integer> result :results){
            vowels+=result.get();
        }
        pool.shutdown();
        pool.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("Vowels :" + vowels);
                
    }
    
}
