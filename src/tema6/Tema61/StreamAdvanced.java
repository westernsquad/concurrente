package Tema61;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamAdvanced {



    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                        new Person("Max", 18),
                        new Person("Peter", 23),
                        new Person("Pamela", 23),
                        new Person("David", 12));


        List<Person> filtered = persons
                .stream()
                .filter(p -> p.name.startsWith("P"))
                .collect(Collectors.toList());
        System.out.println(filtered);


        Map<Integer, List<Person>> personsByAge = persons
                .stream()
                .collect(Collectors.groupingBy(p -> p.age));

        personsByAge
                .forEach((age, p) ->
                        System.out.format("age %s: %s\n", age, p));


        Double averageAge = persons
                .stream()
                .collect(Collectors.averagingInt(p -> p.age));

        System.out.println(averageAge);

        IntSummaryStatistics ageSummary =
                persons
                        .stream()
                        .collect(Collectors.summarizingInt(p -> p.age));

        System.out.println(ageSummary);


        String phrase = persons
                .stream()
                .filter(p -> p.age >= 18)
                .map(p -> p.name)
                .collect(Collectors.joining
                        (" and ", "In Germany ", " are of legal age."));

        System.out.println(phrase);

        persons
                .stream()
                .reduce((p1, p2) -> p1.age > p2.age ? p1 : p2)
                .ifPresent(System.out::println);


        Person result =
                persons
                        .stream()
                        .reduce(new Person("", 0), (p1, p2) -> {
                            p1.age += p2.age;
                            p1.name += p2.name;
                            return p1;
                        });

        System.out.format("name=%s; age=%s", result.name, result.age);


        Integer ageSum = persons
                .stream()
                .reduce(0, (sum, p) -> sum += p.age,
                        (sum1, sum2) -> sum1 + sum2);

        System.out.println(ageSum);

    }

}
