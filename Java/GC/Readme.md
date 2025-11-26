# **JAVA MEMORY MANAGEMENT + GARBAGE COLLECTION (DETAILED NOTES)**

---

# **1. JVM MEMORY MODEL — FULL BREAKDOWN**

Java’s runtime memory model consists of:

```
+-----------------------------+
|      JVM Process Space      |
+-----------------------------+
|  Heap Memory                |
|  Non-Heap (Metaspace)       |
|  Stack (per thread)         |
|  PC Registers               |
|  Native Method Stack        |
+-----------------------------+
```

---

# **2. STACK MEMORY (PER THREAD)**

### **Characteristics**

- Thread-scoped, private to each thread.
- Fast allocation, automatic deallocation.
- LIFO (Last-In-First-Out).
- Contains **stack frames**, each corresponding to an active method call.

### **Stored Inside a Stack Frame**

- Local primitive variables
- Local references (pointers to heap objects)
- Return addresses
- Intermediate computation values

### **Lifecycle**

- New frame created → method call
- Frame destroyed → method returns
- Out-of-scope variables are discarded instantly

### **Failure Case**

- Infinite recursion
- Very deep call stack
  → JVM throws **`StackOverflowError`**

### **ASCII Flow**

```
Thread-1 Stack
 ┌────────────────────────┐
 │ main() Frame           │
 │  ├─ int x = 10         │
 │  ├─ Person p → [heap]  │
 └────────────────────────┘
 ┌────────────────────────┐
 │ test() Frame           │
 │  ├─ String s → [pool]  │
 └────────────────────────┘
```

---

# **3. HEAP MEMORY**

Heap is shared across threads and managed by the Garbage Collector.

```
Heap
 ├── Young Generation
 │     ├── Eden
 │     ├── Survivor 0
 │     └── Survivor 1
 ├── Old Generation (Tenured)
 └── String Pool (inside Heap)
```

---

# **4. YOUNG GENERATION — DETAILED**

## **Components**

1. **Eden**

   - All new objects are allocated here.

2. **Survivor 0 (S0)**

3. **Survivor 1 (S1)**

   - Only one survivor region is used at a time.
   - The other remains empty (flip-flop behavior).

---

## **Minor GC Lifecycle (Young GC)**

### **Step-by-step Execution**

**1. Object creation**

```
o1,o2,o3 → Eden
```

**2. Minor GC triggered (e.g., Eden full)**

- Mark unreachable objects.
- Remove them.
- Move **reachable objects** to the active Survivor space.
- Increment object **age**.
- Swap S0/S1 roles.

### **Flow Diagram**

```
Before Minor GC
Eden: [o1][o2][o3][o4][o5]
S0:   []
S1:   []

GC Marks o2 & o5 as dead

After GC
Eden: []
S0:   [o1(age1)][o3(age1)][o4(age1)]
S1:   []
```

### **Next cycle**

- Surviving objects moved to S1.
- Ages incremented again.
- If age >= threshold → object promoted to **Old Gen**.

---

# **5. OLD GENERATION — DETAILED**

### **Purpose**

- Stores long-lived objects.
- Stores objects that have survived several GC cycles.
- Major GC operates here.

### **Major GC Characteristics**

- Slower
- Expensive
- More references → more traversal time
- Causes longer **Stop-The-World** pauses

### **Major GC Flow Diagram**

```
Old Gen:
[largeObj][objA][objB][objC]

Mark unreachable → sweep → compact
```

---

# **6. METASPACE (NON-HEAP)**

### **Replaced PermGen in Java 8**

### **Stores**

- Class bytecode metadata
- Static variables
- Final constants
- Internal JVM representations (methods, fields, constant pool)

### **Key Properties**

- Uses **native memory** (OS-level)
- Dynamically resizable
- Avoids `OutOfMemoryError: PermGen space`

---

# **7. OBJECT LIFECYCLE — END TO END FLOW**

```
new Object()
    → Eden
        → Survivor 0 (age1)
        → Survivor 1 (age2)
        → Survivor 0 (age3)
            → threshold reached → Old Gen
                → eligible for Major GC
```

---

# **8. TYPES OF REFERENCES — FULL TABLE**

