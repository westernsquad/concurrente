package tema22;

public class EMAp5_Decker {

    static volatile boolean p1sc;
    static volatile boolean p2sc;
    static volatile int turno;

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
            while (p2sc) {
                if (turno != 1) {
                    p1sc = false;
                    while (turno != 1) Thread.onSpinWait();
                    p2sc = true;
                }
            }

            System.out.println("P1_SC1");
            sleep();
            System.out.println("P1_SC2");
            sleep();

            p1sc = false;
            turno = 2;

            System.out.println("P1_SNC1");
            sleep();
            System.out.println("P1_SNC2");
            sleep();
        }
    }

    public static void p2() {
        for (int i = 0; i < 5; i++) {
            p2sc = true;
            while (p1sc) {
                p2sc = false;
                while (turno != 2) Thread.onSpinWait();
                p1sc = true;
            }

            System.out.println("\t\t\tP2_SC1");
            sleep();
            System.out.println("\t\t\tP2_SC2");
            sleep();

            p2sc = false;
            turno = 1;

            System.out.println("\t\t\tP2_SNC1");
            sleep();
            System.out.println("\t\t\tP2_SNC2");
            sleep();
        }
    }

    public static void main(String[] args) {
        turno = 1;
        p1sc = false;
        p2sc = false;
        new Thread(() -> p1(), "P1").start();
        new Thread(() -> p2(), "P2").start();
    }
}
