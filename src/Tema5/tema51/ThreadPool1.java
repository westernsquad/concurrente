package tema51;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool1 {

    public static void execute(int id) {
        Random rnd = new Random();
        for (int i = 0; i < 10; i++) {
            System.out.println("Thread "+id+", iter "+(i+1));
            try {
                Thread.sleep(rnd.nextInt(500)+500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
//        ExecutorService pool = Executors.newSingleThreadExecutor();
//        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 10; i++) {
            int id = i+1;
            pool.execute(() -> execute(id));
        }
        pool.shutdown();
        pool.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        System.out.println("TERMINADO");
    }
}
