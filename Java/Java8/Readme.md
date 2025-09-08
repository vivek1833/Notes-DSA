# 🚀 Java 8 Streams & Functional Programming

This repository contains examples to learn **Java 8 Streams API** and related functional programming features.

---

## ✨ Key Java 8 Features Covered

- **Functional Interfaces**: `Predicate`, `Function`, `Consumer`, `Supplier`
- **Lambda Expressions**: `x -> x * 2`
- **Method References**: `String::toUpperCase`
- **Streams API**:
  - Stream creation
  - Intermediate operations
  - Terminal operations
  - Parallel streams

---

## 📌 Functional Interfaces

Java 8 introduced commonly used functional interfaces in `java.util.function`.

| Interface         | Method              | Example               | Use Case         | Definition                                   |
| ----------------- | ------------------- | --------------------- | ---------------- | -------------------------------------------- |
| **Predicate<T>**  | `boolean test(T t)` | `x -> x > 10`         | Filtering        | Boolean-returning function with one argument |
| **Function<T,R>** | `R apply(T t)`      | `x -> x * x`          | Mapping          | Value-returning function with one argument   |
| **Consumer<T>**   | `void accept(T t)`  | `System.out::println` | Processing       | Void-returning function with one argument    |
| **Supplier<T>**   | `T get()`           | `() -> Math.random()` | Value generation | Value-returning function without arguments   |

🔗 [FunctionalInterfacesDemo.java](/Java/Java8%20features/Java8Demo.java)

---

## 📌 Streams API

A **Stream** is a sequence of elements supporting **functional-style operations**.  
It allows concise, readable, and parallelizable data processing.

### 1. Stream Basics

- **Source**: Collection, Array, Values
- **Intermediate Operations**: `map()`, `filter()`, `sorted()`, `flatMap()`...
- **Terminal Operations**: `collect()`, `reduce()`, `count()`, `forEach()`

🔗 [StreamDemo.java](/Java/Java8%20features/JavaStream.java)

---

### 2. Intermediate Operations

Operations that transform a stream into another stream.  
They are **lazy** and only execute when a terminal operation is called.

- `filter()` → keep elements by condition
- `map()` → transform values
- `sorted()` → sort elements
- `distinct()` → remove duplicates
- `limit()` / `skip()` → slice streams
- `peek()` → debug elements in pipeline
- `flatMap()` → flatten nested streams

🔗 [IntermediateOps.java](/Java/Java8%20features/IntermediateOps.java)

---

### 3. Terminal Operations

Operations that **consume a stream** and produce a result.

- `collect()` → convert to list, set, map, string
- `forEach()` / `forEachOrdered()` → iterate over elements
- `reduce()` → combine into single value
- `count()` → count elements
- `anyMatch()` / `allMatch()` / `noneMatch()` → check conditions
- `findFirst()` / `findAny()` → retrieve elements
- `min()` / `max()` → get extreme values
- `summaryStatistics()` → get count, sum, min, max, average

🔗 [TerminalOps.java](/Java/Java8%20features/TerminalOps.java)

---

### 4. Parallel Streams

- Streams can run **in parallel** for performance.
- Best for **CPU-intensive** operations on **large datasets**.
- **Avoid side effects** (shared mutable state).
- Use `forEachOrdered()` if order matters.

🔗 [ParallelStream.java](/Java/Java8%20features/ParallelStream.java)

---

## ⚡ Quick Examples

### Predicate

```java
Predicate<Integer> isEven = x -> x % 2 == 0;
System.out.println(isEven.test(4)); // true
```

### Function

```java
Function<String, Integer> length = String::length;
System.out.println(length.apply("Hello")); // 5
```

### Consumer

```java
Consumer<String> printer = System.out::println;
printer.accept("Hello Consumer");
```

### Supplier

```java
Supplier<Double> randomSupplier = Math::random;
System.out.println(randomSupplier.get());
```

---

## ✅ Best Practices

- Prefer **pure functions** (no side effects).
- Use **parallel streams** only for **independent, heavy computations**.
- Remember: **Streams cannot be reused** after a terminal operation.
- Use **method references** where possible (`list.forEach(System.out::println)`).

---

## 📚 References

- [Official Java Stream Docs](https://docs.oracle.com/javase/8/docs/api/java/util/stream/Stream.html)
- [Java 8 Functional Interfaces](https://docs.oracle.com/javase/8/docs/api/java/util/function/package-summary.html)

---
