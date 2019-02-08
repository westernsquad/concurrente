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
public class ClienteServidor {
    private static volatile double pedido;
    private static volatile boolean peticion;
    private static volatile boolean realizada;
    private static void sleep(int bound){ //rango
        try{
            Thread.sleep(bound);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    } 
    
    private static void cliente(){
        peticion = true;
        sleep(40);
        while(!realizada){
            sleep(50);
        }
        System.out.println("Pedido realizado" + pedido);
    }
    private static void servidor (){
        
        
    }
    
    public static void main(String[] args) {
            new Thread(() -> cliente()).start();
            new Thread(()-> servidor()).start(); 
    }
    
}
