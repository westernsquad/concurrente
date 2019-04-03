package tema22;

public class EMAp1 {

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
            while (turno != 1) Thread.onSpinWait();

            System.out.println("P1_SC1");
            sleep();
            System.out.println("P1_SC2");
            sleep();

            turno = 2;

            System.out.println("P1_SNC1");
            sleep();
            System.out.println("P1_SNC2");
            sleep();
        }
    }

    public static void p2() {
        for (int i = 0; i < 5; i++) {
            while (turno != 2) Thread.onSpinWait();

            System.out.println("\t\t\tP2_SC1");
            sleep();
            System.out.println("\t\t\tP2_SC2");
            sleep();

            turno = 1;

            System.out.println("\t\t\tP2_SNC1");
            sleep();
            System.out.println("\t\t\tP2_SNC2");
            sleep();
        }
    }

    public static void main(String[] args) {
        turno = 1;
        new Thread(() -> p1(), "P1").start();
        new Thread(() -> p2(), "P2").start();
    }
}
