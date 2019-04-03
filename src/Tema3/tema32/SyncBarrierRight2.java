package tema32;

import java.util.concurrent.Semaphore;

public class SyncBarrierRight2 {

    private static final int NPROC = 3;
    private static volatile int nProc;
    private static Semaphore sb;
    private static Semaphore emNProc;

    public static void process() throws InterruptedException {
        System.out.println("A");
        emNProc.acquire();
        nProc++;
        if (nProc == NPROC) {
            emNProc.release();
            for (int i = 0; i < NPROC-1; i++) {
                sb.release();
            }
        }
        emNProc.release();
        sb.acquire();
        System.out.println("B");
    }

    public static void main(String[] args) {
        sb = new Semaphore(0);
        emNProc = new Semaphore(1);
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
