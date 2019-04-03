package threadcreation5;

public class VariableAccess {

    public void ejecutar() {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                metodo();
            }
        }, "Thread 1");
        thread.start();
    }

    private void metodo() {
        System.out.println("Soy un metodo privado");
    }

    public static void main(String[] args) {
        VariableAccess programa = new VariableAccess();
        programa.ejecutar();
    }
}
