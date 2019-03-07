package concurrente.Tema4;

import static concurrente.LectorEscritor.writer;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author westernsquad
 */
public class tema4_1_Interrupcion {
    public static void generateNumbers() {
        try {
        PrintWriter writer = new PrintWriter("output.txt");
        while (true) {
            BigInteger prime =
            BigInteger.probablePrime(1024,new Random());
            writer.println(prime.toString());
            if(Thread.interrupted()){
            writer.append("Fin fichero");
            writer.close();
            return;
            }
            }
        } catch (IOException e) {
        System.err.println("Exception using file");
        e.printStackTrace();
        System.exit(1);
        }
        
}
    
public static void generateNumbers2() {
    try {
        FileWriter writer = new FileWriter("output.txt");
        while (true) {
            BigInteger prime =
            BigInteger.probablePrime(1024,new Random());
            writer.append(prime.toString());
            writer.append("\r\n");
        try {
            Thread.sleep(1000);
        } catch(InterruptedException e){
            writer.append("Fin fichero");
            writer.close();
            return;
        }
        }
    } catch (IOException e) {//catch de la apertura de fichero
        System.err.println("Exception using file");
        e.printStackTrace();
        System.exit(1);
    }
}
    public static void main(String[] args) {
        Thread t = new Thread(() -> {
        generateNumbers();});
        t.start();
        Scanner teclado = new Scanner(System.in);
        System.out.print("Pulse ENTER para finalizar...");
        teclado.nextLine();
        t.interrupt();
        System.out.println("Hilo interrumpido.");
}
}
