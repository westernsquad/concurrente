package tema42;

import java.util.ArrayList;
import java.util.List;

public class TwoSyncs {

    private static double x = 0;
    private static Object xLock = new Object();
    private static double y = 0;
    private static Object yLock = new Object();

    public static void incX() {
        for (int i = 0; i < 100; i++) {
            synchronized (xLock) {
                x++;
            }
        }
    }

    public static void incY() {
        for (int i = 0; i < 100; i++) {
            synchronized (yLock) {
                y++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 3;
        List<Thread> ths = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Thread th = new Thread(() -> incX());
            ths.add(th);
            th.start();
            Thread th2 = new Thread(() -> incY());
            ths.add(th2);
            th2.start();
        }
        for (int i = 0; i < n; i++) {
            ths.get(i).join();
        }
        System.out.println("X = "+x);
        System.out.println("Y = "+y);
    }
}
