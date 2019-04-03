package tema22;

public class EMAp3 {

    static volatile boolean p1sc;
    static volatile boolean p2sc;

    public static void sleep() {
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public static void p1() {
        for (int i = 0; i < 5; i++) {
            p1sc = true;
            while (p2sc) Thread.onSpinWait();

            System.out.println("P1_SC1");
            sleep();
            System.out.println("P1_SC2");
            sleep();

            p1sc = false;

            System.out.println("P1_SNC1");
            sleep();
            System.out.println("P1_SNC2");
            sleep();
        }
    }

    public static void p2() {
        for (int i = 0; i < 5; i++) {
            p2sc = true;
            while (p1sc) Thread.onSpinWait();

            System.out.println("\t\t\tP2_SC1");
            sleep();
            System.out.println("\t\t\tP2_SC2");
            sleep();

            p2sc = false;

            System.out.println("\t\t\tP2_SNC1");
            sleep();
            System.out.println("\t\t\tP2_SNC2");
            sleep();
        }
    }

    public static void main(String[] args) {
        p1sc = false;
        p2sc = false;
        new Thread(() -> p1(), "P1").start();
        new Thread(() -> p2(), "P2").start();
    }
}
