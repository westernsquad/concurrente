package tema32;

public class BufferNoSync {

    private static final int BUFFER_SIZE = 10;
    private int[] data = new int[BUFFER_SIZE];

    private int pInsert;
    private int pExtract;

    public BufferNoSync() {
        for (int i = 0; i < BUFFER_SIZE; i++) {
            this.data[i] = -1;
        }
    }

    public void insert(int d) {
        data[pInsert] = d;
        pInsert = (pInsert+1) % BUFFER_SIZE;
    }

    public int extract() {
        int d = data[pExtract];
        data[pExtract] = -1;
        pExtract = (pExtract+1) % BUFFER_SIZE;
        return d;
    }
}
