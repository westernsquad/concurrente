package tema32;

import java.util.concurrent.Semaphore;

public class SyncBarrierWrong1 {

    private static final int NPROC = 3;
    private static volatile int nProc;
    private static Semaphore sb;

    public static void process() throws InterruptedException {
        System.out.println("A");
        nProc++;
        if (nProc < NPROC) {
            sb.acquire();
        } else {
            for (int i = 0; i < NPROC-1; i++) {
                sb.release();
            }
        }
        System.out.println("B");
    }

    public static void main(String[] args) {
        sb = new Semaphore(0);
        for (int i = 0; i < NPROC; i++) {
            new Thread(() -> {
                try {
                    process();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Hilo "+(i+1)).start();
        }
    }
}
