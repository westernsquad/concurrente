package tema42.counter;

public class Counter {

    private int x;

    public synchronized void inc() {
        x++;
    }

    public int getValue() {
        return x;
    }

    public static void main(String[] args) {
        Counter counter = new Counter();
        for (int i = 0; i < 100; i++) {
            counter.inc();
        }
        System.out.println(counter.getValue());
    }
}
