# ✅ Java Interview Questions - Level 1

## 1. Difference between JDK, JRE, and JVM

- **JVM (Java Virtual Machine):** Executes Java bytecode. It is platform-dependent but provides a platform-independent execution environment for Java programs. It handles memory management, garbage collection, and runtime optimizations.
- **JRE (Java Runtime Environment):** Provides libraries, JVM, and components necessary to run Java applications. It does not contain development tools like compiler/debugger.
- **JDK (Java Development Kit):** Superset of JRE, includes tools like compiler (`javac`), debugger, JavaDoc, and other utilities for developing and running Java applications.

---

## 2. Main Features of Java (OOP Principles)

- **Encapsulation:** Wrapping data (fields) and methods together. Achieved using access modifiers.
- **Inheritance:** Code reusability by deriving classes from parent classes.
- **Polymorphism:** Ability to use one interface with multiple implementations (method overloading and overriding).
- **Abstraction:** Hiding implementation details and exposing only essential features.

---

## 3. Primitive Data Types in Java

- byte (8-bit)
- short (16-bit)
- int (32-bit)
- long (64-bit)
- float (32-bit)
- double (64-bit)
- char (16-bit Unicode)
- boolean (true/false)

---

## 4. Autoboxing and Unboxing

- **Autoboxing:** Automatic conversion of primitive to wrapper object.
- **Unboxing:** Conversion of wrapper object back to primitive.

```java
Integer x = 10; // Autoboxing
int y = x;     // Unboxing
```

---

## 5. Access Modifiers

- **private:** Accessible only within the same class.
- **default (no modifier):** Accessible within the same package.
- **protected:** Accessible within package and subclasses.
- **public:** Accessible everywhere.

---

## 6. Package in Java

- A package groups related classes and interfaces together. Provides modularization and prevents naming conflicts.

```java
package com.myapp.utils;
```

---

## 7. Static Keyword

- **static variables:** Shared among all objects of a class (class-level memory).
- **static methods:** Belong to the class, can be accessed without creating an object.
- **static blocks:** Used for static initializations.

---

## 8. Constructor in Java

- Special method to initialize objects.

### Types:

- Default constructor (no args)
- Parameterized constructor
- Copy constructor (custom in Java)

```java
class Person {
   String name;
   Person(String n) { this.name = n; }
}
```

---

## 9. Can we use static methods in a constructor?

- Yes, you can call static methods from a constructor, but you cannot declare a constructor as static.

---

## 10. Inheritance in Java

- Mechanism of deriving a class from another class.

### Types:

- Single
- Multilevel
- Hierarchical
- Multiple (via interfaces only)

---

## 11. Polymorphism

- **Compile-time (method overloading).**
- **Runtime (method overriding).**

```java
class Shape {
    void draw(){}
}
class Circle extends Shape {
    void draw(){
        System.out.println("Circle");
    }
}
```

---

## 12. Encapsulation vs Abstraction

- **Encapsulation:** Hides data using access modifiers (focus on _how data is accessed_).
- **Abstraction:** Hides implementation details using abstract classes/interfaces (focus on _what operations are exposed_).

---

## 13. Interface vs Abstract Class

- **Interface:** For full abstraction, multiple inheritance, all methods abstract (till Java 7). Supports default/static methods (Java 8+).
- **Abstract class:** Can have concrete + abstract methods, constructors, fields.

---

## 14. Marker Interface

- Interface without methods. Used to mark classes for special behavior by JVM/Frameworks.
- Example: `Serializable`, `Cloneable`.

---

## 15. String vs StringBuilder vs StringBuffer

- **String:** Immutable, thread-safe, stored in String pool.
- **StringBuilder:** Mutable, not thread-safe, better performance in single-threaded scenarios.
- **StringBuffer:** Mutable, thread-safe (synchronized).

---

## 16. String literal vs new operator

- **String literal:** Stored in string pool, reused if already exists.
- **new operator:** Always creates a new object in heap.

---

## 17. String Pool

- A special memory region inside the heap where string literals are stored and reused.

---

## 18. Advantages of String Pool

- Saves memory, improves performance.

---

## 19. Why String is immutable?

- For thread-safety, security (hashcode caching, classloader integrity), and memory efficiency.

---

## 20. Collection in Java

- A framework that provides classes and interfaces for storing/manipulating groups of objects. Example: `List`, `Set`, `Map`.

---

## 21. Array vs ArrayList

- **Array:** Fixed size, stores primitives and objects.
- **ArrayList:** Dynamic size, stores only objects, part of Collections framework.

---

## 22. ArrayList vs LinkedList

- **ArrayList:** Backed by dynamic array, faster random access.
- **LinkedList:** Doubly-linked list, faster insert/delete in middle.

---

## 23. HashMap

- Key-value pair storage, O(1) average operations.

```java
Map<String,Integer> map = new HashMap<>();
map.put("a",1);
```

---

## 24. HashSet vs TreeSet

- **HashSet:** Unordered, allows null, O(1) operations.
- **TreeSet:** Sorted, no nulls, O(log n) operations.

---

## 25. Iterator

- Object to traverse collections. Supports fail-fast iteration.

---

## 26-28. Set vs List vs Array

- **Set:** Unique elements, unordered (HashSet) or sorted (TreeSet).
- **List:** Ordered, allows duplicates (ArrayList, LinkedList).
- **Array:** Fixed size, stores primitives/objects.

---

## 29. Exception

- Abnormal event disrupting program flow.

### Types:

- Checked
- Unchecked
- Error

---

## 30. Checked vs Unchecked Exceptions

- **Checked:** Compile-time (IOException, SQLException).
- **Unchecked:** Runtime (NullPointerException, ArithmeticException).

---

## 31. try-catch-finally

- **try:** Risky code.
- **catch:** Handle exception.
- **finally:** Always executes (closing resources).

---

## 32. Can try without catch?

- Yes, if finally is present.

---

## 33. throw vs throws

- **throw:** Used to explicitly throw an exception.
- **throws:** Declares exceptions in method signature.

---

## 34. User-defined exceptions

- Custom exception classes extending Exception/RuntimeException.
- Exception - checked (compile-time) , RuntimeException - unchecked (run-time).

```java
class MyException extends Exception {
    public MyException(String msg){
        super(msg);
    }
}
```

## 35. Exception Hierarchy

- In Java, all exceptions inherit from the Throwable class, which has two main branches: `Error` and `Exception`.

  - `Error` represents serious problems (like OutOfMemoryError) that applications should not try to handle.
  - `Exception` is for conditions that applications might want to catch and handle.

