package Tema61;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamsSpecific {

    public static void main(String[] args) {
        Stream.of(1.0, 2.0, 3.0)
                .mapToInt(Double::intValue)
                .mapToObj(i -> "a" + i)
                .forEach(System.out::println);
    }
}
