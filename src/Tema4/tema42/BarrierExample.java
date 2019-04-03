package tema42;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BarrierExample {

    private static final int NPROC = 5;

    private static CyclicBarrier barrier;
    private static Lock lock;

    public static void process(int id) {
        while (true) {
            try {
                lock.lock();
                for (int i = 0; i < id; i++) {
                    System.out.print("\t");
                }
                System.out.println("A");
                lock.unlock();
                barrier.await();
                Thread.sleep(1000);
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        lock = new ReentrantLock();
        barrier = new CyclicBarrier(NPROC, () -> System.out.println("*"));
        for (int i = 0; i < NPROC; i++) {
            int idProc = i;
            new Thread(() -> process(idProc)).start();
        }
    }
}
