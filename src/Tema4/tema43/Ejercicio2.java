package tema43;

import java.util.Random;
import java.util.TreeMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.SynchronousQueue;

public class Ejercicio2 {

    private static BlockingQueue<String> q = new ArrayBlockingQueue<>(5);

    public static void producer(int id) {
        try {
            for (int i = 0; i < 10; i++) {
                String product = "Productor "+id+" -> Producto "+i;
                q.put(product);
                System.out.println("PRODUCIENDO "+product);
                Thread.sleep(new Random().nextInt(1500));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void consumer(int id) {
        try {
            while (true) {
                String elem = q.take();
                System.out.println("CONSUMIDOR "+id+": "+elem);
                Thread.sleep(new Random().nextInt(500));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int id = i+1;
            new Thread(() -> producer(id)).start();
        }
        for (int i = 0; i < 2; i++) {
            int id = i+1;
            new Thread(() -> consumer(id)).start();
        }
    }
}