- Under Exception, we have:
  - Checked exceptions – Must be declared in method signatures or caught. Example: `IOException`, `SQLException`.
  - Unchecked exceptions – Subclasses of RuntimeException, which the compiler does not force you to handle. Example: `NullPointerException`, `IllegalArgumentException`.

```
java.lang.Throwable
│
├── Error (Unrecoverable, system-level errors)
│   └── OutOfMemoryError, StackOverflowError, etc.
│
└── Exception (Recoverable, application-level errors)
    │
    ├── Checked Exceptions (Must be declared or caught)
    │   ├── IOException
    │   ├── SQLException
    │   ├── ClassNotFoundException
    │   └── CustomCheckedException (extends Exception)
    │
    └── Unchecked Exceptions (Runtime exceptions)
        ├── NullPointerException
        ├── IllegalArgumentException
        ├── IndexOutOfBoundsException
        ├── ArithmeticException
        └── CustomUncheckedException (extends RuntimeException)
```

---

## 36. NullPointerException

- Occurs when calling methods on null references.
- Prevention: Use Optional, null checks, defensive coding.

---

## 37. ClassCastException

- Occurs during invalid typecasting.

---

## 38. Error vs Exception

- **Error:** Serious system issues (OutOfMemoryError).
- **Exception:** Recoverable program issues.

---

## 39. break vs continue

- **break:** Exits loop.
- **continue:** Skips current iteration.

---

## 40. == vs equals()

- **==** compares references.
- **equals()** compares values (can be overridden).

---

## 41. @SpringBootApplication

- Combines `@Configuration`, `@EnableAutoConfiguration`, `@ComponentScan`.

---

## 42. @Component vs @Service vs @Repository

- **@Component:** Generic component.
- **@Service:** Business logic.
- **@Repository:** Persistence/DAO layer, exception translation.

---

## 43. Dependency Injection

- Design pattern where dependencies are injected rather than created inside a class.

---

## 44. @Autowired

- Annotation for automatic dependency injection.

---

## 45. Spring Boot Starters

- Predefined dependencies for specific features (`spring-boot-starter-web`, etc).

---

## 46. application.properties

- External configuration file for Spring Boot apps.

---

## 47. Spring vs Spring Boot

- **Spring:** Core framework, manual configuration.
- **Spring Boot:** Opinionated, auto-configuration, production-ready defaults.

---

## 48. IOC Container in Spring

- Inversion of Control container manages lifecycle, dependencies, and configuration of beans.

---

# ✅ Java Interview Questions – Level 2

## 1. Contract between `hashCode()` and `equals()`

**Rules:**

1. If `a.equals(b)` is `true`, then `a.hashCode() == b.hashCode()` **must** be true.
2. If `a.equals(b)` is `false`, hash codes **may** collide but should be well-distributed.
3. `equals` must be **reflexive, symmetric, transitive, consistent**, and return `false` for `null`.
4. Hash-relevant fields should be **immutable** while the object is in a hash-based collection.

**Why it matters:** Violating the contract breaks `HashMap`, `HashSet`, `ConcurrentHashMap` lookups (lost or duplicate keys).

**Good implementation:**

```java
import java.util.Objects;

final class User {
    private final String email;     // immutable identifier
    private final String name;

    User(String email, String name) {
        this.email = email;
        this.name = name;
    }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User other = (User) o;
        return Objects.equals(this.email, other.email);
    }

    @Override public int hashCode() {
        return Objects.hash(email); // use the same fields as equals
    }
}
```

**Pitfall:** Using mutable fields for hash/equals causes key “disappearance” after mutation.

---

## 2. Internal working of `HashMap`

- **Array of buckets** (`Node<K,V>[] table`), index = `hash(key) & (n - 1)` where `n` is capacity (power of two).
- **Hash mixing:** `hash = h ^ (h >>> 16)` spreads high bits to reduce collisions.
- **Bucket structure:** singly linked list of nodes; **since Java 8**, bins **treeify** to a Red-Black Tree (O(log N)) when collisions are heavy.
- **Treeify thresholds (JDK 8+):**
  - `TREEIFY_THRESHOLD = 8`
  - `UNTREEIFY_THRESHOLD = 6`
  - `MIN_TREEIFY_CAPACITY = 64` (below this, resize instead of treeify).
- **Load factor & resizing:** default capacity `16`, default load factor `0.75`. When `size > threshold (capacity * loadFactor)`, capacity doubles and entries are **rehashed**. During resize, nodes either **stay in same index** or move to `index + oldCap` (bit-splitting optimization).
- **Nulls:** `HashMap` allows one `null` key and multiple `null` values.
- **Iterators:** fail-fast (best-effort) on structural modifications outside the iterator.

---

## 3. What happens on `HashMap` collision?

- Entries sharing the same bucket index form a **linked list**, then **tree bin** when size ≥ 8 (and capacity ≥ 64).
- Lookup degrades from O(1) average to O(N) worst-case for lists; O(log N) for tree bins.

```java
Map<Key, String> m = new HashMap<>();
// If Key.hashCode() returns same value for many keys, they collide and go to same bucket.
```

---

## 4. What is the load factor in `HashMap`?

- **Definition:** `size / capacity`. When it exceeds the configured **load factor** (default **0.75**), the map resizes (rehash + capacity × 2).
- **Trade‑off:** Lower load factor → fewer collisions, more memory; higher load factor → more collisions, less memory.

---

## 5. `HashMap` vs `LinkedHashMap` vs `TreeMap`

| Feature    | HashMap                      | LinkedHashMap                                           | TreeMap                                                |
| ---------- | ---------------------------- | ------------------------------------------------------- | ------------------------------------------------------ |
| Ordering   | None                         | **Insertion** order (or **access** order if configured) | **Sorted** by key (natural or comparator)              |
| Complexity | O(1) avg                     | O(1) avg                                                | O(log N)                                               |
| Nulls      | 1 null key, many null values | same as HashMap                                         | **No** null keys (Comparator/compareTo must handle)    |
| Use cases  | General-purpose              | Caches, predictable iteration                           | Range queries, ordered views, `subMap/headMap/tailMap` |

**LRU cache with `LinkedHashMap`:**

```java
class LruCache<K, V> extends LinkedHashMap<K, V> {
    private final int capacity;
    LruCache(int capacity) {
        super(capacity, 0.75f, true); // accessOrder = true
        this.capacity = capacity;
    }
    @Override protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
        return size() > capacity;
    }
}
```

---

## 6. `HashMap` vs `LinkedHashMap` vs `ConcurrentHashMap`

