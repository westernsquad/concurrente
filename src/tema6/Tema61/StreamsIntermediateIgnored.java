package Tema61;

import java.util.stream.Stream;

public class StreamsIntermediateIgnored {

    public static void main(String[] args) {
        Stream.of("d2", "a2", "b1", "b3", "c")
                .filter(s -> {
                    System.out.println("filter: " + s);
                    return true;
                });

    }
}
