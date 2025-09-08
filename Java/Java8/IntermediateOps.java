import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Intermediate Operations in Java Streams
 *
 * - Intermediate operations transform one Stream into another Stream.
 * - They are **lazy**, meaning nothing actually runs until
 * a terminal operation (like forEach, collect, count, reduce) is called.
 *
 * Common Intermediate Operations:
 * 1. filter()
 * 2. map()
 * 3. sorted()
 * 4. distinct()
 * 5. limit()
 * 6. skip()
 * 7. peek()
 * 8. flatMap()
 * (Java 9 additions: takeWhile(), dropWhile())
 */
public class IntermediateOps {
    public static void main(String[] args) {

        List<String> list = Arrays.asList("Vivek", "Akshit", "Ram", "Shyam", "Ghanshyam", "Akshit");

        // --------------------------------------------------
        // 1. filter() - keep only elements that match condition
        // --------------------------------------------------
        long countStartsWithA = list.stream()
                .filter(x -> x.startsWith("A")) // condition
                .count(); // terminal operation
        System.out.println("Names starting with A: " + countStartsWithA);

        // --------------------------------------------------
        // 2. map() - transform elements into new form
        // --------------------------------------------------
        System.out.println("Names in uppercase: " +
                list.stream()
                        .map(String::toUpperCase)
                        .toList());

        // mapToInt example (useful for numeric transformations)
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);
        int sum = numbers.stream()
                .mapToInt(x -> x * 2) // multiply each number by 2
                .sum(); // terminal operation
        System.out.println("Sum of doubled numbers: " + sum);

        // --------------------------------------------------
        // 3. sorted() - sort stream elements
        // --------------------------------------------------
        System.out.println("Sorted alphabetically: " +
                list.stream().sorted().toList());

        System.out.println("Sorted by length: " +
                list.stream()
                        .sorted((a, b) -> a.length() - b.length())
                        .toList());

        // --------------------------------------------------
        // 4. distinct() - remove duplicates
        // --------------------------------------------------
        System.out.println("Unique names starting with A: " +
                list.stream()
                        .filter(x -> x.startsWith("A"))
                        .distinct()
                        .toList());

        // --------------------------------------------------
        // 5. limit() - take first N elements
        // --------------------------------------------------
        System.out.println("First 10 numbers: " +
                Stream.iterate(1, x -> x + 1)
                        .limit(10)
                        .toList());

        // --------------------------------------------------
        // 6. skip() - skip first N elements
        // --------------------------------------------------
        System.out.println("Skip 5, then take 5: " +
                Stream.iterate(1, x -> x + 1)
                        .skip(5)
                        .limit(5)
                        .toList());

        // --------------------------------------------------
        // 7. peek() - debug / see elements as they flow
        // --------------------------------------------------
        Stream.iterate(1, x -> x + 1)
                .skip(10)
                .limit(5)
                .peek(x -> System.out.println("Processing: " + x))
                .count(); // terminal operation needed

        // --------------------------------------------------
        // 8. flatMap() - flatten nested structures
        // --------------------------------------------------
        List<List<String>> listOfLists = Arrays.asList(
                Arrays.asList("apple", "banana"),
                Arrays.asList("orange", "kiwi"),
                Arrays.asList("pear", "grape"));

        System.out.println("Flattened list: " +
                listOfLists.stream()
                        .flatMap(inner -> inner.stream()) // flatten
                        .map(String::toUpperCase)
                        .toList());

        // Another flatMap example: split sentences into words
        List<String> sentences = Arrays.asList(
                "Hello world",
                "Java streams are powerful",
                "flatMap is useful");

        System.out.println("Words extracted: " +
                sentences.stream()
                        .flatMap(sentence -> Arrays.stream(sentence.split(" ")))
                        .map(String::toUpperCase)
                        .toList());

        // --------------------------------------------------
        // Java 9 Additions (for completeness)
        // --------------------------------------------------
        System.out.println("takeWhile < 10: " +
                Stream.iterate(1, x -> x + 1)
                        .takeWhile(x -> x < 10)
                        .toList());

        System.out.println("dropWhile < 10: " +
                Stream.iterate(1, x -> x + 1)
                        .dropWhile(x -> x < 10)
                        .limit(5)
                        .toList());
    }
}
