# 1️⃣ Can two consumers in the same group read the same partition?

**No.**

Within a **single consumer group**, each partition is assigned to **only one consumer at a time**.

### Why?

Kafka guarantees:

- **Parallelism**
- **Ordering per partition**

If two consumers read the same partition:

- Ordering would break
- Offset management would conflict

### Important Edge Case

During **rebalance**, partitions are temporarily unassigned and reassigned. But still - at steady state - **one partition → one consumer (per group)**.

Across **different consumer groups**, yes - multiple consumers can read the same partition independently.

---

# 2️⃣ Can ordering be guaranteed globally?

**No. Only within a single partition.**

Kafka guarantees ordering:

```
Within one partition only
```

If you need global ordering:

- Use **single partition topic**
- Tradeoff: lose parallelism & scalability

### Production Pattern

Use a key (e.g., `userId`) to ensure:

```
Same key → same partition → ordered processing per entity
```

Global ordering across partitions is not supported.

---

# 3️⃣ What happens if producer sends data but broker crashes before ACK?

It depends on `acks` setting.

### Case 1: `acks=0`

Producer doesn’t wait.
→ Message may be lost.
→ No retry.

### Case 2: `acks=1`

Leader writes message.
If leader crashes before replication:
→ Message is lost if follower didn’t replicate.

### Case 3: `acks=all`

Leader waits for all ISR replicas.
If leader crashes after replication:
→ Safe (new leader has data).

If leader crashes **before ack is sent**:

- Producer retries.
- Duplicate possible unless idempotent producer is enabled.

### Best Practice

Enable:

```properties
acks=all
enable.idempotence=true
```

---

# 4️⃣ Can a topic exist without partitions?

**No.**

Every topic has **at least one partition**.

Even if you don't specify, Kafka creates:

```
default partitions = 1
```

Partition is fundamental storage unit.

---

# 5️⃣ Is Kafka pull or push based?

Kafka is **pull-based**.

Consumers request data:

```java
consumer.poll(Duration.ofMillis(100));
```

### Why pull?

- Better backpressure control
- Consumer controls pace
- Avoids overwhelming slow consumers

---

# 6️⃣ Can Kafka work without ZooKeeper?

Yes - in modern Kafka.

Old versions:

```
Kafka + ZooKeeper
```

New versions (Kafka 3.x+):

```
KRaft mode (Kafka Raft metadata mode)
```

ZooKeeper is being removed.

### Interview Tip

Explain:

- ZooKeeper handled metadata, leader election.
- KRaft integrates that internally using Raft consensus.

---

# 7️⃣ Can replication factor be greater than number of brokers?

**No.**

Replication factor ≤ number of brokers.

Example:

```
3 brokers → max replication factor = 3
```

If you try:

```
replication.factor=5 with 3 brokers
```

→ Topic creation fails.

---

# 8️⃣ What happens if replication factor = 1?

Only one copy exists.

If broker crashes:

- Partition unavailable
- Data loss possible

No fault tolerance.

Used only for:

- Development
- Non-critical workloads

Never recommended for production.

---

# 9️⃣ How would you prevent duplicate processing?

Duplicates can occur due to:

- Producer retries
- Consumer reprocessing after crash

### Strategy 1: Idempotent Producer

```properties
enable.idempotence=true
acks=all
```

Prevents duplicate writes.

---

### Strategy 2: Consumer Idempotency (Most Important)

Use business key:

```java
if (!db.exists(eventId)) {
    process();
    db.save(eventId);
}
```

Store processed IDs in DB/cache.

---

### Strategy 3: Kafka Transactions (Exactly Once)

```java
producer.initTransactions();
producer.beginTransaction();
producer.send(record);
producer.commitTransaction();
```

Used with:

```properties
enable.idempotence=true
transactional.id=tx-1
```

---

# 🔟 How to handle out-of-order events?

Kafka guarantees ordering per partition.

Out-of-order happens when:

- Multiple partitions
- Multiple producers
- Retries

### Solution 1: Key-based partitioning

Ensure:

```
Same entity → same partition
```

---

### Solution 2: Versioning / Sequence Number

Add sequence number:

```json
{
  "orderId": 101,
  "version": 3
}
```

Consumer logic:

```java
if (incoming.version > current.version) {
    update();
}
```

---

### Solution 3: Event Time + Windowing (Streams)

Use Kafka Streams:

- Grace period
- Suppression

---

# 1️⃣1️⃣ How to implement retry logic?

### Pattern 1: Simple Retry in Consumer

```java
try {
    process(record);
} catch (Exception e) {
    retry();
}
```

Risk: blocking thread.

---

### Pattern 2: Retry Topic Pattern (Best Practice)

Flow:

```
main-topic → retry-topic → dlq-topic
```

If failure:

- Publish to retry-topic with delay
- After max attempts → DLQ

---

### Pattern 3: Dead Letter Queue (DLQ)

```java
producer.send(new ProducerRecord<>("dlq-topic", record.value()));
```

Used for poison messages.

---

# 1️⃣2️⃣ Delivery Semantics in Kafka

Kafka supports:

---

## ✅ At-most-once

Message may be lost.
No duplicates.

How?

- Commit offset before processing

```java
consumer.commitSync();
process(record);
```

Risk: if crash after commit → message skipped.

---

## ✅ At-least-once (Default)

No message lost.
Duplicates possible.

How?

- Process first
- Commit after

```java
process(record);
consumer.commitSync();
```

If crash before commit:
→ Message reprocessed.

Most common in production.

---

## ✅ Exactly-once

No loss.
No duplicates.

Requires:

- Idempotent producer
- Transactions
- Proper consumer isolation

```properties
enable.idempotence=true
transactional.id=tx-1
isolation.level=read_committed
```

Used in:

- Financial systems
- Payment processing

---
