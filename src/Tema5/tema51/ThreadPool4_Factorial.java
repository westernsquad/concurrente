package tema51;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPool4_Factorial {

    private static class Result {
        int num;
        BigInteger factorial;

        public Result(int num, BigInteger factorial) {
            this.num = num;
            this.factorial = factorial;
        }
    }

    public static Result factorial(int num) {
        BigInteger r = BigInteger.ONE;
        int count = num;
        while (count > 1) {
            r = r.multiply(BigInteger.valueOf(num));
            count--;
        }
        return new Result(num, r);
    }

    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {

//        ExecutorService pool = Executors.newSingleThreadExecutor();
//        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int N = 1000000;
        Set<Future<Result>> tasks = new HashSet<>();
        for (int i = 1; i < N; i++) {
            int num = i;
            tasks.add(pool.submit(() -> factorial(num)));
        }
        while (!tasks.isEmpty()) {
            List<Future<Result>> toRemove = new ArrayList<>();
            for (Future<Result> task : tasks) {
                if (task.isDone()) {
                    Result r = task.get();
                    System.out.println(r.num+": "+r.factorial);
                    toRemove.add(task);
                }
            }
            tasks.removeAll(toRemove);
            System.out.println(tasks.size());
            toRemove.clear();
        }
        pool.shutdown();
    }
}
