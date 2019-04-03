package threadcreation3;

public class CreateThread {

    public static void main(String[] args) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Soy un thread");
            }
        }, "Thread 1");
        thread.start();
    }
}
