import java.util.*;
import java.util.stream.Stream;
import java.util.stream.Collectors;

class Employee {
    int id;
    String name;
    String department;
    double salary;

    public Employee(int id, String name, String department, double salary) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public String getName() {
        return name;
    }
}

public class Questions {
    public static void main(String[] args) {

        // 🔹 Easy Level (Basics & Syntax)
        // Write a program to create a list of integers and:
        // Print all even numbers using stream().filter().
        List<Integer> nums = Arrays.asList(1, 2, 3, 4);
        List<Integer> evenNums = nums.stream()
                .filter(x -> x % 2 == 0)
                .collect(Collectors.toList());

        // Print the count of odd numbers using count().
        List<Integer> oddNums = Arrays.asList(1, 2, 3, 4, 5, 6);
        long oddCount = oddNums.stream()
                .filter(x -> x % 2 != 0)
                .count();

        // Given a list of strings, print all strings that start with "A" using a
        // Predicate.
        List<String> nameList = Arrays.asList("Akash", "Vivek", "Ram", "Sita");
        List<String> nameCollect = nameList.stream()
                .filter(x -> x.startsWith("A"))
                .collect(Collectors.toList());

        // Use Consumer to print each element of a list.
        nameList.stream()
                .forEach(System.out::println);

        // Use Function<String, Integer> to find the length of each word in a list and
        // collect them into a new list.
        List<Integer> nameSizeList = nameList.stream()
                .map(x -> x.length())
                .collect(Collectors.toList());

        // Write a program that uses Supplier to generate random numbers.
        List<Integer> randomStream = Stream.generate(() -> new Random().nextInt(10))
                .limit(10)
                .collect(Collectors.toList());

        // 🔹 Medium Level (Transformations & Collections)
        // Given a list of integers, square each number and remove duplicates. Collect
        // the result into a Set.
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 2, 1);
        // Set<Double> uniqueNums = integers.parallelStream()
        // .map(x -> Math.pow(x, 2))
        // .collect(Collectors.toSet());

        // Find the maximum and minimum number in a list using stream().max() and
        // stream().min().
        int mx = integers.stream()
                .max(Comparator.naturalOrder())
                .get();

        int mn = integers.stream()
                .min(Comparator.naturalOrder())
                .get();

        // Given a list of names, sort them in Natural order.
        List<String> names = Arrays.asList("Sunita", "Vivek", "Ankita");
        names.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());

        // Reverse order using Comparator and method references.
        names.stream()
        .sorted(Comparator.reverseOrder())
        .collect(Collectors.toList());

        // Use Optional to safely get the length of a string (handle null input).
        String str = "Hello World";
        Optional<Integer> len = Optional.ofNullable((str.length()));

        // Convert a Map<String, Integer> (studentName → marks) to a list of student names who scored above 80.
        Map<String, Integer> studentMarks = new HashMap<>();
        studentMarks.put("Sunita", 90);
        studentMarks.put("Vivek", 80);
        studentMarks.put("Ankita", 70);
        

        // 🔹 Hard Level (Real-World Use Cases)
        // Given a list of employees (id, name, department, salary):

        // Group employees by department in a single list
        // List<Employee> l1 = employees.stream()
        // System.out.println("Grouped by department: " + l1);

        // Find the highest-paid employee in each department.

        // Calculate the average salary of all employees.

        // Find the first non-repeated character in a string using Streams.

        // Flatten a list of lists of integers into a single list using flatMap().

        // Use partitioningBy() to divide a list of numbers into even and odd.

        // Given a list of words, find the frequency of each word using
        // Collectors.groupingBy().

        // 🔹 Interview Ready (Challenging & Practical)
        // Write a program to detect duplicates in a list using Streams.

        // From a list of transactions (id, amount, type), find:
        // The highest transaction amount per type.

        // The total transaction amount grouped by type.

        // Implement a caching system using Supplier and Map where the supplier
        // generates values if not present.

        // Find the longest string in a list without using loops.

        // Given a list of employees:
        // Get the second-highest salary using Streams.

        // Get a comma-separated string of all employee names sorted alphabetically.
    }
}