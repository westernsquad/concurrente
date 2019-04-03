package threadcreation8;

public class MaxMin {

    static volatile double n1, n2, min, max;

    public static void min() {
        min = n1 < n2 ? n1 : n2;
    }

    public static void max() {
        max = n1 > n2 ? n1 : n2;
    }

    public static void main(String[] args) {
        n1 = 3;
        n2 = 5;
        min(); // I1
        max(); // I2
        System.out.println("MIN = "+min+", MAX = "+max); //I3
    }
}
