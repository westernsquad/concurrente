package tema41;

public class Messages {
    public static void mensaje() {
        String[] frases = new String[] {
                "La vida es bella",
                "O no...",
                "Los pajaritos cantan",
                "Y molestan..."
        };
        for (String frase : frases) {
            try {
                Thread.sleep(2000);
                System.out.println(frase);
            } catch (InterruptedException e) {
                System.out.println("Se acabó!");
                return;
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> mensaje());
        t.start();
        boolean continuar = true;
        int iters = 0;
        while (continuar) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {

            }
            if (iters == 5) {
                System.out.println("Cansado de esperar!");
                t.interrupt();
                t.join();
                continuar = false;
            } else {
                System.out.println("Todavía esperando...");
                iters++;
            }
        }
        System.out.println("Por fin!");
    }
}
