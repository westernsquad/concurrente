package tema31;

import java.util.concurrent.Semaphore;

public class Semaphore1 {

    private static Semaphore semContinue;

    public static void procA() {
        System.out.println("PA1");
        semContinue.release();
        System.out.println("PA2");
    }

    public static void procB() {
        System.out.println("PB1");
        try {
            semContinue.acquire();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("PB2");
    }

    public static void main(String[] args) {
        semContinue = new Semaphore(1);
        new Thread(() -> procA(), "Proceso A").start();
        new Thread(() -> procB(), "Proceso B").start();
    }
}
