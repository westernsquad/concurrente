package tema51;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPool5_InvokeAll {


    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {

//        ExecutorService pool = Executors.newSingleThreadExecutor();
//        ExecutorService pool = Executors.newCachedThreadPool();
        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int N = 1000;
        List<Callable<String>> tasks = new ArrayList<>();
        for (int i = 1; i < N; i++) {
            int num = i;
            tasks.add(() -> "Task "+num);
        }
//        List<Future<String>> results = pool.invokeAll(tasks);
//        for (Future<String> result : results) {
//            System.out.println(result.get());
//        }
        String res = pool.invokeAny(tasks);
        System.out.println(res);
        pool.shutdown();
    }
}
