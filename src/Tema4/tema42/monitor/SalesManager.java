package tema42.monitor;

import java.util.LinkedList;
import java.util.Queue;

public class SalesManager {

    private static final int TICKET_PRICE = 100;

    private Queue<Integer> requests;
    private int money;

    public SalesManager() {
        requests = new LinkedList<>();
    }

    public synchronized void buy(int client) {
        requests.add(client);
        System.out.println("Cliente "+client+" ha llegado");
    }

    public synchronized void sell(int id) throws InterruptedException {
        while (requests.isEmpty()) {
            this.wait();
        }
        int client = requests.poll();
        System.out.println("Procesado cliente "+client+" por la ventanilla "+id);
        money += TICKET_PRICE;
        show();
        this.notifyAll();
    }

    public synchronized void show(){
        System.out.print("Clientes esperando: ");
        for (int client : requests) {
            System.out.print(client + " ");
        }
        System.out.println();
        System.out.println("MONEY EARN: " + money);
    }
}
