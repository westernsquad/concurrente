package Tema61;

import java.util.Arrays;
import java.util.List;

public class StreamsExample {

    public static void main(String[] args) {
        List<String> l = Arrays.asList("a1", "a2", "b1", "c2", "c2");
        l.stream()
                .filter(s -> s.startsWith("c"))
                .map(String::toUpperCase)
                .sorted()
                .forEach(System.out::println);
    }
}