| Aspect                        | HashMap               | LinkedHashMap          | ConcurrentHashMap                                            |
| ----------------------------- | --------------------- | ---------------------- | ------------------------------------------------------------ |
| Thread-safety                 | Not thread-safe       | Not thread-safe        | **Thread-safe** with high concurrency                        |
| Ordering                      | Unordered             | Insertion/access order | Unordered (per-bin order)                                    |
| Nulls                         | Allows null key/value | Allows null            | **Disallows null** keys/values                               |
| Iterators                     | Fail-fast             | Fail-fast              | **Weakly consistent** (no `ConcurrentModificationException`) |
| Performance under concurrency | N/A                   | N/A                    | Much better than global synchronized maps                    |

---

## 7. Convert a `HashMap` to `ArrayList`

```java
Map<String, Integer> map = Map.of("A",1, "B",2, "C",3);

List<Map.Entry<String,Integer>> entries = new ArrayList<>(map.entrySet());
List<String> keys   = new ArrayList<>(map.keySet());
List<Integer> values = new ArrayList<>(map.values());
```

---

## 8. `Comparable` vs `Comparator`

- **Comparable<T>**: defines **natural order** inside the class via `compareTo`. One order per type.
- **Comparator<T>**: defines **external order(s)**, multiple strategies, doesn’t change the class.

```java
class Person implements Comparable<Person> {
    String name; int age;
    @Override public int compareTo(Person o) {
        return Integer.compare(this.age, o.age); // natural order by age
    }
}

Comparator<Person> byName = Comparator.comparing(p -> p.name);
Comparator<Person> byAgeThenName = Comparator
        .comparingInt((Person p) -> p.age)
        .thenComparing(p -> p.name);
```

---

## 9. What is `ConcurrentHashMap` and how does it work?

- A high-performance **thread-safe** map.
- **Reads** are mostly lock-free; **updates** use fine-grained locking at **bin** level (synchronizing on the bin head) and CAS for table init/resize control.
- **No global lock**; resizing is cooperative among threads.
- **Iterators** are **weakly consistent** (reflect some, not necessarily all, concurrent changes; no exceptions).
- **Null keys/values are forbidden** to avoid ambiguity during concurrent operations.

```java
ConcurrentMap<String, Long> counters = new ConcurrentHashMap<>();
counters.merge("hits", 1L, Long::sum);
counters.computeIfAbsent("user:123", k -> 0L);
```

---

## 10. `Hashtable` vs `ConcurrentHashMap`

| Aspect      | Hashtable                                                                                      | ConcurrentHashMap             |
| ----------- | ---------------------------------------------------------------------------------------------- | ----------------------------- |
| Concurrency | **All methods synchronized** (single lock)                                                     | Fine-grained locking + CAS    |
| Performance | Poor under contention                                                                          | Scales with cores             |
| Iterators   | Fail-fast for iterator; legacy `Enumeration` (from `keys()`/`elements()`) is **not** fail-fast | **Weakly consistent**         |
| Nulls       | **No** null keys/values                                                                        | **No** null keys/values       |
| Status      | Legacy (avoid in new code)                                                                     | Preferred for concurrent maps |

---

## 11. `Vector` vs `ArrayList`

| Aspect        | Vector                          | ArrayList        |
| ------------- | ------------------------------- | ---------------- |
| Thread-safety | Synchronized methods            | Not synchronized |
| Performance   | Slower                          | Faster           |
| Growth        | Can grow by `capacityIncrement` | Grows by ~1.5x   |
| Status        | Legacy (avoid)                  | Preferred        |

---

## 12. `HashMap` vs `Hashtable`

- Thread-safety: `HashMap` **not** thread-safe; `Hashtable` synchronized (single lock).
- Nulls: `HashMap` allows **one null key** and many null values; `Hashtable` allows **none**.
- Performance: `HashMap` faster; `Hashtable` legacy.
- Iteration: Both have iterators; `Hashtable` also has **legacy `Enumeration`** methods.

---

## 13. What is a Functional Interface?

- An interface with **exactly one abstract method** (SAM). May have default/static methods.
- Annotate with `@FunctionalInterface` for compile-time checks.
- Examples: `Runnable`, `Callable<T>`, `Comparator<T>`, `Supplier<T>`, `Function<T,R>`, `Consumer<T>`, `Predicate<T>`.

```java
@FunctionalInterface
interface Transformer<T, R> {
    R apply(T t);
    // default methods allowed
    default Transformer<T,R> andThen(Transformer<R,R> after) { /* ... */ return null; }
}
```

---

## 14. Lambdas, Streams, and `Optional`

- **Lambdas:** concise behavior as data, capture effectively-final variables.
- **Streams:** declarative data processing with lazy evaluation, fusion, and optional parallelism (`parallel()`).
- **Optional:** container to represent presence/absence; encourages null-safe APIs.

```java
List<Integer> nums = List.of(1,2,3,4,5);
int sumSquaresOfEven = nums.stream()
    .filter(n -> n % 2 == 0)
    .map(n -> n * n)
    .reduce(0, Integer::sum);

Optional<String> email = findEmailById(42);
String domain = email.map(s -> s.substring(s.indexOf('@') + 1))
                     .orElse("unknown");
```

---

## 15. `map()` vs `flatMap()` in Java 8

- `map(f)` transforms **T → R**, resulting in `Stream<R>`.
- `flatMap(f)` where `f: T → Stream<R>` flattens nested streams/sequences.

```java
List<List<Integer>> matrix = List.of(List.of(1,2), List.of(3,4));
List<Integer> flat = matrix.stream()
    .flatMap(List::stream)
    .collect(Collectors.toList()); // [1,2,3,4]
```

---

## 16. What is Stream API and its advantages?

- **Stream API**: a high-level, lazy, declarative pipeline for bulk operations on data sources (`Collections`, arrays, IO, etc.).
- **Advantages:** readability, less boilerplate, **lazy** & **fused** ops, easy parallelism, improved testability/composability.

---

## 17. What is a Stream pipeline?

- A sequence: **source** → **intermediate ops** (0..n, lazy) → **terminal op** (executes).
- Example: `source.stream().filter(...).map(...).sorted(...).collect(...)`.

---

## 18. Intermediate vs Terminal operations

- **Intermediate (lazy):** `filter`, `map`, `flatMap`, `sorted`, `distinct`, `peek`, `limit`, `skip`.
- **Terminal (eager execution):** `forEach`, `collect`, `reduce`, `sum`, `count`, `anyMatch/allMatch/noneMatch`, `findFirst/findAny`.

---

