package tema32;

import java.util.concurrent.Semaphore;

public class EMGeneralized {

    private static Semaphore em;
    private static final int NPROC = 5;
    private static final int NPROCINSC = 3;

    public static void process() {
        for (int i = 0; i < 5; i++) {
            try {
                em.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("SC1");
            System.out.println("SC2");
            em.release();
            System.out.println("SNC1");
            System.out.println("SNC2");
        }
    }

    public static void main(String[] args) {
        em = new Semaphore(NPROCINSC);
        for (int i = 0; i < NPROC; i++) {
            new Thread(() -> process()).start();
        }
    }
}
