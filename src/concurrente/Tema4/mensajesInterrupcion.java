/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrente.Tema4;

import java.io.IOException;
import java.math.BigInteger;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author westernsquad
 */
public class mensajesInterrupcion {
    private static int aux;
    public static void mensaje (){
        String[] frases = new String []{
             "MSJ: La vida es bella"+
                "MSJ: O no..."+
                "MSJ: Los pajaritos cantan"+
                "MSJ: Y molestan..."
        };
        for (int i = 0 ; i < frases.length;i++){
            System.out.println(frases[i]);
            try{
                Thread.sleep(2000);
                
            }catch(InterruptedException e){
                System.out.println("MSJ: se acabo!");
                return;
            }
    
        }       
    }
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
        mensaje();});
        t.start();
        int iters = 0;
        boolean continuar = true ;
        while (continuar){
            if (!t.isAlive()){
                continuar = false;
            }else{
                try{
                Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                if (iters == 5){
                    System.out.println("MAIN: Cansado de esperar !");
                    t.interrupt();
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    continuar = false;

                }else{
                    System.out.println("MAIN: Todavia sperando ....");
                    iters++;
                } 
            }
                      
        }
        System.out.println("Por fin!");
}
}
