package tema42;

import java.util.concurrent.Exchanger;

public class ExchangerExample {

    static Exchanger<Double> exchanger = new Exchanger<Double>();

    public static void productor() {
        try {
            double productoL = 0;
            for (int i = 0; i < 10; i++) {
                productoL++;
                Thread.sleep(1000);
                exchanger.exchange(productoL);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void consumidor() {
        try {
            for (int i = 0; i < 10; i++) {
                double producto = exchanger.exchange(null);
                System.out.println("Producto: " + producto);
                Thread.sleep(1000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> productor()).start();
        new Thread(() -> consumidor()).start();
    }
}
