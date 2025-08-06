# Design Payment Gateway – Transaction Processing System

## 📋 Problem Statement

Design a secure, scalable payment gateway (like Stripe, Razorpay, PayPal) that allows:

- Merchants to accept payments from customers (cards, wallets, UPI, netbanking)
- Secure transaction processing and settlement
- Fraud detection and risk management
- Refunds, chargebacks, and reconciliation
- Webhooks and API integrations for merchants
- PCI DSS compliance, audit logging, and reporting
- High availability, low latency, and global scaling

## 🎯 Functional Requirements

### Core Features

1. **Merchant Management**: Onboarding, KYC, API keys, dashboard
2. **Payment Processing**: Accept cards, wallets, UPI, netbanking, etc.
3. **Transaction Management**: Authorization, capture, refund, chargeback
4. **Fraud Detection**: Risk scoring, velocity checks, blacklists, 3DS
5. **Settlement & Reconciliation**: Payouts, ledger, reporting
6. **Webhooks & Integrations**: Real-time notifications, API for merchants
7. **Compliance & Security**: PCI DSS, audit logs, encryption

### Non-Functional Requirements

- **Availability**: 99.99% uptime
- **Latency**: < 500ms for payment processing
- **Scalability**: 10K+ TPS, global merchants
- **Consistency**: Strong for transactions, eventual for reporting
- **Security**: PCI DSS, encryption, anti-fraud

## 🏗️ System Architecture

### High-Level Architecture

```
┌──────────────┐   ┌──────────────┐   ┌──────────────┐
│ Merchant App │   │ Customer App │   │ API Client   │
└──────┬───────┘   └──────┬───────┘   └──────┬───────┘
       │                  │                  │
       └────────────┬─────┴─────┬────────────┘
                    │           │
           ┌────────▼───────────▼───────────┐
           │         API Gateway            │
           └────────┬───────────┬───────────┘
                    │           │
      ┌─────────────▼───┐   ┌───▼────────────┐
      │  Auth Service   │   │ Merchant Svc   │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ Payment Svc     │   │ TransactionSvc │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ Fraud Service   │   │ SettlementSvc  │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ Webhook Service │   │ Reporting Svc  │
      └────────┬────────┘   └────┬───────────┘
               │                 │
           ┌───▼─────────────────▼───┐
           │   Storage Layer (DB,    │
           │   S3, Redis, Ledger)    │
           └─────────────────────────┘
```

### Core Components

#### 1. **API Gateway**

- Entry point for all clients
- Handles authentication, rate limiting, routing

#### 2. **Auth Service**

- Merchant/customer authentication (OAuth, JWT)
- API key management

#### 3. **Merchant Service**

- Merchant onboarding, KYC, dashboard
- API key generation, webhooks

#### 4. **Payment Service**

- Accepts payment requests (cards, wallets, UPI, netbanking)
- Integrates with payment networks/acquirers
- Handles 3DS, OTP, SCA flows

#### 5. **Transaction Service**

- Manages transaction lifecycle (auth, capture, refund, chargeback)
- Ensures idempotency, atomicity
- Stores transaction metadata

#### 6. **Fraud Service**

- Risk scoring, velocity checks, blacklists
- ML-based fraud detection
- Manual review, rules engine

#### 7. **Settlement Service**

- Payouts to merchants, ledger management
- Reconciliation with banks/networks
- Handles failed/partial settlements

#### 8. **Webhook Service**

- Real-time notifications to merchants
- Retry, deduplication, signature verification

#### 9. **Reporting Service**

- Transaction, settlement, and compliance reports
- Audit logs, analytics

#### 10. **Storage Layer**

- SQL/NoSQL for metadata
- Ledger DB for transactions
- S3 for documents, logs
- Redis for caching

## 💾 Data Models

### Merchant Schema

```json
{
  "_id": "ObjectId",
  "name": "string",
  "email": "string",
  "kycStatus": "pending|verified|rejected",
  "apiKeys": ["key1", "key2"],
  "webhooks": ["url1", "url2"],
  "createdAt": "date"
}
```

### Transaction Schema

```json
{
  "_id": "ObjectId",
  "merchantId": "ObjectId",
  "customerId": "ObjectId",
  "amount": 1000,
  "currency": "USD",
  "status": "pending|authorized|captured|refunded|failed|chargeback",
  "method": "card|wallet|upi|netbanking",
  "networkRef": "string",
  "createdAt": "date",
  "updatedAt": "date"
}
```

### Payment Method Schema

```json
{
  "_id": "ObjectId",
  "customerId": "ObjectId",
  "type": "card|wallet|upi|netbanking",
  "details": { "cardLast4": "1234", "expiry": "12/25" },
  "tokenized": true,
  "createdAt": "date"
}
```

### Settlement Schema

```json
{
  "_id": "ObjectId",
  "merchantId": "ObjectId",
  "amount": 10000,
  "currency": "USD",
  "status": "pending|completed|failed",
  "transactions": ["txnId1", "txnId2"],
  "payoutRef": "string",
  "createdAt": "date",
  "updatedAt": "date"
}
```