## 19. Using Stream API: find the **second largest salary** from a list of employees

```java
class Employee {
    private final String name;
    private final int salary;
    Employee(String name, int salary) { this.name = name; this.salary = salary; }
    public String getName() { return name; }
    public int getSalary() { return salary; }
}

List<Employee> employees = List.of(
    new Employee("A", 100),
    new Employee("B", 200),
    new Employee("C", 300),
    new Employee("D", 200)
);

// Distinct salaries, then pick the 2nd largest
int secondLargest = employees.stream()
    .map(Employee::getSalary)
    .distinct()
    .sorted(Comparator.reverseOrder())
    .skip(1)
    .findFirst()
    .orElseThrow(() -> new NoSuchElementException("Not enough distinct salaries"));

System.out.println(secondLargest);
```

**If you need the employee(s) with the 2nd largest salary:**

```java
int target = employees.stream().map(Employee::getSalary).distinct()
    .sorted(Comparator.reverseOrder()).skip(1).findFirst()
    .orElseThrow();

List<Employee> result = employees.stream()
    .filter(e -> e.getSalary() == target)
    .collect(Collectors.toList());
```

---

## 20. Use of Stream API in projects

- **ETL/aggregation:** compute metrics, groupings, rollups.
- **Domain transformations:** mapping DTO ↔ entity, validations.
- **Filtering and joins:** join data from multiple sources in memory (bounded sizes).
- **Parallel processing:** CPU-bound transformations (`parallelStream()`), be mindful of thread-safety and splitting characteristics.
- **Pitfalls:** avoid stateful lambdas, beware of `parallel()` with small data or poor spliterators, prefer collectors over manual mutation, don’t overuse `peek` (testing only).

---

## 21. Lambda expressions vs Anonymous classes

| Aspect      | Lambda                                           | Anonymous Class                                                 |
| ----------- | ------------------------------------------------ | --------------------------------------------------------------- |
| Target      | Functional interface (SAM)                       | Any class/interface                                             |
| Syntax      | Concise, uses **lexical scoping**                | Verbose, creates a new named type                               |
| `this`      | Refers to **enclosing** instance                 | Refers to the **anonymous class** instance itself               |
| Performance | No extra class at runtime (uses `invokedynamic`) | Extra class; slightly more overhead                             |
| Use cases   | Behavior-as-data, Streams, callbacks             | When you need extra fields/methods or multiple abstract methods |

```java
// Lambda
Runnable r1 = () -> System.out.println("Run");

// Anonymous class
Runnable r2 = new Runnable() {
    @Override public void run() { System.out.println("Run"); }
};
```

---

### Bonus: Collision‑resistant key tips

- Prefer immutable keys with high-quality `hashCode()` (e.g., `Objects.hash(a,b,c)`).
- Avoid custom poor hashes (e.g., constant or low-variance).
- For composite keys, either use records (Java 16+) or make a dedicated immutable key class.

```java
record SessionKey(long userId, long deviceId) { } // equals/hash auto-generated
```

## 21. Lambda expressions vs Anonymous classes?

- **Anonymous Classes**: Allow instantiating classes that implement interfaces/extend classes without explicitly creating a subclass.
- **Lambda Expressions**: Provide a more concise syntax for implementing functional interfaces.

**Code Example:**

```java
// Anonymous Class
Runnable r1 = new Runnable() {
    public void run() {
        System.out.println("Running with Anonymous Class");
    }
};

// Lambda Expression
Runnable r2 = () -> System.out.println("Running with Lambda");
```

- Lambdas are shorter, easier to read, and enable functional programming.
- Anonymous classes are still needed when working with abstract classes or multiple methods.

---

## 22. What are Sealed Classes?

- Introduced in **Java 15 (preview)**, sealed classes restrict which classes can extend or implement them.
- Helps enforce strict class hierarchies.

**Example:**

```java
public sealed class Vehicle permits Car, Truck {}

final class Car extends Vehicle {}
final class Truck extends Vehicle {}
```

---

## 23. Fail-safe vs Fail-fast Iteration

- **Fail-fast**: Throws `ConcurrentModificationException` if the collection is modified during iteration.
- **Fail-safe**: Iterates over the collection, even if it is modified during iteration.

**Code Example:**

```java
List<String> list = new ArrayList<>();

// Fail-safe
for (String s : list) {
    // ...
}

// Fail-fast
for (String s : list) {
    // ...
    list.remove(0);
}
```

---

## 24. Cloneable: Deep vs Shallow Copy

- **Shallow Copy**: Copies object references, not actual objects. Means changes to one object affect the other.
- **Deep Copy**: Creates a full independent copy. Means changes to one object do not affect the other.

```java
class Address implements Cloneable {
    String city;
    public Address(String city) { this.city = city; }
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class Person implements Cloneable {
    String name;
    Address address;
    public Person(String name, Address address) {
        this.name = name; this.address = address;
    }
    protected Object clone() throws CloneNotSupportedException {
        // Deep copy
        Person p = (Person) super.clone();
        p.address = (Address) address.clone();
        return p;
    }
}
```

---

## 25. Java Memory Management

- Divided into **Stack (method calls, primitives)** and **Heap (objects, instance vars)**.
- Managed by **Garbage Collector**.
- Includes memory areas like **Young Gen (Eden + Survivor spaces)**, **Old Gen**, and **Metaspace**.

---

## 26. Garbage Collection in Java

- Reclaims memory from unreachable objects.
- Types:
  - **Serial GC**
  - **Parallel GC**
  - **CMS (Concurrent Mark-Sweep)**
  - **G1 Garbage Collector**

---

## 27. Stack vs Heap Memory

- **Stack**: Stores primitive values and references to objects; method execution is LIFO. It is not shared among threads and has a fixed size.
- **Heap**: Stores actual objects. Shared among thread and has no fixed size.

---

## 28. wait() vs sleep()

- `wait()`:

  - Defined in Object class.
  - Used for inter-thread communication.
  - Releases the object’s monitor lock while waiting.
  - Must be called inside a synchronized block/method.
  - Thread waits until it's notified via notify()/notifyAll().

- `sleep()`
  - Defined in Thread class.
  - Pauses the thread for a specified time.
  - Does not release any locks held by the thread.
  - Can be called from anywhere (no synchronization needed).

---

## 29. volatile keyword

The volatile keyword is used to mark a variable as being stored in main memory, not in a thread's local cache. When a variable is declared as volatile, it ensures that:

- All reads of that variable go directly to main memory.
- All writes to that variable are immediately visible to other threads.

---

## 30. ThreadLocal

