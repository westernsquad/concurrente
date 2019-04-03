package tema42;

import java.util.concurrent.CountDownLatch;

public class CountDownExercise {

    private static CountDownLatch latchD;
    private static CountDownLatch latchBG;
    private static CountDownLatch latchH;
    private static CountDownLatch latchE;
    private static CountDownLatch latchC;

    public static void p1() {
        try {
            System.out.println("A");
            latchD.countDown();
            latchBG.await();
            System.out.println("B");
            latchH.countDown();
            latchE.countDown();
            latchC.await();
            System.out.println("C");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void p2() {
        try {
            latchD.await();
            System.out.println("\tD");
            latchBG.countDown();
            latchE.await();
            System.out.println("\tE");
            latchC.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void p3() {
        try {
            System.out.println("\t\tF");
            latchD.countDown();
            latchBG.await();
            System.out.println("\t\tG");
            latchH.await();
            System.out.println("\t\tH");
            latchE.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        latchD = new CountDownLatch(2);
        latchBG = new CountDownLatch(1);
        latchH = new CountDownLatch(1);
        latchE = new CountDownLatch(2);
        latchC = new CountDownLatch(1);
        new Thread(() -> p1()).start();
        new Thread(() -> p2()).start();
        new Thread(() -> p3()).start();
    }
}