## 🚀 Payment Processing Flow

### Steps

1. Customer initiates payment via merchant app
2. Payment Service validates request, forwards to acquirer/network
3. Handles 3DS/OTP/SCA if required
4. Transaction Service records transaction, updates status
5. Fraud Service scores transaction, may flag for review
6. On success, funds are authorized/captured
7. Settlement Service batches payouts to merchants
8. Webhook Service notifies merchant of status

### Idempotency & Atomicity

- Use idempotency keys for all payment/transaction APIs
- Ensure atomic updates to transaction and ledger

### Security

- PCI DSS compliance (no sensitive data at rest)
- Tokenization of card data
- Encryption at rest and in transit
- Audit logs for all sensitive actions

## 🔒 Security & Fraud Prevention

### Authentication & Authorization

- OAuth2, JWT for user/merchant auth
- API key management, IP whitelisting
- Role-based access for admin/support

### Fraud Detection

- Risk scoring, velocity checks, blacklists
- ML models for anomaly detection
- Manual review, rules engine
- 3DS, OTP, SCA for high-risk transactions

### Data Protection

- Tokenization, encryption
- No sensitive data at rest
- Secure webhooks (signatures, HTTPS)
- Audit logs, compliance reports

## 📊 Performance Optimization

### Processing

- Async processing for non-critical flows (webhooks, reporting)
- Batch settlements, reconciliation
- Use Redis for caching hot data

### Scaling

- Partition by merchant, region
- Auto-scale stateless services
- Use message queues for async flows

### Storage

- Ledger DB for transactions (strong consistency)
- S3 for logs, documents
- Shard DBs by merchant/region

## 🧪 Testing Strategy

### Unit Testing

- Payment, transaction, settlement logic
- Fraud detection rules
- Permission checks

### Integration Testing

- End-to-end payment to settlement
- Webhook and reporting flows
- KYC and onboarding

### Load Testing

- Simulate high TPS
- Settlement and reconciliation under load
- Fraud detection at scale

### Security Testing

- PCI DSS compliance checks
- Tokenization, encryption validation
- Auth bypass, webhook security

## 🚀 Implementation Phases

### Phase 1: MVP (2-3 months)

- Merchant onboarding, KYC, dashboard
- Basic payment processing (cards)
- Transaction management, refunds
- Webhooks, reporting

### Phase 2: Enhanced Features (3-4 months)

- Support wallets, UPI, netbanking
- Fraud detection, risk scoring
- Settlement, reconciliation
- Advanced reporting, analytics

### Phase 3: Advanced Features (2-3 months)

- ML-based fraud detection
- Multi-currency, global support
- API for third-party integrations
- Mobile/web apps

### Phase 4: Enterprise Features (2-3 months)

- Enterprise SSO, audit logs
- Compliance, GDPR
- Custom branding, white-label
- Global CDN optimization

## 🛠️ Technology Stack

### Backend

- **Language**: Go, Java, or Node.js
- **Framework**: Spring Boot, Express.js, Gin
- **Storage**: AWS S3, GCS, Azure Blob
- **Metadata DB**: Cassandra, DynamoDB, MongoDB
- **Ledger DB**: PostgreSQL, CockroachDB
- **Queue**: Kafka, RabbitMQ
- **Search**: Elasticsearch
- **Cache**: Redis

### Frontend

- **Web**: React, Angular, Vue.js
- **Mobile**: React Native, Flutter, native

### Infrastructure

- **Cloud**: AWS, GCP, Azure
- **Load Balancer**: AWS ALB, Nginx
- **CDN**: CloudFront, Cloud CDN
- **Monitoring**: Prometheus, Grafana
- **Logging**: ELK Stack

## 📈 Monitoring & Analytics

### Key Metrics

- **Payment Latency**: Time to process payment
- **Transaction Success Rate**: Success/failure ratio
- **Fraud Detection Rate**: Flagged/blocked transactions
- **Settlement Latency**: Time to settle payouts
- **Error Rates**: Failed payments, webhook errors

### Alerting

- High error rates
- Payment or settlement latency spikes
- Fraud/abuse incidents
- PCI DSS compliance issues

## 🔄 Disaster Recovery

### Backup Strategy

- Regular metadata and ledger DB backups
- Cross-region replication for storage
- Automated restore procedures

### Failover Strategy

- Multi-region deployment
- Automatic failover for storage and DB
- Graceful degradation for non-critical features
- Data consistency checks after failover

---

## 📚 Additional Resources

- [Stripe Engineering Blog](https://stripe.com/blog/engineering)
- [PCI DSS Compliance](https://www.pcisecuritystandards.org/)
- [Fraud Detection Techniques](https://stripe.com/docs/radar)
- [Ledger DB Design](https://www.cockroachlabs.com/docs/v21.1/architecture/ledger.html)
- [Secure Webhooks](https://stripe.com/docs/webhooks)

---

**Note**: This is a comprehensive system design for educational purposes. Real-world implementations may vary based on specific requirements, constraints, and business needs.
