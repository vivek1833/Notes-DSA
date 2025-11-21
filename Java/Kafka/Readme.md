### Apache Kafka is an event streaming platform. What does that mean?
- Kafka combines three key capabilities so you can implement your use cases for event streaming end-to-end with a single battle-tested solution:
    1. To publish (write) and subscribe to (read) streams of events, including continuous import/export of your data from other systems.
    2. To store streams of events durably and reliably for as long as you want.
    3. To process streams of events as they occur or retrospectively.

### Core Fundamentals (The "Must-Know" Basics)

- `Producer`: Publishes (writes) messages to topics.
- `Consumer`: Subscribes to (reads) messages from topics.
- `Topic`: A categorized stream of messages (e.g., user-click-events).
- `Partition`: Topics are split into partitions for parallelism and scalability. Each partition is an ordered, immutable sequence of records.
- `Broker`: A single Kafka server. A cluster is composed of multiple brokers.
- `Cluster`: A group of brokers working together.
- `ZooKeeper`: The "brain" for metadata management, leader election, and cluster state. (Crucial Note: Be prepared to discuss its upcoming removal in favor of the KRaft protocol).

### Key Concepts:

- `Offset`: A unique, sequential ID for a message within a partition. Consumers track their position.
- `Consumer Group`: A set of consumers that cooperate to consume a topic. Each partition is consumed by only one consumer in the group.
- `Replication & In-Sync Replicas (ISR)`: How Kafka provides durability and high availability. Understand leader and follower replicas.
- `Message/Record`: The unit of data. Composed of Key, Value, Headers, and Timestamp.
- `Log Compaction`: A retention policy that ensures the latest value for each key is retained. Critical for table-like topics.
- `Data flow`: Producer → Broker (topic-partition) → Consumer.
- `Message ordering`: Only guaranteed within a single partition.
- `Replication`: Ensures fault tolerance (each partition has a leader + followers).
- `Leader election`: Each partition has one leader that handles all reads/writes.
- `Acknowledgements`:
    acks=0 → no ack
    acks=1 → leader ack
    acks=all → leader + all in-sync replicas ack (strongest durability)

### How does Kafka work in a nutshell?
- Kafka is a distributed system consisting of servers and clients that communicate via a high-performance TCP network protocol

    1. `Servers:` Kafka is run as a cluster of one or more servers that can span multiple datacenters or cloud regions. Some of these servers form the storage layer, called the `brokers`. Other servers run Kafka Connect to continuously import and export data as event streams to integrate Kafka with your existing systems such as relational databases as well as other Kafka `clusters`. To let you implement mission-critical use cases, a Kafka cluster is highly scalable and fault-tolerant: if any of its servers fails, the other servers will take over their work to ensure continuous operations without any data loss.

    2. `Clients:` They allow you to write distributed applications and microservices that read, write, and process streams of events in parallel, at scale, and in a fault-tolerant manner even in the case of network problems or machine failures. Clients are either `producers` or `consumers` of events.

### Main Concepts and Terminology
`Producers` are those client applications that publish (write) events to Kafka, and consumers are those that subscribe to (read and process) these events. In Kafka, producers and consumers are fully decoupled and agnostic of each other, which is a key design element to achieve the high scalability that Kafka is known for. For example, producers never need to wait for consumers. Kafka provides various guarantees such as the ability to process events exactly-once.

`Events` are organized and durably stored in topics. Very simplified, a topic is similar to a folder in a filesystem, and the events are the files in that folder. An example topic name could be "payments". Topics in Kafka are always multi-producer and multi-subscriber: a topic can have zero, one, or many producers that write events to it, as well as zero, one, or many consumers that subscribe to these events. Events in a topic can be read as often as needed-unlike traditional messaging systems, events are not deleted after consumption. Instead, you define for how long Kafka should retain your events through a per-topic configuration setting, after which old events will be discarded. Kafka's performance is effectively constant with respect to data size, so storing data for a long time is perfectly fine.

`Topics` are partitioned, meaning a topic is spread over a number of "buckets" located on different Kafka brokers. This distributed placement of your data is very important for scalability because it allows client applications to both read and write the data from/to many brokers at the same time. When a new event is published to a topic, it is actually appended to one of the topic's partitions. Events with the same event key (e.g., a customer or vehicle ID) are written to the same partition, and Kafka guarantees that any consumer of a given topic-partition will always read that partition's events in exactly the same order as they were written.

![Working](Storage.png)
￼
*Figure: This example topic has four partitions P1–P4. Two different producer clients are publishing, independently from each other, new events to the topic by writing events over the network to the topic's partitions. Events with the same key (denoted by their color in the figure) are written to the same partition. Note that both producers can write to the same partition if appropriate.*

To make your data fault-tolerant and highly-available, every topic can be replicated, even across geo-regions or datacenters, so that there are always multiple brokers that have a copy of the data just in case things go wrong, you want to do maintenance on the brokers, and so on. A common production setting is a replication factor of 3, i.e., there will always be three copies of your data. This replication is performed at the level of topic-partitions.

![Kafka infra](Kafka_Cluster.png)
