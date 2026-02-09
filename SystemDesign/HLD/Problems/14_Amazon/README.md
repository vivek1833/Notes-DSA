# Design Amazon / Flipkart – E-commerce Platform

## 📋 Problem Statement

Design a comprehensive e-commerce platform similar to Amazon or Flipkart that handles:

- Product catalog management
- User authentication and profiles
- Shopping cart and checkout
- Payment processing
- Order management and tracking
- Inventory management
- Search and recommendations
- Reviews and ratings
- Seller marketplace
- Real-time notifications

## 🎯 Functional Requirements

### Core Features

1. **User Management**: Registration, authentication, profiles, addresses
2. **Product Catalog**: Categories, products, variants, pricing, availability
3. **Search & Discovery**: Search, filters, sorting, recommendations
4. **Shopping Cart**: Add/remove items, quantity management, price calculation
5. **Checkout Process**: Address selection, payment, order confirmation
6. **Order Management**: Order tracking, status updates, returns/refunds
7. **Payment Processing**: Multiple payment methods, security, fraud detection
8. **Inventory Management**: Stock tracking, low stock alerts, auto-replenishment
9. **Seller Platform**: Seller registration, product listing, order fulfillment
10. **Reviews & Ratings**: Product reviews, seller ratings, moderation

### Non-Functional Requirements

- **Availability**: 99.9% uptime
- **Latency**: < 200ms for search, < 500ms for checkout
- **Scalability**: Handle millions of products and users
- **Consistency**: Strong consistency for inventory, eventual for reviews
- **Security**: PCI DSS compliance, fraud prevention

## 🏗️ System Architecture

### High-Level Architecture

```
┌─────────────────┐    ┌─────────────────┐    ┌─────────────────┐
│   Web Client    │    │  Mobile Client  │    │  Admin Panel    │
└─────────┬───────┘    └─────────┬───────┘    └─────────┬───────┘
          │                      │                      │
          └──────────────────────┼──────────────────────┘
                                 │
                    ┌─────────────┴─────────────┐
                    │      CDN / Load Balancer  │
                    └─────────────┬─────────────┘
                                  │
                    ┌─────────────┴─────────────┐
                    │      API Gateway          │
                    └─────────────┬─────────────┘
                                  │
        ┌─────────────────────────┼─────────────────────────┐
        │                         │                         │
┌───────▼────────┐    ┌───────────▼──────────┐    ┌────────▼────────┐
│   User Service │    │  Product Service     │    │  Order Service  │
└───────┬────────┘    └───────────┬──────────┘    └────────┬────────┘
        │                         │                        │
┌───────▼────────┐    ┌───────────▼──────────┐    ┌────────▼────────┐
│  Auth Service  │    │  Search Service      │    │ Payment Service │
└───────┬────────┘    └───────────┬──────────┘    └────────┬────────┘
        │                         │                        │
┌───────▼────────┐    ┌───────────▼──────────┐    ┌────────▼────────┐
│ Notification   │    │  Inventory Service   │    │  Shipping Service│
│   Service      │    └───────────┬──────────┘    └────────┬────────┘
└───────┬────────┘                │                        │
        │              ┌──────────▼──────────┐              │
        │              │   Analytics Service │              │
        │              └──────────┬──────────┘              │
        │                         │                         │
        └─────────────────────────┼─────────────────────────┘
                                  │
                    ┌─────────────▼─────────────┐
                    │      Data Stores          │
                    │  ┌─────────┬─────────────┐│
                    │  │  MySQL  │   Redis     ││
                    │  │         │             ││
                    │  └─────────┴─────────────┘│
                    │  ┌─────────┬─────────────┐│
                    │  │Elastic  │   MongoDB   ││
                    │  │ Search  │             ││
                    │  └─────────┴─────────────┘│
                    └────────────────────────────┘
```

### Core Services

#### 1. **User Service**

- User registration and authentication
- Profile management and preferences
- Address book management
- User session management

#### 2. **Product Service**

- Product catalog management
- Category hierarchy
- Product variants and attributes
- Pricing and availability

#### 3. **Search Service**

- Full-text search across products
- Faceted search and filtering
- Search result ranking
- Search analytics and optimization

#### 4. **Order Service**

