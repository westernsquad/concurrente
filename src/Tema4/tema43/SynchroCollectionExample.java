package tema43;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SynchroCollectionExample {

    static List<String> sharedList;

    protected static void process(int num) {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep((long) (Math.random() * 500));
            } catch (InterruptedException e) {}
            sharedList.add("H"+num+"_I"+i);
        }
    }

    public static void main(String[] args) {
        sharedList = Collections.synchronizedList(new ArrayList<String>());

        List<Thread> ths = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            int id = i+1;
            ths.add(new Thread(() -> process(id)));
        }

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

        System.out.println("List: "+sharedList);
    }
}
