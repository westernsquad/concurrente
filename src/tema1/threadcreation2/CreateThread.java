package tema1.threadcreation2;

public class CreateThread {

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Soy un thread");
            }
        };

        Thread thread = new Thread(r, "Thread 1");
        thread.start();
    }
}
