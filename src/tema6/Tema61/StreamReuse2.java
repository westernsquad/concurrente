package Tema61;

import java.util.function.Supplier;
import java.util.stream.Stream;

public class StreamReuse2 {

    public static void main(String[] args) {
        Supplier<Stream<String>> streamSupplier =
                () -> Stream.of("d2", "a2", "b1", "b3", "c")
                        .filter(s -> s.startsWith("a"));

        streamSupplier.get().anyMatch(s -> true);   // ok
        streamSupplier.get().noneMatch(s -> true);  // ok


    }
}
