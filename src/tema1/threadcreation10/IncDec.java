package tema1.threadcreation10;

public class IncDec {

    static volatile double x;

    public static void main(String[] args) throws InterruptedException {
        Thread inc = new Thread(() -> {
            x = x + 1;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Thread dec = new Thread(() -> {
            x = x - 1;
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        inc.start();
        dec.start();
        inc.join();
        dec.join();
        System.out.println("X = "+x);
    }
}
