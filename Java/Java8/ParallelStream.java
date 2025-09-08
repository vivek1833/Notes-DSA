import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Parallel Streams in Java
 *
 * - A parallel stream splits the workload across multiple threads.
 * - Useful for CPU-intensive tasks on large datasets.
 * - Can improve performance, but adds overhead for small/simple tasks.
 * - Order of execution is NOT guaranteed unless you use forEachOrdered().
 *
 * When to use:
 * ✅ Large datasets
 * ✅ Independent tasks (no shared state)
 * ❌ Small datasets (overhead > benefit)
 * ❌ Side effects / order-sensitive operations
 */
public class ParallelStream {
    public static void main(String[] args) {

        // ----------------------------------------------
        // Performance Example: Factorials
        // ----------------------------------------------
        long startTime = System.currentTimeMillis();
        List<Integer> list = Stream.iterate(1, x -> x + 1).limit(20_000).toList();

        // Sequential
        List<Long> factorialsList = list.stream()
                .map(ParallelStream::factorial)
                .toList();
        long endTime = System.currentTimeMillis();
        System.out.println("Time with sequential stream: " + (endTime - startTime) + " ms");

        // Parallel
        startTime = System.currentTimeMillis();
        factorialsList = list.parallelStream()
                .map(ParallelStream::factorial)
                .toList();
        endTime = System.currentTimeMillis();
        System.out.println("Time with parallel stream: " + (endTime - startTime) + " ms");

        // ----------------------------------------------
        // Caution: Side effects in parallel streams
        // ----------------------------------------------
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        // Sequential cumulative sum works fine
        AtomicInteger sum = new AtomicInteger(0);
        List<Integer> cumulativeSum = numbers.stream()
                .map(sum::addAndGet)
                .toList();
        System.out.println("Expected cumulative sum: [1, 3, 6, 10, 15]");
        System.out.println("Result with sequential stream: " + cumulativeSum);

        // Parallel cumulative sum → WRONG result (race conditions)
        sum.set(0);
        List<Integer> parallelSum = numbers.parallelStream()
                .map(sum::addAndGet)
                .toList();
        System.out.println("Result with parallel stream (incorrect): " + parallelSum);

        // ----------------------------------------------
        // Demonstrating Order
        // ----------------------------------------------
        System.out.println("\nforEach with parallel stream (order not guaranteed):");
        IntStream.rangeClosed(1, 10).parallel().forEach(System.out::print);

        System.out.println("\nforEachOrdered with parallel stream (order preserved):");
        IntStream.rangeClosed(1, 10).parallel().forEachOrdered(System.out::print);

        // ----------------------------------------------
        // Switching between sequential and parallel
        // ----------------------------------------------
        System.out.println("\n\nMixing sequential and parallel:");
        List<Integer> mixed = list.parallelStream()
                .map(x -> x * 2) // parallel
                .sequential() // back to sequential
                .limit(5)
                .toList();
        System.out.println("First 5 doubled numbers: " + mixed);
    }

    // Helper method: factorial calculation
    private static long factorial(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