- ThreadLocal gives each thread its own separate copy of a variable.
- Even if 10 threads are using the same code, each one will get and set its own value, like it’s in a private bubble.

```java
ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 1);
```

---

## 31. Deadlock in Java

- A deadlock occurs when two or more threads are waiting forever for each other to release resources (locks), and none of them can proceed.

**Avoid by:**

1. Always lock in the same order.
2. Use tryLock() with timeout (from java.util.concurrent.locks).
3. Use higher-level concurrency utilities like ExecutorService , ConcurrentHashMap.

---

## 32. Runnable vs Callable

- **Runnable**: Runnable.run() does not return a result and cannot throw checked exceptions.
- **Callable**: Callable.call() returns a result and can throw checked exceptions.

```java
ExecutorService executor = Executors.newFixedThreadPool(2);

// Runnable example
Runnable task1 = () -> System.out.println("Runnable task");
executor.submit(task1);
// returns Future<?> (but result is always null)

// Callable example
Callable<String> task2 = () -> "Callable result";
Future<String> future = executor.submit(task2);
// returns Future<String>

```

---

## 33. Synchronized Methods vs Blocks

- **Method**: Locks the whole method.
- **Block**: Locks only specific section of code.

```java
synchronized void method() {}
synchronized(this) { /* block */ }
```

---

## 34. Spring Boot Auto-Configuration

- Spring Boot Auto-Configuration is a feature that automatically configures Spring application components based on the dependencies available on the classpath and the properties defined in `application.properties` or `application.yml`.
- It helps reduce boilerplate code and setup time by applying sensible defaults. For example, if Spring Boot detects that `spring-boot-starter-web` is on the classpath, it auto-configures a web server, request mapping, and JSON support — without any manual configuration.
- This behavior is enabled through the `@EnableAutoConfiguration` annotation, which is part of `@SpringBootApplication`. It uses conditional annotations like `@ConditionalOnClass` or `@ConditionalOnMissingBean` to decide what to configure.

---

## 35. @Configuration and @Bean

- `@Configuration`: Marks class as a source of bean definitions.
- `@Bean`: Declares a bean.

```java
@Configuration
class AppConfig {
    @Bean
    public MyService service() { return new MyService(); }
}
```

---

## 36. Profiles in Spring Boot (@Profile)

- A profile in Spring is a way to segregate parts of your application configuration and make it only available in certain environments.
- Example: You might have different data sources for development and production — you can define two @Beans with `@Profile("dev")` and `@Profile("prod")`.

```java
@Configuration
public class AppConfig {

    @Bean
    @Profile({"dev", "test"})
    public DataSource devDataSource() {
        return new HikariDataSource("jdbc:h2:mem:devdb");
    }

    @Bean
    @Profile("prod")
    public DataSource prodDataSource() {
        return new HikariDataSource("jdbc:mysql://prod-db-url");
    }
}
```

---

## 37. @Transactional

- Manages transactions declaratively.
- Benefits: Rollback on failure, consistent DB state.
- RuntimeException or Error is thrown, Transaction rolls back
- Checked exception thrown, Does not rollback by default (can be configured).

```java
@Transactional(rollbackFor = Exception.class)
public void someMethod() throws Exception {
    // Will rollback even on checked exception
}
```

---

## 38. CommandLineRunner & ApplicationRunner

