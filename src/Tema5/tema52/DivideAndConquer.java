package tema52;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Random;

public class DivideAndConquer {

    private static final int TH_SEQ = 2;

    public static int findMax(int[] a, int min, int max) {
        int maxVal = 0;
        if (max-min <= TH_SEQ) {
            maxVal = a[min];
            for (int i = min+1; i < max; i++) {
                maxVal = Math.max(maxVal, a[i]);
            }
        } else {
            int mid = (min + max)/2;
            int leftRes = findMax(a, min, mid);
            int rightRes = findMax(a, min, mid);
            maxVal = Math.max(leftRes, rightRes);
        }
        return maxVal;
    }

    public static int[] generateRandArray(int size) {
        Random rnd = new Random();
        int[] a = new int[size];
        for (int i = 0; i < size; i++) {
            a[i] = rnd.nextInt(size);
        }
        return a;
    }

    public static void main(String[] args) {
        int[] a = generateRandArray(100);
        long tIni = System.currentTimeMillis();
        int max = findMax(a, 0, a.length);
        double secs = (System.currentTimeMillis() - tIni) / 1000.0;
        System.out.println("MAX = "+max);
        System.out.println("Time: "+secs);
    }
}
