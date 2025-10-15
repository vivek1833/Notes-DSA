import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        List<Integer> nums = Arrays.asList(1,2,3,4);


        // Print the count of odd numbers using count().

        // Given a list of strings, print all strings that start with "A" using a
        // Predicate.

        // Use Consumer to print each element of a list.

        // Use Function<String, Integer> to find the length of each word in a list and
        // collect them into a new list.

        // Write a program that uses Supplier to generate random numbers.

        // 🔹 Medium Level (Transformations & Collections)
        // Given a list of integers, square each number and remove duplicates. Collec the result into a Set.

        // Find the maximum and minimum number in a list using stream().max() and stream().min().

        // Given a list of names, sort them in:
        // Natural order.

        // Reverse order using Comparator and method references.

        // Use Optional to safely get the length of a string (handle null input).

        // Convert a Map<String, Integer> (studentName → marks) to a list of student names who scored above 80.

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

        // Given a list of words, find the frequency of each word using Collectors.groupingBy().

        // 🔹 Interview Ready (Challenging & Practical)
        // Write a program to detect duplicates in a list using Streams.

        // From a list of transactions (id, amount, type), find:
        // The highest transaction amount per type.


        // The total transaction amount grouped by type.


        // Implement a caching system using Supplier and Map where the supplier generates values if not present.


        // Find the longest string in a list without using loops.

        // Given a list of employees:
        // Get the second-highest salary using Streams.


        // Get a comma-separated string of all employee names sorted alphabetically.
    }
}