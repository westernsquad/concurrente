package tema41;

import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;

public class GenerateNumbers {

    public static void generateNumbers() {
        try {
            PrintWriter writer = new PrintWriter("output.txt");
            while (true) {
                BigInteger prime = BigInteger.probablePrime(1024,new Random());
                writer.println(prime.toString());
                if(Thread.interrupted()){
                    writer.println("Fin fichero");
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

    public static void main(String[] args) {
        Thread t = new Thread(new Runnable() {
            public void run() {
                generateNumbers();
            }
        });
        t.start();

        Scanner teclado = new Scanner(System.in);
        System.out.print("Pulse ENTER para finalizar...");
        teclado.nextLine();

        t.interrupt();

        System.out.println("Hilo interrumpido.");
        teclado.close();

    }
}
