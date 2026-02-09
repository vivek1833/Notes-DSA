# Design Monitoring System – Metrics & Alerting

## 📋 Problem Statement

Design a metrics scraping, alerting (like prometheus) that can handle:
- **100K+ unique time series** (metrics with unique label combinations)
- **5M+ data points per minute** ingestion
- **2+ years of data retention** with configurable policies
- **Sub-second alert evaluation** latency
- **Multi-tenant** support with isolation

## 🎯 Functional Requirements

### Core Features
1. **Metrics Scraping & Ingestion**: Pull metrics from various endpoints (HTTP, JMX, custom exporters) at configurable intervals
2. **Time-Series Storage**: Efficient storage with compression for 2+ years of historical data
3. **PromQL-like Query Language**: Support for complex queries, aggregations, and mathematical operations
4. **Alerting Engine**: Rule-based alerting with flexible thresholds, time windows, and severity levels
5. **Dashboard & Visualization**: Web UI for creating dashboards, graphs, and exploring metrics

### Non-Functional Requirements
- **Availability**: 99.95% uptime, multi-AZ deployment with automatic failover
- **Latency**: <100ms for 95th percentile queries on recent data, <2s for complex queries on historical data
- **Scalability**: Horizontal scaling to handle 10x current load without redesign
- **Consistency**: Eventual consistency for distributed writes, strong consistency for metadata
- **Security**: TLS encryption, OAuth2 authentication, RBAC authorization, data isolation between tenants

## 🏗️ System Architecture

### High-Level Architecture

```
┌─────────────────────────────────────────────────────────────┐
│                    Load Balancer (Nginx)                    │
└──────────────┬────────────────┬──────────────┬──────────────┘
               │                │              │
    ┌──────────▼────┐  ┌────────▼──────┐  ┌───▼────────────┐
    │   Query API   │  │   Write API   │  │  Alert Manager │
    │   (GraphQL)   │  │   (gRPC/HTTP) │  │                │
    └───────┬───────┘  └───────┬───────┘  └───────┬────────┘
            │                  │                   │
    ┌───────▼──────────────────▼───────────────────▼───────┐
    │              Query Router & Distributor              │
    └──────────────┬──────────────────┬────────────────────┘
                   │                  │
         ┌─────────▼──────┐  ┌────────▼─────────┐
         │  Query Engine  │  │   Ingest Node    │
         │                │  │                  │
         └────────┬───────┘  └────────┬─────────┘
                  │                   │
    ┌─────────────▼───────────────────▼─────────────┐
    │           Storage: TSDB Cluster               │
    │  ┌────────────┐ ┌────────────┐ ┌────────────┐│
    │  │   Hot      │ │   Warm     │ │   Cold     ││
    │  │  (2 weeks) │ │ (3 months) │ │ (2 years)  ││
    │  └────────────┘ └────────────┘ └────────────┘│
    └───────────────────────────────────────────────┘
            │                  │                  │
    ┌───────▼──────┐  ┌───────▼──────┐  ┌────────▼──────┐
    │   SSD/NVMe   │  │    SSD       │  │  HDD/S3       │
    └──────────────┘  └──────────────┘  └───────────────┘
```

### Core Components

#### 1. **Ingestion Pipeline**
- **Scraper Service**: Discovers targets and pulls metrics via HTTP/HTTPS
- **Push Gateway**: Accepts metrics from short-lived jobs that can't be scraped
- **Message Queue (Kafka)**: Buffers incoming metrics during spikes
- **Validation & Normalization**: Sanitizes, validates, and transforms metrics

#### 2. **Time-Series Database (TSDB)**
- **Hot Storage**: In-memory + SSD for last 2 weeks of data
- **Warm Storage**: SSD for 3 months of data with compression
- **Cold Storage**: Object storage (S3/GCS) for 2+ years with heavy compression
- **Index**: Inverted index for fast label-based lookups

#### 3. **Query & Alert Engine**
- **Query Parser**: Parses and validates query language
- **Distributed Query Planner**: Splits queries across storage tiers
- **Alert Rule Evaluator**: Continuously evaluates alert rules
- **Notification Service**: Sends alerts via email, Slack, PagerDuty, webhooks

## 💾 Data Models

### Time-Series Data Schema
```javascript
// Metric sample
{
  metric_name: "cpu_usage_percent",
  labels: {
    job: "api-server",
    instance: "10.0.1.1:9100",
    region: "us-east-1",
    team: "platform"
  },
  samples: [  // Compressed format
    [timestamp1, value1],  // delta-encoding + XOR compression
    [timestamp2, value2],
    // ...
  ],
  metadata: {
    metric_type: "gauge",  // gauge, counter, histogram, summary
    help: "CPU usage percentage",
    unit: "percent",
    retention_days: 730
  }
}
```

