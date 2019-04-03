package tema32.philosopher;

import java.util.concurrent.Semaphore;

public class Philosopher extends Thread {

    private int numPhil;
    private int leftFork;
    private int rightFork;
    private Semaphore[] semForks;

    private void sleep(int bound) {
        try {
            Thread.sleep(bound);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Philosopher(int numPhil, Semaphore[] semForks, String name) {
        super(name);
        this.numPhil = numPhil;
        this.semForks = semForks;
        this.leftFork = numPhil;
        this.rightFork = (numPhil+1) % semForks.length;
    }

    public Philosopher(int numPhil, Semaphore[] semForks) {
        this.numPhil = numPhil;
        this.semForks = semForks;
        this.leftFork = numPhil;
        this.rightFork = (numPhil+1) % semForks.length;
    }

    private void eat() throws InterruptedException {
        semForks[leftFork].acquire();
        semForks[rightFork].acquire();
        System.out.println("Philosopher "+(numPhil+1)+" eating");
        sleep(1500);
        System.out.println("Philosopher "+(numPhil+1)+" done eating");
        semForks[rightFork].release();
        semForks[leftFork].release();
    }

    private void think() {
        System.out.println("Philosopher "+(numPhil+1)+" thinking");
        sleep(1500);
    }

    @Override
    public void run() {
        try {
            while(true) {
                eat();
                think();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
