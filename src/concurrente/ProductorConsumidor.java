/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concurrente;

import java.util.Random;


/**
 *
 * @author westernsquad
 */
public class ProductorConsumidor {
    private static volatile int product;
    private static volatile boolean continuar; //en este caso da un poco igual
    private static void sleep(int bound){ //rango
        try{
            Thread.sleep(bound);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    private static void producer(){
        while (true) {
            while(continuar){
                sleep(1000);
            }
            product= new Random().nextInt(1000);
            System.out.println("he producido "+product);
            sleep(20);
            continuar = true;
        }
    }
    private static void consumer (){
        while (true) {
        while (!continuar){
            sleep(5);
        }/*Thread.onSpinWait();*/
        System.out.println("El producto es " +product);
        product = -1;
        sleep(1000);
        continuar = false;
        }
        
    }
    public static void main(String[] args) {
            new Thread(() -> producer()).start();
            new Thread(()-> consumer()).start();
        
        
        
    }
    
}

