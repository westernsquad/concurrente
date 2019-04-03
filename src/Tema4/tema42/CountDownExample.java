package tema42;

import java.util.concurrent.CountDownLatch;

public class CountDownExample {

    private static int NRUNNERS = 5;
    private static CountDownLatch latch;

    public static void runner(int id){
        System.out.println("Runner "+id+" ready");
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Runner "+id+" running");
    }

    public static void judge() {
        for (int i = NRUNNERS; i > 0; i--) {
            System.out.println("JUDGE: "+i+" ... ");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            latch.countDown();
        }
        System.out.println("JUDGE: GO!");
    }

    public static void main(String[] args) {
        latch = new CountDownLatch(NRUNNERS);
        for (int i = 0; i < NRUNNERS; i++) {
            int finalId = i+1;
            new Thread(() -> runner(finalId)).start();
        }
        new Thread(() -> judge()).start();
    }
}
