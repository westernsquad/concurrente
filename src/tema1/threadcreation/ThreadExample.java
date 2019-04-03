package threadcreation;


public class ThreadExample implements Runnable {

    @Override
    public void run() {
        System.out.println("Soy un thread");
    }
}