### Alert Rule Schema
```javascript
{
  rule_id: "alert-001",
  name: "HighCPUUsage",
  expr: "avg(cpu_usage_percent{job=\"api-server\"}) > 80",
  duration: "5m",  // Condition must be true for 5 minutes
  labels: {
    severity: "warning",
    team: "platform",
    service: "api"
  },
  annotations: {
    summary: "High CPU usage on {{ $labels.instance }}",
    description: "CPU usage is {{ $value }}% for 5 minutes",
    runbook: "https://runbook.example.com/high-cpu"
  },
  active_at: "2024-01-15T10:30:00Z",
  silenced_until: null
}
```

### Tenant Schema
```javascript
{
  tenant_id: "tenant-abc123",
  name: "Acme Corp",
  quota: {
    max_series: 100000,
    samples_per_second: 10000,
    retention_days: 730,
    alert_rules: 500
  },
  isolation: {
    storage_prefix: "tenants/abc123/",
    network_vpc: "vpc-123",
    api_key: "sk_live_..."
  }
}
```

## 🚀 Scalability Considerations

### Horizontal Scaling
- **Stateless services**: All API services are stateless and can be scaled horizontally
- **Sharding by tenant/time**: Data sharded by tenant ID and time ranges
- **Read/write separation**: Separate read replicas for query workloads
- **Caching layer**: Redis cache for frequently accessed metadata and query results

### Storage Scaling
- **Hot/Warm/Cold tiers**: Automatic data movement between storage tiers
- **Compression algorithms**: Different compression for different age of data
- **Columnar storage**: Parquet/ORC format for cold storage queries
- **Data lifecycle**: Automatic deletion based on retention policies

### Query Performance
- **Query federation**: Split queries across storage tiers in parallel
- **Result caching**: Cache query results with TTL based on data age
- **Materialized views**: Pre-compute common aggregations
- **Query timeout & limits**: Protect system from expensive queries

## 🔒 Security Considerations
- **Authentication**: OAuth2, JWT tokens, API keys with rotation
- **Authorization**: RBAC with tenant isolation at storage level
- **Encryption**: TLS 1.3 for transit, AES-256 for data at rest
- **Audit logging**: All access and modifications logged
- **Rate limiting**: Per-tenant rate limits to prevent abuse
- **Data masking**: Sensitive labels (IPs, hostnames) can be hashed

## 📊 Performance Optimization

### Ingestion Pipeline
- **Batching**: Aggregate samples before writing (100-1000 samples per batch)
- **Compression**: Snappy/LZ4 compression for network transmission
- **Write-ahead log**: For durability before batch writes
- **Memory pool**: Reuse memory buffers to reduce GC pressure

### Query Engine
- **Query planning**: Cost-based query optimization
- **Vectorized execution**: Process multiple samples simultaneously
- **Predicate pushdown**: Filter data at storage layer when possible
- **Bloom filters**: Quickly exclude irrelevant time series

### Storage Layer
- **LSM-tree structure**: Append-only writes with background compaction
- **Delta encoding**: Store only differences between consecutive samples
- **Gorilla compression**: Specialized compression for time-series data
- **Data skipping indexes**: Store min/max values per data chunk

### Failover Strategy
1. **Active-active API layer**: Multiple API instances behind load balancer
2. **Storage replication**: 3x replication across availability zones
3. **Graceful degradation**: During failures, reduce data resolution but keep system up
4. **Disaster recovery**: Cross-region async replication for critical data
5. **Automated recovery**: Self-healing with automatic node replacement

## 🧪 Testing Strategy
- **Load testing**: Simulate 10x production load to identify bottlenecks
- **Chaos engineering**: Randomly kill services to test resilience
- **Canary deployments**: Roll out changes to small percentage of traffic first
- **Backward compatibility**: Ensure query API remains compatible

## 💰 Cost Optimization
- **Storage tiering**: 90%+ of data in cheap object storage
- **Data lifecycle**: Automatic deletion of expired data
- **Query optimization**: Minimize data scanned for common queries
- **Spot instances**: Use spot instances for stateless workloads

---

## 📚 Additional Resources

- [Prometheus Architecture](https://prometheus.io/docs/introduction/overview/)
- [Facebook Gorilla Paper](https://www.vldb.org/pvldb/vol8/p1816-teller.pdf)
- [Time Series Database Comparison](https://db-engines.com/en/ranking/time+series+dbms)
- [Monitoring Best Practices](https://landing.google.com/sre/sre-book/chapters/monitoring-distributed-systems/)
- [Thanos Architecture](https://thanos.io/tip/thanos/design.md/)
- [OpenMetrics Specification](https://openmetrics.io/)
- [SRE Alerting Principles](https://sre.google/sre-book/monitoring-distributed-systems/)

---

**Estimated System Requirements for 100K series, 5M samples/min:**
- **Ingestion nodes**: 3-5 nodes (16 CPU, 32GB RAM)
- **Query nodes**: 5-7 nodes (32 CPU, 64GB RAM) 
- **Hot storage**: 5TB SSD (2 weeks retention)
- **Cold storage**: 50TB HDD/S3 (2 years retention)
- **Monthly cost**: ~$5K-$10K (cloud infrastructure)