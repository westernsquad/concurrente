package tema52;

import java.util.concurrent.RecursiveTask;

public class DyCFJTaskOpt extends RecursiveTask<Integer> {

    private static final int TH_SEQ = 2;

    private int[] a;
    private int min;
    private int max;

    public DyCFJTaskOpt(int[] a, int min, int max) {
        this.a = a;
        this.min = min;
        this.max = max;
    }

    @Override
	public Integer compute() {
        if (max - min <= TH_SEQ) {
            int maxVal = a[min];
            for (int i = min+1; i < max; i++) {
                maxVal = Math.max(maxVal, a[i]);
            }
            return maxVal;
        } else {
            int mid = (max + min) / 2;
            DyCFJTaskOpt left = new DyCFJTaskOpt(a, min, mid);
            DyCFJTaskOpt right = new DyCFJTaskOpt(a, mid, max);
            left.fork();
            int rightRes = right.compute();
            int leftRes = left.join();
            return Math.max(leftRes, rightRes);
        }
    }
}
