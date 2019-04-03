package tema32;

import java.util.concurrent.Semaphore;

public class SyncBarrierRight3 {

    private static final int NPROC = 3;
    private static volatile int nProc;
    private static Semaphore sb;
    private static Semaphore emNProc;
    private static Semaphore semUnblock;

    public static void process() throws InterruptedException {
        System.out.println("A");
        emNProc.acquire();
        nProc++;
        if (nProc < NPROC) {
            emNProc.release();
            sb.acquire();
            semUnblock.release();
        } else {
            System.out.println("*");
            nProc = 0;
            for (int i = 0; i < NPROC - 1; i++) {
                sb.release();
            }
            for (int i = 0; i < NPROC - 1; i++) {
                semUnblock.acquire();
            }
        }
    }

    public static void main(String[] args) {
        sb = new Semaphore(0);
        semUnblock = new Semaphore(0);
        emNProc = new Semaphore(1);
        for (int i = 0; i < NPROC; i++) {
            new Thread(() -> {
                try {
                    process();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }, "Hilo " + (i + 1)).start();
        }
    }
}
