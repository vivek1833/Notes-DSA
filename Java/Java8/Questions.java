import java.util.*;
import java.util.Map.Entry;
import java.util.function.Function;
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

                // Convert a Map<String, Integer> (studentName → marks) to a list of student names who scored above 80.
                Map<String, Integer> studentMarks = new HashMap<>();
                studentMarks.put("Sunita", 90);
                studentMarks.put("Vivek", 80);
                studentMarks.put("Ankita", 70);

                List<String> flatStudents = studentMarks.entrySet().stream()
                .filter(s -> s.getValue() > 80)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

                // 🔹 Hard Level (Real-World Use Cases)
                // Given a list of employees (id, name, department, salary):
                List<Employee> employees = new ArrayList<>();
                employees.add(new Employee(1, "A", "IT", 100));
                employees.add(new Employee(2, "B", "HR", 2000));
                employees.add(new Employee(3, "C", "IT", 3000));
                employees.add(new Employee(4, "D", "HR", 4000));
                employees.add(new Employee(5, "E", "IT", 5000));
                employees.add(new Employee(6, "F", "HR", 6000));
                employees.add(new Employee(7, "G", "IT", 7000));
                employees.add(new Employee(8, "H", "HR", 8000));
                employees.add(new Employee(9, "I", "IT", 9000));
                employees.add(new Employee(10, "J", "HR", 10000));

                // Group employees by department in a single list
                Map<String, List<Employee>> empsByDept = employees.stream()
                .collect(Collectors.groupingBy(
                   Employee::getDepartment     
                ));

                // Find the highest-paid employee in each department.
                Map<String, Optional<Employee>> maxSalaryEmpByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
                ));

                // Calculate the average salary of all employees.
                Double avgSalary = employees.stream()
                .collect(Collectors.averagingDouble(Employee::getSalary));

                // Find the first non-repeated character in a string using Streams.
                String s = "vivek";
                Entry<Character, Long> nonRepeatedChar = s.chars().mapToObj(e -> (char) e)
                .collect(Collectors.groupingBy(
                   Function.identity(),
                   LinkedHashMap::new,
                   Collectors.counting()
                )).entrySet().stream()
                .filter(e -> e.getValue() == 1)
                .findFirst()
                .orElse(null);

                // Flatten a list of lists of integers into a single list using flatMap().
                List<List<Integer>> listOfLists = List.of(
                        List.of(1, 2, 3),
                        List.of(4, 5),
                        List.of(6, 7, 8, 9)
                        );

                        List<Integer> flatList = listOfLists.stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList());

                // Use partitioningBy() to divide a list of numbers into even and odd.

                // Given a list of words, find the frequency of each word using Collectors.groupingBy().
                List<String> words = Arrays.asList("Java", "Streams", "API", "Spring");

                LinkedHashMap<String, Long> collect = words.stream()
                .collect(Collectors.groupingBy(
                        Function.identity(),
                        LinkedHashMap::new,
                        Collectors.counting()     
                ));

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
                // Map<String, Optional<Employee>> secondHighestSalary = employees.stream()
                // .collect(Collectors.groupingBy(
                //         Employee::getDepartment,
                //         Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))
                //         ));

                // Get a comma-separated string of all employee names sorted alphabetically.


                // List<Entry<String, Long>> empByCount = employees.stream()
                // .collect(Collectors.groupingBy(
                // Employee::getDepartment,
                // Collectors.counting()
                // )).entrySet().stream()
                // .filter(e -> e.getValue() > 2)
                // .collect(Collectors.toList());

                // Entry<String, Double> maxAvgSalaryByDept = employees.stream()
                //                 .collect(Collectors.groupingBy(
                //                                 Employee::getDepartment,
                //                                 Collectors.averagingDouble(Employee::getSalary)))
                //                 .entrySet().stream()
                //                 .max(Map.Entry.comparingByValue()).orElse(null);            
        }
}