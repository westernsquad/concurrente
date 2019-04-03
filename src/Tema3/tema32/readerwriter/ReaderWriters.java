package tema32.readerwriter;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class ReaderWriters {
    private static Semaphore resourceSem;
    private static Semaphore readerSem;
    private static int readerCount;

    private static void sleep(int bound) {
        try {
            Thread.sleep(bound);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void writer() {
        try {
            while (true) {
                resourceSem.acquire();
                System.out.println("Escritor escribiendo");
                sleep(1000);
                resourceSem.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void reader() {
        try {
            while (true) {
                readerSem.acquire();
                readerCount++;
                if (readerCount == 1) {
                    // Es el primer lector, cojo el recurso
                    resourceSem.acquire();
                }
                readerSem.release();

                System.out.println("Lector leyendo");
                sleep(1000);

                readerSem.acquire();
                readerCount--;
                if (readerCount==0) {
                    // Soy el último lector, libero el recurso
                    resourceSem.release();
                }
                readerSem.release();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        readerSem = new Semaphore(1);
        resourceSem = new Semaphore(1);
        List<Thread> ths = new ArrayList<Thread>();
        ths.add(new Thread(() -> reader(), "Lector 1"));
        ths.add(new Thread(() -> reader(), "Lector 2"));
        ths.add(new Thread(() -> writer(), "Escritor 1"));
        ths.add(new Thread(() -> writer(), "Escritor 2"));
        // Arrancar hilos
        for (int i = 0; i < ths.size(); i++) {
            ths.get(i).start();
        }
        // Esperar a que todos los hilos hayan terminado
        for (int i = 0; i < ths.size(); i++) {
            try {
                ths.get(i).join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