- Shopping cart management
- Checkout process
- Order creation and tracking
- Order history and status updates

#### 5. **Payment Service**

- Payment method management
- Payment processing and authorization
- Fraud detection and prevention
- Refund and chargeback handling

#### 6. **Inventory Service**

- Real-time inventory tracking
- Stock level management
- Inventory alerts and notifications
- Auto-replenishment triggers

## 💾 Data Models

### User Schema

```javascript
{
  _id: ObjectId,
  email: String,
  password: String (hashed),
  firstName: String,
  lastName: String,
  phone: String,
  addresses: [{
    type: String, // 'home', 'work', 'other'
    street: String,
    city: String,
    state: String,
    zipCode: String,
    country: String,
    isDefault: Boolean
  }],
  preferences: {
    language: String,
    currency: String,
    notifications: {
      email: Boolean,
      sms: Boolean,
      push: Boolean
    }
  },
  createdAt: Date,
  updatedAt: Date
}
```

### Product Schema

```javascript
{
  _id: ObjectId,
  name: String,
  description: String,
  categoryId: ObjectId,
  brand: String,
  sku: String,
  variants: [{
    variantId: ObjectId,
    attributes: {
      size: String,
      color: String,
      material: String
    },
    price: Number,
    salePrice: Number,
    inventory: Number,
    images: [String]
  }],
  specifications: Map,
  tags: [String],
  sellerId: ObjectId,
  status: String, // 'active', 'inactive', 'out_of_stock'
  rating: Number,
  reviewCount: Number,
  createdAt: Date,
  updatedAt: Date
}
```

### Order Schema

```javascript
{
  _id: ObjectId,
  userId: ObjectId,
  orderNumber: String,
  items: [{
    productId: ObjectId,
    variantId: ObjectId,
    quantity: Number,
    price: Number,
    totalPrice: Number
  }],
  subtotal: Number,
  tax: Number,
  shipping: Number,
  discount: Number,
  total: Number,
  status: String, // 'pending', 'confirmed', 'shipped', 'delivered', 'cancelled'
  shippingAddress: {
    street: String,
    city: String,
    state: String,
    zipCode: String,
    country: String
  },
  billingAddress: {
    street: String,
    city: String,
    state: String,
    zipCode: String,
    country: String
  },
  paymentMethod: {
    type: String, // 'credit_card', 'debit_card', 'paypal'
    last4: String,
    expiryDate: String
  },
  paymentStatus: String, // 'pending', 'paid', 'failed', 'refunded'
  trackingNumber: String,
  estimatedDelivery: Date,
  createdAt: Date,
  updatedAt: Date
}
```

### Inventory Schema

```javascript
{
  _id: ObjectId,
  productId: ObjectId,
  variantId: ObjectId,
  quantity: Number,
  reservedQuantity: Number,
  availableQuantity: Number,
  lowStockThreshold: Number,
  reorderPoint: Number,
  supplierId: ObjectId,
  lastUpdated: Date
}
```

## 🔍 Search Implementation

### Elasticsearch Configuration

```javascript
// Product mapping
{
  "mappings": {
    "properties": {
      "name": {
        "type": "text",
        "analyzer": "standard",
        "boost": 3
      },
      "description": {
        "type": "text",
        "analyzer": "standard",
        "boost": 1
      },
      "category": {
        "type": "keyword"
      },
      "brand": {
        "type": "keyword"
      },
      "price": {
        "type": "float"
      },
      "rating": {
        "type": "float"
      },
      "tags": {
        "type": "keyword"
      },
      "location": {
        "type": "geo_point"
      }
    }
  }
}
```

### Search Query Example

```javascript
{
  "query": {
    "bool": {
      "must": [
        {
          "multi_match": {
            "query": "laptop",
            "fields": ["name^3", "description"],
            "type": "best_fields"
          }
        }
      ],
      "filter": [
        {
          "range": {
            "price": {
              "gte": 500,
              "lte": 2000
            }
          }
        },
        {
          "term": {
            "category": "electronics"
          }
        }
      ]
    }
  },
  "sort": [
    {
      "rating": {
        "order": "desc"
      }
    },
    {
      "price": {
        "order": "asc"
      }
    }
  ],
  "aggs": {
    "categories": {
      "terms": {
        "field": "category"
      }
    },
    "price_ranges": {
      "range": {
        "field": "price",
        "ranges": [
          {"to": 100},
          {"from": 100, "to": 500},
          {"from": 500, "to": 1000},
          {"from": 1000}
        ]
      }
    }
  }
}
```

