package threadcreation7;

public class Printer {


    public static void main(String[] args) {
        new Thread(() -> {
            for (int i=0;i<5;i++) {
                System.out.println("XXX");
            }
        }).start();
        new Thread(() -> {
            for (int i=0;i<5;i++) {
                System.out.println("---");
            }
        }).start();
        new Thread(() -> {
            System.out.println("***");
        }).start();
    }
}
