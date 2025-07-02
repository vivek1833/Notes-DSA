# 2. Core High-Level Design Concepts

## Database Design for Scalability

### Relational (SQL) vs NoSQL Databases

- **Relational (SQL):** Structured tables, ACID properties, strong consistency. E.g., MySQL, PostgreSQL.
- **NoSQL:** Flexible schema, horizontal scaling, eventual consistency. E.g., MongoDB, Cassandra.

**Java Example (JDBC for SQL):**

```java
Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "user", "pass");
Statement stmt = conn.createStatement();
ResultSet rs = stmt.executeQuery("SELECT * FROM users");
```

**Java Example (MongoDB NoSQL):**

```java
MongoClient mongoClient = new MongoClient("localhost", 27017);
MongoDatabase db = mongoClient.getDatabase("mydb");
MongoCollection<Document> users = db.getCollection("users");
users.find().forEach(doc -> System.out.println(doc));
```

### Partitioning

- **Horizontal Partitioning (Sharding):** Split rows across multiple tables/servers.
- **Vertical Partitioning:** Split columns into different tables.

### Replication

- Copy data to multiple servers for reliability and availability.
- **Master-Slave:** One write server, many read replicas.

### Sharding

- Distribute data across multiple servers to handle large datasets and high throughput.

### Data Consistency

- **Strong Consistency:** All users see the same data at the same time.
- **Eventual Consistency:** Data updates propagate over time (common in NoSQL).

### CAP Theorem

- **Consistency, Availability, Partition Tolerance:** You can only guarantee two out of three in a distributed system.

**Mermaid Diagram: CAP Theorem**

```mermaid
graph TD
  A[Consistency] --\
  B[Availability] --\
  C[Partition Tolerance] --\
  D[Distributed System]
  D --> A
  D --> B
  D --> C
```

---

## Caching Strategies in HLD

### In-Memory Caching

- Store frequently accessed data in memory (e.g., Redis, Memcached).

**Java Example (Spring Cache + Redis):**

```java
@Cacheable("products")
public Product getProduct(String id) {
    // Fetch from DB if not in cache
}
```

### Cache-Aside

- Application checks cache first, then DB if not found.

### Write-Through

- Writes go to cache and DB simultaneously.

### Write-Behind

- Writes go to cache, DB updated asynchronously.

### Cache Eviction Strategies

- **LRU (Least Recently Used):** Remove least recently accessed item.
- **LFU (Least Frequently Used):** Remove least accessed item.
- **FIFO (First In, First Out):** Remove oldest item.

---

## Microservices Architecture Design

### Loose Coupling

- Services interact via APIs, not direct calls.

### Service Discovery

- Automatically find and connect to services (e.g., Netflix Eureka).

### Inter-Service Communication

- **REST:** HTTP APIs.
- **gRPC:** High-performance RPC.
- **Message Queues:** Asynchronous (e.g., RabbitMQ, Kafka).

**Java Example (Feign Client for REST):**

```java
@FeignClient(name = "user-service")
public interface UserClient {
    @GetMapping("/users/{id}")
    User getUser(@PathVariable String id);
}
```

### API Gateway

- Single entry point for all clients (e.g., Zuul, Spring Cloud Gateway).

**Java Example (Spring Cloud Gateway):**

```java
@SpringBootApplication
public class ApiGatewayApplication {
    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
```

---

## Authentication and Authorization Design

### JWT (JSON Web Token)

- Stateless authentication, token contains user info.

**Java Example (JWT Generation):**

```java
String jwt = Jwts.builder()
    .setSubject("user1")
    .signWith(SignatureAlgorithm.HS256, secretKey)
    .compact();
```

### OAuth 2.0

- Delegated access (e.g., "Login with Google").

### RBAC (Role-Based Access Control)

- Permissions based on user roles (admin, user, etc.).

### ABAC (Attribute-Based Access Control)

- Permissions based on user attributes (department, location, etc.).

### Authentication vs Authorization

- **Authentication:** Verifying identity.
- **Authorization:** Checking permissions.

---

## Designing for High Availability and Fault Tolerance

### Load Balancers

- Distribute requests across multiple servers (e.g., Nginx, AWS ELB).

### Replication and Redundancy

- Multiple copies of data/components for reliability.

### Graceful Degradation

- System continues to operate in a reduced mode during failures.

**Java Example (Circuit Breaker with Resilience4j):**

```java
@CircuitBreaker(name = "backendA", fallbackMethod = "fallback")
public String callBackendA() {
    // Call external service
}
public String fallback(Throwable t) {
    return "Service unavailable";
}
```

---

**Summary:**

- Core HLD concepts ensure systems are scalable, reliable, and secure.
- Java code samples illustrate practical implementation of these concepts.