## 🛒 Shopping Cart Implementation

### Cart Service Architecture

```javascript
class CartService {
  async addToCart(userId, productId, variantId, quantity) {
    // Check inventory availability
    const inventory = await this.inventoryService.checkAvailability(
      productId,
      variantId,
      quantity
    );
    if (!inventory.available) {
      throw new Error("Product not available in requested quantity");
    }

    // Add to cart (Redis)
    const cartKey = `cart:${userId}`;
    const cartItem = {
      productId,
      variantId,
      quantity,
      addedAt: new Date(),
    };

    await this.redis.hset(cartKey, productId, JSON.stringify(cartItem));

    // Reserve inventory
    await this.inventoryService.reserveInventory(
      productId,
      variantId,
      quantity
    );

    return cartItem;
  }

  async getCart(userId) {
    const cartKey = `cart:${userId}`;
    const cartItems = await this.redis.hgetall(cartKey);

    const items = [];
    for (const [productId, itemData] of Object.entries(cartItems)) {
      const item = JSON.parse(itemData);
      const product = await this.productService.getProduct(productId);
      const variant = product.variants.find(
        (v) => v.variantId === item.variantId
      );

      items.push({
        ...item,
        product,
        variant,
        totalPrice: variant.price * item.quantity,
      });
    }

    return items;
  }
}
```

## 💳 Payment Processing

### Payment Flow

```javascript
class PaymentService {
  async processPayment(orderId, paymentMethod, amount) {
    try {
      // 1. Validate payment method
      const validation = await this.validatePaymentMethod(paymentMethod);
      if (!validation.valid) {
        throw new Error(validation.error);
      }

      // 2. Check fraud
      const fraudCheck = await this.fraudDetectionService.check(
        orderId,
        paymentMethod
      );
      if (fraudCheck.risk > 0.8) {
        throw new Error("Payment flagged for fraud review");
      }

      // 3. Process payment
      const paymentResult = await this.paymentGateway.process({
        amount,
        currency: "USD",
        paymentMethod,
        orderId,
        metadata: {
          userId: order.userId,
          items: order.items,
        },
      });

      // 4. Update order status
      await this.orderService.updatePaymentStatus(
        orderId,
        paymentResult.status
      );

      // 5. Send confirmation
      await this.notificationService.sendPaymentConfirmation(orderId);

      return paymentResult;
    } catch (error) {
      // Handle payment failure
      await this.handlePaymentFailure(orderId, error);
      throw error;
    }
  }
}
```

## 📦 Inventory Management

### Real-time Inventory Tracking

```javascript
class InventoryService {
  async updateInventory(productId, variantId, quantity, operation) {
    const lockKey = `inventory_lock:${productId}:${variantId}`;

    // Acquire distributed lock
    const lock = await this.redis.set(lockKey, "locked", "PX", 5000, "NX");
    if (!lock) {
      throw new Error("Inventory update in progress");
    }

    try {
      const inventory = await this.inventoryModel.findOne({
        productId,
        variantId,
      });

      if (operation === "reserve") {
        inventory.reservedQuantity += quantity;
        inventory.availableQuantity -= quantity;
      } else if (operation === "release") {
        inventory.reservedQuantity -= quantity;
        inventory.availableQuantity += quantity;
      } else if (operation === "ship") {
        inventory.quantity -= quantity;
        inventory.reservedQuantity -= quantity;
      }

      // Check low stock threshold
      if (inventory.availableQuantity <= inventory.lowStockThreshold) {
        await this.triggerLowStockAlert(
          productId,
          variantId,
          inventory.availableQuantity
        );
      }

      await inventory.save();

      // Publish inventory update event
      await this.eventBus.publish("inventory.updated", {
        productId,
        variantId,
        availableQuantity: inventory.availableQuantity,
      });
    } finally {
      // Release lock
      await this.redis.del(lockKey);
    }
  }
}
```

## 🚀 Scalability Considerations

### Database Sharding

- **User Data**: Shard by user ID
- **Product Data**: Shard by category or seller
- **Order Data**: Shard by date or user ID
- **Inventory**: Shard by product ID

