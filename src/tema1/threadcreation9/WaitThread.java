package threadcreation9;

public class WaitThread {

    public static void main(String[] args) throws InterruptedException {
        Thread th = new Thread(() -> {
            for (int i = 0; i < 10; i++) {
                System.out.println("Iteracion "+i);
            }
        });
        th.start();
        th.join();
        System.out.println("Programa terminado");
    }
}
