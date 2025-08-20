# High-Level Design (HLD) Notes

This document provides concise, beginner-friendly notes for all major HLD topics, with Java-based examples where helpful. Use this for quick revision and interview prep!

---

## 1. Foundational Concepts

### What is High-Level Design (HLD)?

- HLD is the process of defining the architecture, components, and interactions of a system at a broad level.
- Focuses on system structure, major modules, and their relationships.

### HLD vs LLD

- **HLD:** Big picture, architecture, technologies, modules, data flow.
- **LLD:** Detailed class diagrams, methods, logic, algorithms.

### Purpose of HLD

- Provides a blueprint for system development.
- Ensures all stakeholders understand the system's structure.

### System Architecture Types

- **Monolithic:** All components in a single codebase (e.g., early Java web apps).
- **Microservices:** Each feature is a separate service (e.g., Spring Boot microservices).
- **Layered:** Presentation, Business Logic, Data Access layers (e.g., Java EE apps).
- **Client-Server:** Clients (UI) interact with backend servers (e.g., REST APIs).
- **Event-Driven:** Components communicate via events (e.g., Kafka-based systems).

### Scalability

- **Vertical Scaling:** Add more power (CPU/RAM) to one server.
- **Horizontal Scaling:** Add more servers to distribute load.
- **Load Balancing:** Distributes traffic (e.g., Nginx, AWS ELB).
- **Elasticity:** Auto-scale resources in cloud (e.g., AWS Auto Scaling).

### System Components

- **Frontend:** User interface (e.g., React, Angular).
- **Backend:** Business logic (e.g., Java Spring Boot).
- **Database:** Data storage (e.g., MySQL, MongoDB).
- **Cache:** Fast data access (e.g., Redis).
- **Third-Party Services:** Payment gateways, email APIs.
- **Message Queues:** Asynchronous processing (e.g., RabbitMQ, Kafka).

---

## 2. Core HLD Concepts

### Database Design for Scalability

- **Relational (SQL):** Structured, ACID (e.g., MySQL).
- **NoSQL:** Flexible, scalable (e.g., MongoDB).
- **Partitioning:** Split data (horizontal = sharding, vertical = by columns).
- **Replication:** Copy data for reliability.
- **Sharding:** Distribute data across servers.
- **Consistency:**
  - **Strong:** Immediate consistency (e.g., SQL).
  - **Eventual:** Updates propagate over time (e.g., DynamoDB).
- **CAP Theorem:** Consistency, Availability, Partition Tolerance (pick 2).

### Caching Strategies

- **In-memory Caching:** Fast access (e.g., Redis, Memcached).
- **Cache-Aside:** App loads from cache, else DB.
- **Write-Through:** Writes go to cache and DB.
- **Write-Behind:** Writes go to cache, DB updated later.
- **Eviction:** LRU, LFU, FIFO.

### Microservices Architecture

- **Loose Coupling:** Services interact via APIs.
- **Service Discovery:** Find services dynamically (e.g., Eureka).
- **Communication:** REST, gRPC, Message Queues.
- **API Gateway:** Single entry point (e.g., Zuul, Spring Cloud Gateway).

### Authentication & Authorization

- **JWT:** JSON Web Tokens for stateless auth.
- **OAuth 2.0:** Delegated access (e.g., Google login).
- **RBAC:** Role-based access.
- **ABAC:** Attribute-based access.
- **Authentication:** Who are you?
- **Authorization:** What can you do?

### High Availability & Fault Tolerance

- **Load Balancers:** Distribute requests.
- **Replication:** Multiple copies for reliability.
- **Redundancy:** Backup components.
- **Graceful Degradation:** Partial service during failures.

---

## 3. Advanced System Design Concepts

### Large Scale Systems

- **Distributed Systems:** Multiple servers, data centers.
- **Scaling:** Horizontal (add servers), Vertical (bigger servers).
- **Distributed Transactions:** Eventual consistency, Sagas.

### Event-Driven Architecture

- **Event Sourcing:** Store state as events.
- **CQRS:** Separate read/write models.
- **Message Brokers:** Kafka, RabbitMQ.
- **Pub/Sub:** Publish/subscribe pattern.

### Low Latency Design

- **Edge Computing:** Process data near users.
- **CDN:** Cache content globally (e.g., Cloudflare).
- **Real-Time Processing:** Stream analytics (e.g., Apache Flink).

### Distributed Systems

- **Geo Distribution:** Multiple regions/data centers.
- **Distributed DBs:** Spanner, Cassandra.
- **Consistent Hashing:** Evenly distribute load.
- **Leader Election:** Paxos, Raft.

### APIs & Services

- **RESTful API:** Resource-based, stateless.
- **GraphQL:** Flexible queries.
- **Rate Limiting:** Prevent abuse.
- **Versioning:** /v1/, /v2/ endpoints.

### Security

- **TLS/SSL:** Encrypt data in transit.
- **SQL Injection/XSS/CSRF:** Common attacks.
- **OAuth/JWT:** Secure authentication.
- **Encryption:** Protect sensitive data.

---

## 4. Deep-Dive Topics

### CI/CD

- **Continuous Integration:** Automated builds/tests.
- **Continuous Delivery/Deployment:** Automated releases.
- **Blue-Green/Canary Deployments:** Safer rollouts.
- **Version Control:** Git.

### Monitoring & Logging

- **Centralized Logging:** ELK Stack, Splunk.
- **Distributed Tracing:** Jaeger, Zipkin.
- **Metrics/Alerting:** Prometheus, Grafana.
- **Health Checks:** Self-healing systems.

### Data Privacy & Compliance

- **GDPR:** Data protection laws.
- **Data Masking/Anonymization:** Hide sensitive info.
- **Retention Policies:** How long to keep data.

### Multi-Tenancy

- **Single Tenant:** One customer per instance.
- **Multi-Tenant:** Multiple customers share resources.
- **Isolation:** Separate data per tenant.

### Mobile Application Design

- **Client-Server:** Mobile app talks to backend.
- **Optimized APIs:** Pagination, compression.
- **Offline Support:** Local storage, sync.

---

## 5. Specialized Use Cases

### Real-Time Applications

- **WebSockets:** Bi-directional communication (e.g., chat apps).
- **Long Polling:** Fallback for real-time.

### Cloud-Native Design

- **Serverless:** AWS Lambda, GCP Functions.
- **Auto-Scaling:** Cloud-managed scaling.
- **Cost Optimization:** Use right-sized resources.

### Machine Learning Systems

- **Model Serving:** Deploy ML models (e.g., TensorFlow Serving).
- **Data Pipelines:** ETL for ML.

### Edge Computing

- **Edge vs Cloud:** Local vs centralized processing.
- **Use Cases:** IoT, autonomous vehicles.

### Video Streaming & Content Delivery

- **Scalable Streaming:** Use CDNs, adaptive bitrate.
- **Example:** YouTube, Netflix.

---

## Java Example: Simple REST API (Spring Boot)

```java
@RestController
public class HelloController {
    @GetMapping("/hello")
    public String hello() {
        return "Hello, World!";
    }
}
```

---
