package tema42;

import java.util.ArrayList;
import java.util.List;

public class SyncExample1 {

    private static double x = 0;
    private static Object lock = new Object();

    public static void inc() {
        for (int i = 0; i < 100; i++) {
            synchronized (lock) {
                x++;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        int n = 3;
        List<Thread> ths = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Thread th = new Thread(() -> inc());
            ths.add(th);
            th.start();
        }
        for (int i = 0; i < n; i++) {
            ths.get(i).join();
        }
        System.out.println("X = "+x);
    }
}