| Reference Type | GC Behaviour                                   | When to Use                  |
| -------------- | ---------------------------------------------- | ---------------------------- |
| **Strong**     | Never collected until reference removed        | Normal object usage          |
| **Weak**       | Collected **as soon as GC runs**               | WeakHashMap, caches          |
| **Soft**       | Collected only under memory pressure           | Memory-sensitive caches      |
| **Phantom**    | Collected after finalization; used for cleanup | Advanced resource management |

---

## **Diagrams**

### **Strong Reference**

```
Stack            Heap
p ─────────────→ [Person]
```

### **Weak Reference**

```
WeakRef p → [Person]
GC runs → [Person] reclaimed
```

### **Soft Reference**

```
GC ignores unless memory low
```

---

# **9. HOW OBJECTS BECOME ELIGIBLE FOR GC**

### **1. Setting reference = null**

```java
obj = null;
```

### **2. Reassigning reference**

```java
obj = new Person();  // old Person eligible
```

### **3. Out of scope variables**

```
Method end → frame destroyed → references gone
```

### **4. Circular references do NOT block GC**

Java GC handles graphs, not ref-counting.

---

# **10. GARBAGE COLLECTION ALGORITHMS (DETAILED)**

---

## **1. Mark & Sweep**

- Mark phase: traverse object graph, mark reachable.
- Sweep phase: delete unmarked objects.

```
[Marked][Unmarked][Marked][Unmarked]
→ Sweep → only Marked remain
```

---

## **2. Mark, Sweep & Compact**

- After sweep → rearrange survivors to eliminate fragmentation.

```
Before:
[ObjA][ ][ObjB][ ][ObjC][ ]

After Compact:
[ObjA][ObjB][ObjC][ ][ ][ ]
```

---

# **11. GARBAGE COLLECTORS — FULL COMPARISON**

---

## **1. Serial GC**

- Single GC thread.
- STW (Stop The World) events.
- Suitable for small heaps or embedded devices.

### Diagram:

```
Application Threads: Paused
GC Thread: Runs alone
```

---

## **2. Parallel GC (Java 8 Default)**

- Multiple GC worker threads.
- Parallelizes young and old generation collections.
- Still Stop-The-World, but faster.

### Diagram:

```
App Threads: Paused
GC Threads: T1,T2,T3,T4 → parallel cleanup
```

---

## **3. CMS (Concurrent Mark Sweep)**

- Reduces pause times.
- Application and GC threads run concurrently.
- No compaction → fragmentation possible.

### Diagram:

```
App Threads ↔ Active
GC Threads ↔ Active
(Concurrent)
```

---

## **4. G1 GC (Java 9+ Default)**

- Region-based GC.
- Predictable low latency.
- Compaction included.
- Designed for large heaps (multi-GB).

### Diagram:

```
Heap split into many regions:
[R1][R2][R3][R4][R5]...

GC picks regions with most garbage → evacuates them
```

---

# **12. STOP THE WORLD (STW) EVENTS**

GC requires a safe-point where:

- No thread is mutating object graph.
- All application threads pause.

Impact:

- Throughput ↓
- Latency ↑
- Can harm SLAs if GC not tuned

---

# **13. `System.gc()` BEHAVIOR**

- Sends a request, not a command.
- JVM **may ignore** it.
- Should not be used in production.

---

# **14. PUTTING IT TOGETHER — COMPLETE RUNTIME FLOW**

### **Flow Diagram**

```
             +-------------------------+
             |   Thread Calls Method   |
             +-----------+-------------+
                         |
                 Create Stack Frame
                         |
             +-----------v-------------+
             |  Allocate Objects in    |
             |        Eden            |
             +-----------+-------------+
                         |
                 Eden becomes full
                         |
             +-----------v-------------+
             |       Minor GC          |
             | Mark → Sweep → Copy     |
             +-----------+-------------+
                         |
             Surviving objects → S0/S1
                         |
            Age reaches threshold?
                   /         \
                 Yes          No
                 |            |
+----------------v--+      Continue in
| Promote to Old Gen |      Young Gen
+---------+----------+
          |
      Old Gen fills
          |
 +--------v--------+
 |    Major GC     |
 +-----------------+
```

---