### Caching Strategy

- **Product Catalog**: Cache in Redis with TTL
- **User Sessions**: Store in Redis
- **Search Results**: Cache popular searches
- **Inventory**: Cache frequently accessed items

### Microservices Communication

- **Synchronous**: REST APIs for immediate responses
- **Asynchronous**: Message queues for background processing
- **Event-Driven**: Event bus for loose coupling

## 🔒 Security Considerations

### Payment Security

- PCI DSS compliance
- Tokenization of payment data
- Encryption in transit and at rest
- Fraud detection algorithms

### User Data Protection

- GDPR compliance
- Data encryption
- Access control and audit logs
- Secure session management

### API Security

- Rate limiting
- Input validation
- JWT authentication
- CORS configuration

## 📊 Performance Optimization

### Database Optimization

- Proper indexing strategy
- Query optimization
- Connection pooling
- Read replicas for read-heavy operations

### Caching Strategy

- **L1 Cache**: Application-level caching
- **L2 Cache**: Distributed caching (Redis)
- **CDN**: Static assets and product images

### Search Optimization

- Elasticsearch tuning
- Search result caching
- Query optimization
- Faceted search optimization

## 🧪 Testing Strategy

### Unit Testing

- Service layer testing
- Data model validation
- Business logic testing

### Integration Testing

- API endpoint testing
- Database integration testing
- Payment gateway testing

### Load Testing

- High concurrent user testing
- Database performance testing
- Search performance testing

### Security Testing

- Penetration testing
- Vulnerability scanning
- Payment security testing

## 🚀 Implementation Phases

### Phase 1: MVP (3-4 months)

- Basic product catalog
- User registration and authentication
- Simple shopping cart
- Basic checkout process
- Payment integration

### Phase 2: Enhanced Features (4-5 months)

- Advanced search and filtering
- Product reviews and ratings
- Order tracking
- Email notifications
- Basic analytics

### Phase 3: Advanced Features (3-4 months)

- Seller marketplace
- Advanced inventory management
- Recommendation engine
- Mobile app
- Advanced analytics

### Phase 4: Enterprise Features (2-3 months)

- Multi-tenant architecture
- Advanced fraud detection
- A/B testing framework
- API for third-party integrations

## 🛠️ Technology Stack

### Backend

- **Language**: Java/Spring Boot or Node.js
- **Database**: MySQL for transactions, MongoDB for catalog
- **Search**: Elasticsearch
- **Cache**: Redis
- **Message Queue**: Apache Kafka or RabbitMQ

### Frontend

- **Framework**: React or Angular
- **State Management**: Redux or NgRx
- **UI Library**: Material-UI or Ant Design

### Infrastructure

- **Cloud**: AWS or GCP
- **Load Balancer**: AWS ALB or Nginx
- **CDN**: CloudFront or Cloud CDN
- **Monitoring**: Prometheus + Grafana
- **Logging**: ELK Stack

## 📈 Monitoring & Analytics

### Key Metrics

- **Conversion Rate**: Cart to purchase conversion
- **Average Order Value**: Revenue per order
- **Customer Lifetime Value**: Long-term customer value
- **Inventory Turnover**: How quickly inventory sells
- **Search Performance**: Search latency and accuracy

### Business Metrics

- **Revenue**: Daily, weekly, monthly revenue
- **Orders**: Order volume and growth
- **Products**: Product performance and inventory
- **Users**: User acquisition and retention

## 🔄 Disaster Recovery

### Backup Strategy

- Daily database backups
- Real-time replication
- Point-in-time recovery
- Cross-region backup

### Failover Strategy

- Multi-region deployment
- Automatic failover
- Data consistency checks
- Graceful degradation

---

## 📚 Additional Resources

- [Amazon Architecture](https://highscalability.com/amazon-architecture)
- [E-commerce Best Practices](https://www.shopify.com/enterprise/ecommerce-architecture)
- [Payment Processing Security](https://www.pcisecuritystandards.org/)
- [Inventory Management](https://www.investopedia.com/terms/i/inventory-management.asp)

---

**Note**: This is a comprehensive system design for educational purposes. Real-world implementations may vary based on specific requirements, constraints, and business needs.
