package threadcreation7;

public class Increment {

    static int result = 0;
    static final int TIMES = 5;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i=0;i<TIMES;i++) {
                result++;
                System.out.println("INC: "+result);
            }
        }).start();
        new Thread(() -> {
            for (int i=0;i<TIMES;i++) {
                result--;
                System.out.println("DEC: "+result);
            }
        }).start();
    }
}
