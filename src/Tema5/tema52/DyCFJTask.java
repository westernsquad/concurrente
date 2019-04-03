package tema52;

import java.util.concurrent.RecursiveTask;

public class DyCFJTask extends RecursiveTask<Integer> {

    private static final int TH_SEQ = 2;

    private int[] a;
    private int min;
    private int max;

    public DyCFJTask(int[] a, int min, int max) {
        this.a = a;
        this.min = min;
        this.max = max;
    }

    @Override
    protected Integer compute() {
        if (max - min <= TH_SEQ) {
            int maxVal = a[min];
            for (int i = min+1; i < max; i++) {
                maxVal = Math.max(maxVal, a[i]);
            }
            return maxVal;
        } else {
            int mid = (max + min) / 2;
            DyCFJTask left = new DyCFJTask(a, min, mid);
            DyCFJTask right = new DyCFJTask(a, mid, max);
            left.fork();
            right.fork();
            int leftRes = left.join();
            int rightRes = right.join();
            return Math.max(leftRes, rightRes);
        }
    }
}
