# Java Interview Questions - Level 1 (Experienced SDE Explanations)

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
- **Encapsulation:** Hides data using access modifiers (focus on *how data is accessed*).  
- **Abstraction:** Hides implementation details using abstract classes/interfaces (focus on *what operations are exposed*).  

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

## 34-35. User-defined exceptions
- Custom exception classes extending Exception/RuntimeException.  

```java
class MyException extends Exception { 
    public MyException(String msg){ 
        super(msg); 
    } 
}
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
