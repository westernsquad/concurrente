package tema42;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockExample1 {

    private static final int NPROC = 3;

    private static double x = 0;
    private static Lock xLock = new ReentrantLock();

    public static void inc() {
        for (int i = 0; i < 10000000; i++) {
            xLock.lock();
            x = x + 1;
            xLock.unlock();
        }
    }

    public static void main(String[] args) throws InterruptedException{
        List<Thread> ths = new ArrayList<>();
        for (int i = 0; i < NPROC; i++) {
            Thread th = new Thread(() -> inc());
            ths.add(th);
            th.start();
        }
        for (Thread th : ths) {
            th.join();
        }
        System.out.println("x = "+x);
    }
}
