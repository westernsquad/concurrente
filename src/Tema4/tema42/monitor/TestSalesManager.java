package tema42.monitor;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestSalesManager {

    private static int NCLIENTS = 10;
    private static int NSELLER = 2;
    private static SalesManager manager;

    public static void buyers() {
        for (int i = 0; i < NCLIENTS; i++) {
            manager.buy((i+1));
        }
    }

    public static void seller(int id){
        try {
            while (true) {
                manager.sell(id);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        manager = new SalesManager();
        List<Thread> ths = new ArrayList<>();
        ths.add(new Thread(() -> buyers(), "Client"));
        for (int i = 0; i < NSELLER; i++) {
            int idSeller = i+1;
            ths.add(new Thread(() -> seller(idSeller), "Seller "+(i+1)));
        }
        for (Thread th : ths) {
            th.start();
        }
        for (Thread th : ths) {
            th.join();
        }
    }
}
