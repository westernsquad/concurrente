package tema42.monitorexercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TestServer {

    public static void main(String[] args) throws InterruptedException {
        // CREAR EL MONITOR
        Server server = new Server();

        int nMant = 5;
        int nReq = 5;

        List<Thread> ths = new ArrayList<>();
        for (int i = 0; i < nMant; i++) {
            int finalI = i+1;
            ths.add(new Thread(() -> {
                try {
                    server.startMaintenance("Maintenance "+finalI);
                    Thread.sleep(100 + new Random().nextInt(100));
                    server.endMaintenance("Maintenance "+finalI);
                } catch (InterruptedException e) {

                }
            }));
        }

        for (int i = 0; i < nReq; i++) {
            int finalI = i+1;
            ths.add(new Thread(() -> {
                try {
                    server.startRequest("Request "+finalI);
                    Thread.sleep(100 + new Random().nextInt(100));
                    server.endRequest("Request "+finalI);
                } catch (InterruptedException e) {

                }
            }));
        }

        for (Thread th : ths) {
            th.start();
        }
        for (Thread th : ths) {
            th.join();
        }

        System.out.println("Fin programa");
    }
}
