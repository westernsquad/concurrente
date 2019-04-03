package Tema61;

import java.io.*;
import java.util.Optional;
import java.util.Random;
import java.util.stream.Stream;

public class Exercise {

    private static void generateFile(int n, int m, String path) {
        Random rnd = new Random();
        try (PrintWriter pw = new PrintWriter(path)) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    pw.print(rnd.nextFloat()+" ");
                }
                pw.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        String path = "./exampleFile.txt";
        generateFile(50000, 1000, path);

        Stream<String> s = new BufferedReader(new FileReader(path)).lines();
        long tIni = System.currentTimeMillis();
//        Optional<Float> maxVal = s.parallel()
        Optional<Float> maxVal = s
                .map(s1 -> {
                    float avg = 0;
                    int count = 0;
                    for (String elem : s1.split("\\s+")) {
                        avg += Float.parseFloat(elem);
                        count++;
                    }
                    avg /= count;
                    return avg;
                })
                .reduce(Math::max);
        double secs = (System.currentTimeMillis() - tIni) / 1000.0;
        System.out.println(maxVal.get()+"\t"+secs);
    }
}
