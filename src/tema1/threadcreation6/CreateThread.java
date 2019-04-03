package threadcreation6;

public class CreateThread {


    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("Soy un hilo");
        };
        new Thread(task).start();

        new Thread(() -> System.out.println("Soy un hilo 2")).start();
    }
}
