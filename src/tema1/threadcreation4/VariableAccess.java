package threadcreation4;

public class VariableAccess {

    public static void main(String[] args) {
        final int datos = 1234;

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Datos = "+datos);
            }
        }, "Thread 1");
        thread.start();
    }
}
