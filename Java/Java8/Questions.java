import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Questions {
    public static void main(String[] args) {

        // 🔹 Easy Level (Basics & Syntax)
        // Write a program to create a list of integers and:
        // Print all even numbers using stream().filter().
        List<Integer> nums = Stream.of(1, 2, 3, 4, 5)
                .filter(x -> x % 2 == 0)
                .toList();
        System.out.println("Even numbers: " + nums);

        // Print the count of odd numbers using count().
        long odds = Stream.of(1, 2, 3, 4, 5, 6).filter(x -> x % 2 != 0).count();
        System.out.println("Odd Count: " + odds);

        // Given a list of strings, print all strings that start with "A" using a
        // Predicate.
        List<String> list = Stream.of("Ashwin", "Vivek", "Mikey", "Anand")
                .filter(x -> x.startsWith("A"))
                .toList();
        System.out.println("List with A: " + list);

        // Use Consumer to print each element of a list.
        list.stream().forEach(System.out::println);

        // Use Function<String, Integer> to find the length of each word in a list and
        // collect them into a new list.
        List<Integer> lengths = list.stream().map(x -> x.length()).toList();
        System.out.println("Lengths: " + lengths);

        // Write a program that uses Supplier to generate random numbers.
        System.out.println("Random numbers: " + Stream.generate(() -> new Random().nextInt(10)).limit(5).toList());

        // 🔹 Medium Level (Transformations & Collections)
        // Given a list of integers, square each number and remove duplicates. Collect
        // the result into a Set.
        Set<Integer> set = Stream.of(1, 1, 2, 3, 4, 5).map(x -> x * x).collect(Collectors.toSet());
        System.out.println("Set: " + set);

        // Find the maximum and minimum number in a list using stream().max() and
        // stream().min().
        int mx = Stream.of(1, 2, 3, 4, 5, 6).max((a, b) -> a - b).get();
        int mn = Stream.of(1, 2, 3, 4, 5, 6).min((a, b) -> a - b).get();
        System.out.println("Max: " + mx);
        System.out.println("Min: " + mn);

        // Given a list of names, sort them in:
        // Natural order.
        List<String> namesSorted = Stream.of("Ashwin", "Vivek", "Mikey", "Anand").sorted((a,b) -> a.compareTo(b)).toList();
        System.out.println("Sorted names: " + namesSorted);

        // Reverse order using Comparator and method references.
        List<String> names = Stream.of("Ashwin", "Vivek", "Mikey", "Anand").sorted((a,b) -> b.compareTo(a)).toList();
        System.out.println("Sorted names: " + names);

        // Use Optional to safely get the length of a string (handle null input).
        List<Integer> nl = Stream.of("Ashwin", "Vivek", "Mikey", "Anand", "").map(x -> x.length()).toList();
        System.out.println("Length of strings: " + nl);

        // Convert a Map<String, Integer> (studentName → marks) to a list of student names who scored above 80.
        Map<String, Integer> marks = new HashMap<>();
        marks.put("Ashwin", 80);
        marks.put("Vivek", 90);
        marks.put("Mikey", 70);
        marks.put("Anand", 60);
        Map<String, Integer> namesWithMarks = marks.entrySet().stream().filter(x -> x.getValue() >= 80).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
        System.out.println("Names with marks > 80: " + namesWithMarks);

        // 🔹 Hard Level (Real-World Use Cases)
        // Given a list of employees (id, name, department, salary):
        List<Employee> employees = Stream.of(
                new Employee(1, "Ashwin", "Sales", 50000),
                new Employee(2, "Vivek", "Sales", 60000),
                new Employee(3, "Mikey", "IT", 70000),
                new Employee(4, "Anand", "IT", 80000)
        ).toList();

        // Group employees by department in a single list
        // List<Employee> l1 = employees.stream()
        // System.out.println("Grouped by department: " + l1);


        // Find the highest-paid employee in each department.

        // Calculate the average salary of all employees.

        // Find the first non-repeated character in a string using Streams.

        // Flatten a list of lists of integers into a single list using flatMap().

        // Use partitioningBy() to divide a list of numbers into even and odd.

        // Given a list of words, find the frequency of each word using Collectors.groupingBy().

        // 🔹 Interview Ready (Challenging & Practical)
        // Write a program to detect duplicates in a list using Streams.
        List<Integer> arr = Stream.of(1,1,2,2,3,4,4,5,6,6).distinct().toList();
        System.out.println("Distinct: " + arr);

        // From a list of transactions (id, amount, type), find:
        // The highest transaction amount per type.


        // The total transaction amount grouped by type.


        // Implement a caching system using Supplier and Map where the supplier generates values if not present.


        // Find the longest string in a list without using loops.
        String longestString = Stream.of("Ashwin", "Vivek", "Mikey", "Anand").max(Comparator.comparingInt(String::length)).get();
        System.out.println("Longest string: " + longestString);

        // Given a list of employees:
        // Get the second-highest salary using Streams.
        List<Employee> empl = Stream.of(
                new Employee(1, "Ashwin", "Sales", 50000),
                new Employee(2, "Vivek", "Sales", 60000),
                new Employee(3, "Mikey", "IT", 70000),
                new Employee(4, "Anand", "IT", 80000)
        ).toList();

        Employee e1 = empl.stream().sorted(Comparator.comparing(Employee::getSalary).reversed()).skip(1).findFirst().get();
        System.out.println("Second highest salary: " + e1);


        // Get a comma-separated string of all employee names sorted alphabetically.
        String e2 = empl.stream().map(Employee::getName).sorted().collect(Collectors.joining(", "));
        System.out.println("Employee names: " + e2);
    }
}

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