In Spring Boot (Java), CommandLineRunner and ApplicationRunner are two functional interfaces that allow you to run code after the application context has been loaded and before the application is fully up (i.e., before it's accepting requests in a web application).
They’re typically used for things like:

- Initializing data in the database
- Running background tasks at startup
- Logging startup info
- Validating application state

```java
@SpringBootApplication
public class MyApp implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        // do something
    }
}
```

```java
@SpringBootApplication
public class MyApp implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        // do something
    }
}
```

If you implement both, both will run. You can control the order using @Order(1) and @Order(2).

---

## 39. Embedded Server (Tomcat)

- Spring Boot includes Tomcat as the default embedded server via the spring-boot-starter-web dependency
- No need for external deployment.
- Registers a DispatcherServlet to handle HTTP requests

---

## 40. Override Default Auto-Configurations

- Use `@ConditionalOnMissingBean`, exclude configs via `@SpringBootApplication(exclude = ...)`.

---

## 41. Handling Circular Dependencies

- Use `@Lazy`, redesign beans, or use setter injection.

---

## 42 & 43 & 44. @Qualifier vs @Primary vs @Autowired

- `@Autowired`: Injects dependencies.
- `@Primary`: Default bean when multiple matches.
- `@Qualifier`: Explicitly specify which bean.

```java
@Service("fastService")
class FastService {}

@Autowired
@Qualifier("fastService")
Service service;
```

---

## 45. @ExceptionHandler vs @ControllerAdvice

- `@ExceptionHandler`: Handles exceptions in one controller.
- `@ControllerAdvice`: Global exception handler.

---

## 46. @ComponentScan vs @EnableAutoConfiguration

- `@ComponentScan`: Scans for beans in base packages.
- `@EnableAutoConfiguration`: Enables Spring Boot’s auto-configuration.

---

## 47. @Async vs @Scheduled

- `@Async`: Runs method in background thread.
- `@Scheduled`: Runs method on schedule (cron/fixed rate).

---

## 48. @Cacheable vs @CacheEvict

- `@Cacheable`: Stores method result in cache.
- `@CacheEvict`: Removes entries from cache.

---

## 49. Lazy Loading vs Eager Loading

- **Lazy**: Objects loaded only when needed.
- **Eager**: Objects loaded immediately with parent.

---

## 50. persist() vs merge()

- `persist()`: Makes transient entity managed.
- `merge()`: Updates detached entity into persistence context.

---

## 51. save() vs saveAndFlush()

- `save()`: Saves entity but may delay flush.
- `saveAndFlush()`: Saves and immediately flushes changes to DB.

---

## 52. get() vs load() in Hibernate

- `get()`: Returns entity or null if not found.
- `load()`: Returns proxy; throws exception if entity not found.

# ✅ Java Interview Questions – Level 3

## 1. How does the JVM work internally? (Class Loader, GC, memory areas)

**Explanation:** JVM executes Java bytecode and provides an abstraction over the OS and CPU. Key components:

- **Class Loader Subsystem**: Loads `.class` files into memory. Follows a _delegation model_ (Bootstrap → Extension → System → Application/custom). Loading stages: _Loading, Linking (verification, preparation, resolution), Initialization_.
- **Runtime Data Areas**:

  - **Method Area (PermGen / Metaspace)**: Stores class metadata, static variables, constant pool. In modern JVMs (Java 8+), class metadata is stored in _Metaspace_ (native memory) instead of PermGen.
  - **Heap**: All objects (instance data) are allocated here. Divided into generations: _Young (Eden + Survivor)_ and _Old/Tenured_. Most GC activity happens here.
  - **Stack**: Each thread gets its own stack containing frames for method calls (local variables, operand stack, etc.). Primitive values and object references live here.
  - **Program Counter (PC) Register**: Points to the current JVM instruction.
  - **Native Method Stacks**: For native (JNI) calls.

- **Garbage Collector (GC)**: Reclaims unused objects. Generational collectors (like G1, Parallel, CMS historically) assume most objects die young. GC algorithms: Mark-and-Sweep, Mark-Compact, Copying, Generational, Concurrent Mark Sweep, Garbage-First (G1), ZGC, Shenandoah.

**Key interview points:** Class loader delegation, difference between Metaspace and PermGen, generational GC, stop-the-world pauses vs concurrent collectors, tuning with JVM flags (`-Xmx`, `-Xms`, `-XX:+UseG1GC`, `-XX:MaxMetaspaceSize`).

---

## 2. What are different types of class loaders in Java?

**Explanation:** The common class loaders are:

- **Bootstrap ClassLoader** (native, loads JDK core classes from `rt.jar` or modular runtime). It has no parent in Java terms.
- **Extension (Platform) ClassLoader** (loads platform libraries).
- **System (Application) ClassLoader** (loads classes from application classpath).
- **Custom ClassLoaders**: You can extend `ClassLoader` to implement isolation, dynamic loading, hot-reloading, or plugin systems.

**Key interview points:** Delegation model — class loader first delegates to its parent; visibility rules; how `Thread.currentThread().getContextClassLoader()` is used; class unloading constraints (classes can be unloaded only when the ClassLoader itself is garbage collected).

---

## 3. What are phantom references in Java?

**Explanation:** Java provides `Reference` classes: `SoftReference`, `WeakReference`, and `PhantomReference` (in `java.lang.ref`).

- **SoftReference**: Used for caches; objects are reclaimed only when JVM needs memory.
- **WeakReference**: Collected in the next GC if no strong references exist.
- **PhantomReference**: Always returns `null` from `get()`. It’s enqueued **after** the object has been finalized but **before** the memory is reclaimed. Used to perform post-mortem cleanup and determine exactly when an object is phantom-reachable. `PhantomReference` requires a `ReferenceQueue` to learn when the object is collected.

**Example use-case:** Cleaner implementations, custom memory management with native resources, avoiding resurrection in finalizers.

**Code sketch:**

```java
ReferenceQueue<MyObject> queue = new ReferenceQueue<>();
PhantomReference<MyObject> pr = new PhantomReference<>(new MyObject(), queue);
// Poll the queue to know when MyObject is enqueued (eligible for reclamation)
```

---

## 4. How does Java handle memory leaks despite having garbage collector?

**Explanation:** GC reclaims only objects that are unreachable from GC roots. Memory "leaks" happen when references are unintentionally retained, preventing GC.

**Common causes:**

- Static collections that keep growing (`static Map` or `List`).
- Listeners or callbacks not removed.
- ThreadLocal objects whose values are not cleared — especially when thread pools are used.
- Cache misuse without eviction policy.
- Large object graphs or native memory leaks (via JNI or direct `ByteBuffer` allocations).

**How to detect:** heap dumps, profilers (VisualVM, YourKit, JProfiler), `jmap -dump`, `jstat`, GC logs.

**Mitigation:** use weak/soft references for caches, remove listeners, clear ThreadLocals, size limits and eviction (LRU), use connection pooling and close resources.

---

## 5. How do you analyze heap dumps in Java?

**Explanation:** Heap dumps capture a snapshot of the heap memory and can be analyzed to find memory leaks and object-retention paths.

**Steps:**

1. Generate dump: `jmap -dump:live,format=b,file=heap.hprof <pid>` or via `-XX:+HeapDumpOnOutOfMemoryError`.
2. Open with tools: **Eclipse MAT (Memory Analyzer Tool)**, VisualVM, YourKit, JProfiler.
3. Look for suspects:
   - Dominator tree (which objects retain most memory).
   - Top consumers by instance count and retained size.
   - Paths to GC roots to find why objects are retained.
4. Use MAT queries (OQL) and the Leak Suspects report.

**Key tips:** compare multiple heap dumps over time, filter known large caches, inspect retained sizes rather than shallow sizes.

---

## 6. What is Executor framework in Java?

**Explanation:** The Executor framework (in `java.util.concurrent`) decouples task submission from execution. Core interfaces/classes:

- `Executor` — single `execute(Runnable)` method.
- `ExecutorService` — extends Executor with lifecycle (submit, shutdown, awaitTermination) and futures.
- `ScheduledExecutorService` — supports scheduled tasks.
- `ThreadPoolExecutor` — configurable pool implementation.
- `Executors` — factory utility class (`newFixedThreadPool`, `newCachedThreadPool`, `newSingleThreadExecutor`, `newScheduledThreadPool`).

**Key interview points:** task queuing, rejection policies, thread factories, bounded vs unbounded queues, proper shutdown.

**Sample:**

```java
ExecutorService ex = Executors.newFixedThreadPool(4);
ex.submit(() -> System.out.println("task"));
ex.shutdown();
```

---

## 7. What is a Future in Java?

**Explanation:** `Future<V>` represents result of an asynchronous computation. You can `get()` the result (blocking), `cancel()` it, or check `isDone()`.

**Limitations:** `Future` cannot be composed easily; `get()` blocks and provides no direct callback. `CompletableFuture` (Java 8) addresses these with functional callbacks, chaining, and non-blocking composition.

**Sample:**

```java
Future<Integer> f = ex.submit(() -> {
    Thread.sleep(500);
    return 42;
});
int result = f.get(); // blocks
```

---

## 8. What is a Thread pool & why should we use it?

**Explanation:** A thread pool reuses a fixed number of threads to execute tasks. Benefits:

- **Resource control:** Limits concurrent threads (avoids too many threads/spawning overhead).
- **Reduced latency:** Reuse threads instead of creating/destroying them often.
- **Throughput:** Efficient task scheduling and queueing.

**Considerations:** choose thread count based on CPU-bound vs I/O-bound tasks. For CPU-bound tasks, threads ~ number of cores. For I/O-bound tasks, higher thread count may help.

---

## 9. Difference between `synchronized` and `ReentrantLock`?

**Explanation & differences:**

- `synchronized` is a language construct; `ReentrantLock` is a class with advanced features.
- **Reentrancy:** both are reentrant.
- **TryLock & timed waits:** `ReentrantLock` supports `tryLock()` and `tryLock(timeout, unit)` while `synchronized` cannot.
- **Interruptible lock acquisition:** `lockInterruptibly()` allows thread to be interrupted while waiting — `synchronized` does not.
- **Fairness:** `ReentrantLock` can be created as fair; `synchronized` has no fairness guarantee.
- **Condition objects:** `ReentrantLock` supports multiple `Condition`s for more flexible waiting/notification; `synchronized` uses a single monitor and `wait()/notify()/notifyAll()`.

**Code sample (tryLock):**

```java
ReentrantLock lock = new ReentrantLock();
if(lock.tryLock()){
    try{ /* critical section */ } finally { lock.unlock(); }
}
```

---

## 10. Difference between `volatile` and `synchronized`?

**Explanation:**

- `volatile` ensures **visibility** of writes to variables across threads and prevents reordering of reads/writes to that variable (it provides a lightweight memory barrier). It does **not** provide atomicity for compound operations.
- `synchronized` provides both **mutual exclusion** (only one thread can execute the critical section) and **visibility** (entering/exiting a monitor establishes happens-before relationships).

**Example where volatile is insufficient:**

```java
volatile int counter = 0;
// counter++ is NOT atomic (read-modify-write), so race conditions possible
```

---

## 11. Difference between thread safety and atomicity?

**Explanation:**

- **Atomicity**: an operation is atomic if it appears to happen as a single indivisible step (e.g., `AtomicInteger.incrementAndGet()`).
- **Thread safety**: an API or code is thread-safe if it functions correctly when accessed from multiple threads concurrently (often via synchronization, immutability, or concurrent data structures).

Atomic operations are one building block to achieve thread safety, but thread safety often requires coordination of multiple operations.

---

## 12. Difference between busy-waiting vs blocking vs non-blocking calls?

**Explanation:**

- **Busy-waiting**: repeatedly checking a condition in a loop (wastes CPU cycles). Example: `while(!flag) {}`.
- **Blocking**: thread yields CPU and is suspended by OS/JVM until condition/event occurs (efficient). Example: `Object.wait()`, `BlockingQueue.take()`.
- **Non-blocking**: algorithms that make progress without blocking threads, usually using atomic primitives (`compareAndSet`). Can be lock-free or wait-free.

**Interview note:** prefer blocking primitives for simpler design; non-blocking for high-performance, low-latency systems.

---

## 13. How does `CompletableFuture` work internally?

**Explanation:**
`CompletableFuture` implements `Future` and `CompletionStage` and is backed by a ForkJoinPool (`ForkJoinPool.commonPool()`) for async tasks by default. Key ideas:

- **Non-blocking composition**: `thenApply`, `thenCompose`, `thenAccept` return new stages and schedule callbacks when previous stage completes.
- **Execution**: `supplyAsync` or `runAsync` submit tasks to an `Executor`. Dependent stages get enqueued and executed when the dependency completes; internal data structures manage completion state and dependent actions.

**Important internals:** uses `Unsafe` and CAS operations to update completion state, and a list of dependent actions; uses `ForkJoinTask` for parallelism.

**Example:**

```java
CompletableFuture.supplyAsync(() -> fetch())
    .thenApply(this::transform)
    .thenAccept(System.out::println);
```

---

## 14. What is Producer-Consumer pattern? Implement with `BlockingQueue`.

**Explanation:**
Producer-Consumer decouples producers (create tasks/data) from consumers (process tasks/data) via a thread-safe queue. `BlockingQueue` implementations handle synchronization, blocking producers when full and consumers when empty.

**Code example:**

```java
import java.util.concurrent.*;

BlockingQueue<Integer> q = new ArrayBlockingQueue<>(10);
ExecutorService ex = Executors.newFixedThreadPool(4);

Runnable producer = () -> {
  try {
    for(int i=0;i<100;i++){
      q.put(i);
    }
    q.put(-1); // poison pill
  } catch (InterruptedException e){ Thread.currentThread().interrupt(); }
};

Runnable consumer = () -> {
  try {
    while(true){
      int v = q.take();
      if(v == -1) break;
      // process v
    }
  } catch(InterruptedException e){ Thread.currentThread().interrupt(); }
};

ex.submit(producer);
ex.submit(consumer);
ex.shutdown();
```

---

## 15. How does Java Stream work internally? (parallel vs sequential)

**Explanation:**

- **Sequential streams** operate on a pipeline of intermediate operations (map/filter), which are _lazy_ — nothing runs until a terminal operation (collect, forEach) is invoked. The stream builds a pipeline of `Sink` objects internally and processes elements one-by-one.
- **Parallel streams** split the data source into `Spliterator` chunks and process chunks in parallel (using `ForkJoinPool.commonPool()` by default). Results are combined using `Collector`’s combiner. Performance depends on the data source (ArrayList/primitive arrays are good), stateless operations, and side-effect-free functions.

**Important considerations:**

- Avoid shared mutable state in lambdas.
- Prefer `collect()` with thread-safe collectors or use `Collectors.toList()` which handles parallel accumulation.
- Use `parallelStream()` judiciously — not always faster (small datasets, I/O-bound operations may be slower).

---

## 16. How does `Semaphore` work? Real life examples?

**Explanation:**
`Semaphore` (in `java.util.concurrent`) maintains a set of permits. Threads `acquire()` permits (blocking if none available) and `release()` them. Useful for limiting concurrent access to a resource.

**Real-life examples:**

- Limiting database connections to a max number.
- Controlling access to a pool of printers.

**Code sample:**

```java
Semaphore s = new Semaphore(3); // 3 permits
s.acquire();
try { /* access resource */ } finally { s.release(); }
```

---

## 17. What is a BlockingQueue? Its usage and types?

**Explanation:**
`BlockingQueue` is a concurrent queue supporting operations that wait for the queue to become non-empty when retrieving, and wait for space to become available when storing.

**Common implementations:**

- `ArrayBlockingQueue` — bounded, backed by array, optionally fair.
- `LinkedBlockingQueue` — optionally bounded, linked nodes.
- `PriorityBlockingQueue` — priority ordering.
- `SynchronousQueue` — handoff; no internal capacity.
- `LinkedTransferQueue` — unbounded, advanced transfer features.

**Usage:** thread pools, producer-consumer patterns, work queues.

---

## 18. How does Spring manage Bean lifecycle? What are the hooks?

**Explanation:**
Spring creates and manages beans in the ApplicationContext. Lifecycle phases:

1. Instantiate bean.
2. Populate properties (dependency injection).
3. `BeanNameAware`, `BeanFactoryAware`, `ApplicationContextAware` callbacks.
4. `BeanPostProcessor` (pre-initialization hooks).
5. `@PostConstruct` or `InitializingBean.afterPropertiesSet()`.
6. `BeanPostProcessor` (post-initialization hooks).
7. Bean ready to use.
8. Shutdown: `@PreDestroy` or `DisposableBean.destroy()`.

**Code hooks:**

```java
@Component
public class MyBean implements InitializingBean, DisposableBean {
  @Override public void afterPropertiesSet(){ /* init */ }
  @Override public void destroy(){ /* cleanup */ }

  @PostConstruct public void init(){ }
  @PreDestroy public void cleanup(){ }
}
```

---

## 19. What is the default scope of a Bean in Spring?

**Answer:** `singleton` (one shared instance per Spring `ApplicationContext`). For web apps, `request`, `session`, and `application` scopes exist.

---

## 20. How does optimistic locking work in JPA? `@Version` annotation?

**Explanation:**
Optimistic locking assumes conflicts are rare and checks for conflicts at commit time. JPA supports it via a version column.

**How it works:**

- Entity has `@Version` field (int, long, or timestamp).
- On read, the version is loaded. On update, the `WHERE` clause includes the version. If no row is updated (because version changed), JPA throws `OptimisticLockException`.

**Example:**

```java
@Entity
public class Account {
  @Id private Long id;
  @Version private Long version;
  private BigDecimal balance;
}
```

**Usage:** safe concurrent updates without pessimistic locking.

---

## 21. How do you handle Pagination and Sorting in Spring Data JPA?

**Explanation & sample:**
Spring Data provides `Pageable` and `Sort`.

```java
Pageable p = PageRequest.of(page, size, Sort.by("name").ascending());
Page<User> pageUser = userRepository.findAll(p);
List<User> users = pageUser.getContent();
long total = pageUser.getTotalElements();
```

**Controller params:** accept `page`, `size`, `sort` and map to `Pageable` (Spring can auto-bind with `@PageableDefault`).

---

## 22. What is Connection Pooling?

**Explanation:**
Connection pooling reuses database connections, avoiding the overhead of creating/closing connections per request. Pools (HikariCP, Tomcat JDBC, DBCP) manage sizing, idle timeouts, max lifetime, and validation.

**Key settings:** `maxPoolSize`, `connectionTimeout`, `idleTimeout`, `maxLifetime`, validation query.

---

## 23. What is stateless session? How does JWT help?

**Explanation:**

- **Stateless session**: server does not store session state; each request contains all needed authentication data.
- **JWT (JSON Web Token)**: a compact, signed token that carries claims; server validates signature and derives identity without server-side session storage.

**Security notes:** keep tokens short-lived, use refresh tokens, secure storage on client, and use HTTPS. Consider revocation strategies (token blacklist, short expiry).

---

## 24. How do you implement 2FA?

**Explanation:**
Common 2FA approaches:

- **TOTP (Time-based One-Time Password)**: Google Authenticator style. Server stores secret per user; both client and server compute tokens based on time slices.
- **SMS / Email OTP**: send code to user (less secure due to SIM swap, interception).
- **Hardware tokens / U2F**: secure physical keys.

**Flow (TOTP):**

1. Generate secret, show QR to user.
2. User scans with authenticator app.
3. On login, server asks for TOTP code and verifies.

**Library:** `Google Authenticator` implementations or `com.warrenstrange:googleauth`.

---

## 25. How do you secure micro-services connection?

**Explanation:**

- Use **mTLS** (mutual TLS) for service-to-service authentication.
- Use **OAuth2 / JWT** tokens issued by an identity provider; services validate tokens.
- Network-level controls: service mesh (Istio, Linkerd) can provide mTLS, observability, and traffic routing.
- Secrets management: Vault or cloud KMS for storing credentials.
- Rate limiting, circuit breakers, and TLS encryption in transit.

---

## 26. How to implement OAuth2 authentication in Java Spring Boot?

**Explanation (high-level):**

- Use **Spring Security** and `spring-security-oauth2-client` for Authorization Code flow.
- Configure OAuth2 clients (client-id/secret, callback URL) in `application.yml`.
- Use `@EnableWebSecurity` and configure security filters or rely on auto-configuration.

**Minimal config (application.yml):**

```yaml
spring:
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: <id>
            client-secret: <secret>
            scope: openid,profile,email
```

**Notes:** for resource servers validate JWT tokens with `spring-boot-starter-oauth2-resource-server` and configure issuer/jwk set URI.

---

## 27. What are the core components of Apache Kafka?

**Explanation:**

- **Broker**: Kafka server storing topics and partitions.
- **Topic**: logical stream of messages.
- **Partition**: ordered, immutable sequence of messages within a topic.
- **Producer**: publishes messages to topics.
- **Consumer**: reads messages from topics.
- **Zookeeper / Kafka Controller**: older Kafka versions use ZooKeeper for metadata; newer KRaft mode removes ZooKeeper.
- **Replication**: partitions are replicated across brokers for durability.

---

## 28. What are Consumer Groups? How does rebalancing work?

**Explanation:**

- **Consumer group**: a set of consumers with same group id. Each partition is consumed by at most one consumer within a group — provides horizontal scaling.
- **Rebalancing**: when consumers join or leave, Kafka reassigns partitions among consumers. During rebalance, consumption pauses; consumers commit offsets and resume after assignment.

**Notes:** minimize unnecessary rebalances (sticky assignment, long-lived consumers) and use cooperative rebalancing to reduce disruptions.

---

## 29. How do you ensure message ordering in Kafka?

**Explanation:**
Ordering is guaranteed **within a partition**. To preserve order for a key, produce messages with the same partitioning key so they map to the same partition. Avoid parallel consumers for the same partition if strict ordering is required.

**Tradeoffs:** throughput vs ordering (more partitions => more parallelism but ordering across partitions not guaranteed).

---

## 30. What is a Partition in Kafka? How does it work?

**Explanation:**
A partition is an ordered, immutable sequence of records. Each partition is assigned to a single leader broker (and replicated to followers). Producers write to leaders; consumers read from leaders. Partitions enable Kafka's scalability and parallelism: topic throughput increases with more partitions but increases coordination cost (and consumer ordering considerations).

## **Key points:** offsets are per-partition, replication ensures durability and fault-tolerance, leader election happens during failures.
