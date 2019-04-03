package tema52;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ForkJoinPool;

public class DivideAndConquerFJ {

    private static final int TH_SEQ = 2;

    public static int findMax(int[] a, int min, int max) {
        int maxVal = 0;
        if (max-min <=2) {
            maxVal = a[min];
            for (int i = min+1; i < max; i++) {
                maxVal = Math.max(maxVal, a[i]);
            }
        } else {
            int mid = (min + max)/2;
            maxVal = Math.max(findMax(a, min, mid), findMax(a, mid+1, max));
        }
        return maxVal;
    }

    public static int[] generateRandArray(int size) {
        Random rnd = new Random();
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = rnd.nextInt(size*10);
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = generateRandArray(100);
        ForkJoinPool pool = new ForkJoinPool();
        long tIni = System.currentTimeMillis();
        int max = pool.invoke(new DyCFJTask(a, 0, a.length));
//        int max = pool.invoke(new DyCFJTaskOpt(a, 0, a.length));
        double secs = (System.currentTimeMillis() - tIni) / 1000.0;
        System.out.println("MAX = "+max);
        System.out.println("Time: "+secs);
    }
}
