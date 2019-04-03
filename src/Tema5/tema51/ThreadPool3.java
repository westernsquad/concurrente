package tema51;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.*;

public class ThreadPool3 {

    private static Set<Character> vowels;

    public static int wordCount(String word) {
        int vowelsCount = 0;
        for (int i = 0; i < word.length(); i++) {
            vowelsCount += vowels.contains(word.charAt(i))?1:0;
        }
        return vowelsCount;
    }

    public static void main(String[] args) throws InterruptedException, IOException, ExecutionException {
        vowels = new HashSet<>();
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');

//        ExecutorService pool = Executors.newSingleThreadExecutor();
        ExecutorService pool = Executors.newCachedThreadPool();
//        ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        BufferedReader bf = new BufferedReader(new FileReader("prueba_texto.txt"));
        String line = null;
        List<Future<Integer>> tasks = new ArrayList<>();
        while ((line = bf.readLine()) != null) {
            String[] tokens = line.split("\\s+");
            for (int i = 0; i < tokens.length; i++) {
                int id = i;
                tasks.add(pool.submit(() -> wordCount(tokens[id])));
            }
        }
        int total = 0;
        for (Future<Integer> task : tasks) {
            total += task.get();
        }
        System.out.println("Vocales: "+total);
        pool.shutdown();
    }
}
