/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package HojaEjercicios;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author westernsquad
 */
public class Ejer1Concurrente {
    public static int[][] multiply(int[][] a, int[][] b) {
    int[][] c = new int[a.length][b[0].length];
    // se comprueba si las matrices se pueden multiplicar
    if (a[0].length == b.length) {
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                
                for (int k = 0; k < a[0].length; k++) {
                    c[i][j] += a[i][k] * b[k][j];
                }
            }
        }
    }
    return c;
}


public static void main(String[] args) {
    long TInicio, TFin, tiempo; //Variables para determinar el tiempo de ejecución
    TInicio = System.nanoTime(); 
    int[][] a = { { 1,1}, { 2,2} };
    int[][] b = { { 3,3 }, { 4, 4 }};   
    int[][] c = multiply(a, b);
    List<Thread> hilos = new ArrayList<Thread>();
    for (int fila=0; fila<a.length; fila++)
            for (int columna=0; columna<b[0].length; columna++)
            {
                Thread hilo = new Thread(()-> multiply(a,b));
                         
                hilos.add(hilo);
                hilo.start();
            }
    
     for (Thread hilo: hilos)
            try {
                hilo.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


    
    
    TFin = System.nanoTime(); //Tomamos la hora en que finalizó el algoritmo y la almacenamos en la variable T
    tiempo = TFin - TInicio; //Calculamos los milisegundos de diferencia
    System.out.println("Tiempo de ejecución en milisegundos: " + tiempo ); 
}

}
