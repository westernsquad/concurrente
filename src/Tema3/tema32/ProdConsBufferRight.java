package tema32;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

public class ProdConsBufferRight {

    private static final int NPROD = 1;
    private static final int NCONS = 3;

    private static BufferSync buffer;


    public static void sleep(int bound) {
        try {
            Thread.sleep(new Random().nextInt(bound));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void producer() {
        for (int i = 0; i < 20; i++) {
            sleep(500);
            buffer.insert(i);
        }
    }

    public static void consumer() {
        while (true) {
            int data = buffer.extract();
            sleep(500);
            System.out.println("DATA: "+data);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        buffer = new BufferSync();
        List<Thread> ths = new ArrayList<>();
        for (int i = 0; i < NPROD; i++) {
            ths.add(new Thread(() -> producer(), "Producer "+(i+1)));
        }
        for (int i = 0; i < NCONS; i++) {
            ths.add(new Thread(() -> consumer(), "Consumer"+(i+1)));
        }
        for (Thread th : ths) {
            th.start();
        }
        for (Thread th : ths) {
            th.join();
        }

    }
}
