package threadcreation8;

public class MaxMinConcurrent {

    static volatile double n1, n2, min, max;

    public static void min() {
        new Thread(() -> min = n1 < n2 ? n1 : n2).start();
    }

    public static void max() {
        new Thread(() -> max = n1 > n2 ? n1 : n2).start();
    }

    public static void main(String[] args) {
        n1 = 3;
        n2 = 5;
        min(); // I1
        max(); // I2
        System.out.println("MIN = "+min+", MAX = "+max); //I3
    }
}
