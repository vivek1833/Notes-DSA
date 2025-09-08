import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Terminal Operations in Java Streams
 *
 * - Terminal operations produce a non-stream result (List, int, String,
 * Optional, etc.)
 * - They trigger the actual processing of the stream pipeline.
 * - After a terminal operation, the stream cannot be reused.
 *
 * Common Terminal Operations:
 * 1. collect()
 * 2. forEach() / forEachOrdered()
 * 3. reduce()
 * 4. count()
 * 5. anyMatch() / allMatch() / noneMatch()
 * 6. findFirst() / findAny()
 * 7. toArray()
 * 8. min() / max()
 * 9. summaryStatistics()
 * 10. joining() (for Strings)
 */
public class TerminalOps {
    public static void main(String[] args) {

        List<Integer> list = Arrays.asList(1, 2, 3);

        // --------------------------------------------------
        // 1. collect() - convert stream to a collection
        // --------------------------------------------------
        List<Integer> collected = list.stream().skip(1).collect(Collectors.toList());
        System.out.println("Collected list: " + collected);

        // Shortcut in Java 16+: toList()
        System.out.println("Collected list using toList(): " + list.stream().skip(1).toList());

        // Collectors.joining()
        List<String> words = Arrays.asList("Java", "Streams", "API");
        String joined = words.stream().collect(Collectors.joining(", "));
        System.out.println("Joined string: " + joined);

        // --------------------------------------------------
        // 2. forEach() - perform action on each element
        // --------------------------------------------------
        System.out.println("forEach:");
        list.stream().forEach(System.out::println);

        // forEachOrdered (important for parallel streams)
        System.out.println("forEachOrdered with parallel:");
        list.parallelStream().forEachOrdered(System.out::println);

        // --------------------------------------------------
        // 3. reduce() - combine elements into one result
        // --------------------------------------------------
        Optional<Integer> sum = list.stream().reduce(Integer::sum);
        sum.ifPresent(s -> System.out.println("Sum using reduce: " + s));

        int product = list.stream().reduce(1, (a, b) -> a * b);
        System.out.println("Product using reduce: " + product);

        // --------------------------------------------------
        // 4. count() - count elements
        // --------------------------------------------------
        System.out.println("Count of elements: " + list.stream().count());

        // --------------------------------------------------
        // 5. anyMatch(), allMatch(), noneMatch()
        // --------------------------------------------------
        boolean hasEven = list.stream().anyMatch(x -> x % 2 == 0);
        boolean allPositive = list.stream().allMatch(x -> x > 0);
        boolean noneNegative = list.stream().noneMatch(x -> x < 0);

        System.out.println("Any even? " + hasEven);
        System.out.println("All positive? " + allPositive);
        System.out.println("None negative? " + noneNegative);

        // --------------------------------------------------
        // 6. findFirst(), findAny()
        // --------------------------------------------------
        System.out.println("findFirst: " + list.stream().findFirst().get());
        System.out.println("findAny: " + list.stream().findAny().get());

        // --------------------------------------------------
        // 7. toArray()
        // --------------------------------------------------
        Object[] array = list.stream().toArray();
        System.out.println("Array length: " + array.length);

        // --------------------------------------------------
        // 8. min() / max()
        // --------------------------------------------------
        System.out.println("Max: " +
                Stream.of(2, 44, 69).max(Integer::compare).get());
        System.out.println("Min: " +
                Stream.of(2, 44, 69).min(Integer::compare).get());

        // --------------------------------------------------
        // 9. summaryStatistics() (useful for numbers)
        // --------------------------------------------------
        IntSummaryStatistics stats = IntStream.of(2, 4, 6, 8, 10).summaryStatistics();
        System.out.println("Summary statistics: " + stats);

        // --------------------------------------------------
        // 10. Important Notes
        // --------------------------------------------------

        // Example: Counting occurrences of 'l' in a string
        String sentence = "Hello world";
        long countL = sentence.chars().filter(ch -> ch == 'l').count();
        System.out.println("Count of 'l': " + countL);

        // Streams cannot be reused once a terminal operation is called
        Stream<String> namesStream = Stream.of("Anna", "Bob", "Charlie");
        namesStream.forEach(System.out::println);
        // namesStream.map(String::toUpperCase).toList(); // ERROR:
        // IllegalStateException

        // --------------------------------------------------
        // Quick Demo Examples
        // --------------------------------------------------

        List<String> names = Arrays.asList("Anna", "Bob", "Charlie", "David");
        System.out.println("Names > 3 letters: " +
                names.stream().filter(x -> x.length() > 3).toList());

        List<Integer> numbers = Arrays.asList(5, 2, 9, 1, 6);
        System.out.println("Squared & sorted: " +
                numbers.stream().map(x -> x * x).sorted().toList());

        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println("Sum using reduce: " +
                integers.stream().reduce(Integer::sum).get());
    }
}
