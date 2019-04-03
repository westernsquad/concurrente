package tema51;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ThreadPool2 {

    private static final int LIST_SIZE = 1000;

    public static void execute(int id) {
        Random rnd = new Random();
        System.out.println("Starting thread "+id);
        List<BigInteger> l = new ArrayList<>();
        for (int i = 0; i < LIST_SIZE; i++) {
            l.add(BigInteger.probablePrime(256, rnd));
        }
        BigInteger max = BigInteger.ZERO;
        for (int i = 0; i < LIST_SIZE; i++) {
            BigInteger next = l.get(i);
            if (next.compareTo(max) > 0) {
                max = next;
            }
        }
        System.out.println("Max value in thread "+id+": "+max);
    }

    public static void main(String[] args) throws InterruptedException {
        long tIni = System.currentTimeMillis();
//        ExecutorService pool = Executors.newSingleThreadExecutor();
        ExecutorService pool = Executors.newCachedThreadPool();
//        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < 100; i++) {
            int id = i+1;
            pool.execute(() -> execute(id));
        }
        pool.shutdown();
        pool.awaitTermination(Integer.MAX_VALUE, TimeUnit.DAYS);
        double secs = (System.currentTimeMillis()-tIni) / 1000.0;
        System.out.println("Tiempo: "+secs);
    }
}
