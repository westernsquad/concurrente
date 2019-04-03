package tema32.philosopher;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class PhilosopherProblem {


    public static void main(String[] args) throws InterruptedException {
        int numPhilos = 5;
        Semaphore[] semForks = new Semaphore[numPhilos];
        for (int i = 0; i < numPhilos; i++) {
            semForks[i] = new Semaphore(1);
        }
        List<Thread> ths = new ArrayList<>();
        for (int i = 0; i < numPhilos; i++) {
            Philosopher ph = new Philosopher(i, semForks, "Philosopher "+(i+1));
            ths.add(ph);
            ph.start();
        }
        for (Thread th : ths) {
            th.join();
        }
    }
}
