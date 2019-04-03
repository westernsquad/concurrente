package tema43;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class IsSecureList1 {

    private static List<String> l;

    private static void process(int num) {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException e) {}
            l.add("H"+num+"_I"+i);
        }
    }
    
    private static void deleteLast() {
        while (!l.isEmpty()) {
            int lastIndex = l.size()-1;
            String elem = l.remove(lastIndex);
            System.out.println("DELETED: "+elem);
        }
    }

    public static void main(String[] args) {
        l = Collections.synchronizedList(new ArrayList<String>());

        List<Thread> ths = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int id = i+1;
            ths.add(new Thread(() -> process(id)));
        }
        ths.add(new Thread(() -> deleteLast()));

        for (Thread th : ths) {
            th.start();
        }

        for (Thread th : ths) {
            try {
                th.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("FIN");
    }
}
