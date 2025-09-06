import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * StreamDemo - Demonstrates Java 8 Streams API
 *
 * Streams allow processing of collections in a functional, declarative style.
 * Key Benefits:
 * - Simplifies data processing
 * - Embraces functional programming
 * - Improves readability and maintainability
 * - Enables easy parallelism
 *
 * Core Concepts:
 * - Source: Data source (Collection, Array, etc.)
 * - Intermediate Operations: Transformations (map, filter, sorted, distinct...)
 * - Terminal Operations: Produce result (collect, forEach, reduce, count...)
 */
public class JavaStream {
        public static void main(String[] args) {

                // Example: filter even numbers and count them
                List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
                long evenCount = numbers.stream()
                                .filter(x -> x % 2 == 0) // Intermediate operation
                                .count(); // Terminal operation
                System.out.println("Count of even numbers: " + evenCount);

                // ----------------------------
                // Creating Streams
                // ----------------------------

                // 1. From Collections
                List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
                Stream<Integer> streamFromList = list.stream();

                // 2. From Arrays
                String[] array = { "a", "b", "c" };
                Stream<String> streamFromArray = Arrays.stream(array);

                // 3. Using Stream.of()
                Stream<String> streamFromValues = Stream.of("a", "b", "c");

                // 4. Infinite Streams (use limit() to avoid infinite execution)
                Stream<Integer> infiniteGenerated = Stream.generate(() -> 1).limit(5);
                Stream<Integer> infiniteIterated = Stream.iterate(1, x -> x + 1).limit(5);

                // ----------------------------
                // Common Intermediate Operations
                // ----------------------------

                List<Integer> processedNumbers = numbers.stream()
                                .filter(x -> x % 2 != 0) // keep odd numbers
                                .map(x -> x * x) // square them
                                .sorted() // sort ascending
                                .distinct() // remove duplicates
                                .collect(Collectors.toList()); // collect to List
                System.out.println("Processed numbers: " + processedNumbers);

                // ----------------------------
                // Terminal Operations
                // ----------------------------

                // forEach
                numbers.stream().forEach(x -> System.out.print(x + " "));
                System.out.println();

                // reduce
                Optional<Integer> sum = numbers.stream().reduce((a, b) -> a + b);
                sum.ifPresent(s -> System.out.println("Sum using reduce: " + s));

                // collect
                List<String> collected = Arrays.asList("Java", "Streams", "API")
                                .stream()
                                .map(String::toUpperCase)
                                .collect(Collectors.toList());
                System.out.println("Collected: " + collected);

                // ----------------------------
                // Parallel Streams
                // ----------------------------
                long parallelCount = numbers.parallelStream()
                                .filter(x -> x % 2 == 0)
                                .count();
                System.out.println("Even count using parallelStream: " + parallelCount);
        }
}
