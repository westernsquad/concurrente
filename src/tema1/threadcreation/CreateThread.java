package threadcreation;

public class CreateThread {

    public static void main(String[] args) {
        Thread th = new Thread(new ThreadExample(), "ThreadExample");
        th.start();
    }
}
