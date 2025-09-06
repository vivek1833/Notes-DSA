import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.*;
import java.util.stream.Collectors;

/**
 * Demo class to showcase Java 8 Features:
 * - Lambda Expressions
 * - Functional Interfaces (Predicate, Function, Consumer, Supplier, etc.)
 * - Streams API
 * - Method & Constructor References
 * - Optional
 * - Date & Time API
 */
public class Java8Demo {
    public static void main(String[] args) {

        // -------------------- Lambda Expressions --------------------
        // Lambda = anonymous function (no name, no return type, no access modifier)
        Thread t1 = new Thread(() -> System.out.println("Hello from Lambda Thread"));
        t1.start();

        MathOperation sumOperation = (a, b) -> a + b;
        MathOperation subtractOperation = (a, b) -> a - b;
        System.out.println("Sum: " + sumOperation.operate(1, 2));
        System.out.println("Subtraction: " + subtractOperation.operate(5, 3));

        // -------------------- Predicate --------------------
        // Predicate = boolean-returning function with one argument
        Predicate<Integer> isEven = x -> x % 2 == 0;
        System.out.println("Is 4 even? " + isEven.test(4));

        Predicate<String> startsWithV = s -> s.toLowerCase().startsWith("v");
        Predicate<String> endsWithK = s -> s.toLowerCase().endsWith("k");
        System.out.println("Vivek starts with V & ends with K? " + startsWithV.and(endsWithK).test("Vivek"));

        // -------------------- Function --------------------
        // Function = value-returning function with one argument
        Function<Integer, Integer> doubleIt = x -> 2 * x;
        Function<Integer, Integer> tripleIt = x -> 3 * x;
        System.out.println("Double then triple: " + doubleIt.andThen(tripleIt).apply(10));
        System.out.println("Identity function: " + Function.identity().apply(42));

        // -------------------- Consumer --------------------
        // Consumer = void-returning function with one argument
        Consumer<Integer> print = x -> System.out.println("Consumed: " + x);
        print.accept(51);

        List<Integer> numbers = Arrays.asList(1, 2, 3);
        Consumer<List<Integer>> printList = list -> list.forEach(System.out::println);
        printList.accept(numbers);

        // -------------------- Supplier --------------------
        // Supplier = value-returning function with no arguments
        Supplier<String> helloSupplier = () -> "Hello World";
        System.out.println("Supplied: " + helloSupplier.get());

        // -------------------- Combined Example --------------------
        Predicate<Integer> predicate = x -> x % 2 == 0;
        Function<Integer, Integer> square = x -> x * x;
        Consumer<Integer> printer = System.out::println;
        Supplier<Integer> hundredSupplier = () -> 100;

        if (predicate.test(hundredSupplier.get())) {
            printer.accept(square.apply(hundredSupplier.get()));
        }

        // -------------------- BiPredicate, BiConsumer, BiFunction --------------------
        // BiPredicate = boolean-returning function with two arguments
        // BiConsumer = void-returning function with two arguments
        // BiFunction = value-returning function with two arguments
        BiPredicate<Integer, Integer> isSumEven = (x, y) -> (x + y) % 2 == 0;
        System.out.println("Is 5+5 even? " + isSumEven.test(5, 5));

        BiConsumer<Integer, String> biConsumer = (x, y) -> {
            System.out.println("Integer: " + x);
            System.out.println("String: " + y);
        };
        biConsumer.accept(10, "Hello");

        BiFunction<String, String, Integer> biFunction = (x, y) -> (x + y).length();
        System.out.println("Length of concatenated string: " + biFunction.apply("Java", "8"));

        // -------------------- UnaryOperator & BinaryOperator --------------------
        // UnaryOperator = value-returning function with one argument
        // BinaryOperator = value-returning function with two arguments
        UnaryOperator<Integer> doubler = x -> 2 * x;
        BinaryOperator<Integer> adder = (x, y) -> x + y;
        System.out.println("UnaryOperator result: " + doubler.apply(15));
        System.out.println("BinaryOperator result: " + adder.apply(5, 7));

        // -------------------- Method Reference --------------------
        // Method Reference = reference to a method of an object, represented by
        // ::methodName
        List<String> students = Arrays.asList("Ram", "Shyam", "Ghanshyam");
        students.forEach(System.out::println); // method reference instead of lambda

        // -------------------- Constructor Reference --------------------
        // Constructor Reference = reference to a constructor, represented by ::new
        List<String> names = Arrays.asList("A", "B", "C");
        List<MobilePhone> mobilePhones = names.stream()
                .map(MobilePhone::new)
                .collect(Collectors.toList());

        // -------------------- Optional --------------------
        // Optional = container for a value that may or may not be present
        Optional<String> optionalName = Optional.ofNullable("Vivek");
        optionalName.ifPresentOrElse(
                val -> System.out.println("Optional contains: " + val),
                () -> System.out.println("Optional is empty"));

        // -------------------- Date & Time API --------------------
        // LocalDate, LocalDateTime, DateTimeFormatter
        LocalDate today = LocalDate.now();
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        System.out.println("Today's Date: " + today);
        System.out.println("Current DateTime: " + now.format(formatter));
    }
}

// -------------------- Helper Classes & Interfaces --------------------
class MobilePhone {
    String name;

    public MobilePhone(String name) {
        this.name = name;
        System.out.println("Created MobilePhone: " + name);
    }
}

@FunctionalInterface
interface MathOperation {
    int operate(int a, int b);
}
