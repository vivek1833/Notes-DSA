# Design Twitter – Social Media Platform

## 📋 Problem Statement

Design a large-scale social media platform like Twitter that allows users to:

- Post short messages (tweets) with optional media
- Follow/unfollow other users
- View a timeline of tweets from followed users
- Like, retweet, and reply to tweets
- Search tweets, users, hashtags, and trends
- Receive notifications for relevant activities
- Support trending topics and hashtags
- Ensure high availability, scalability, and security

## 🎯 Functional Requirements

### Core Features

1. **User Management**: Registration, authentication, profiles, follow/unfollow
2. **Tweet Management**: Post, delete, like, retweet, reply
3. **Timeline Generation**: Personalized timeline of tweets from followed users
4. **Search & Trends**: Search tweets, users, hashtags, trending topics
5. **Notifications**: Real-time and batch notifications for likes, retweets, replies, follows
6. **Media Support**: Attach images, videos, GIFs to tweets
7. **Direct Messaging**: (Optional) Private chat between users

### Non-Functional Requirements

- **Availability**: 99.9% uptime
- **Latency**: < 200ms for timeline load, < 1s for posting
- **Scalability**: 100M+ users, 1B+ tweets, global access
- **Consistency**: Eventual for timeline, strong for tweet/like/retweet
- **Security**: Data privacy, access control, abuse prevention

## 🏗️ System Architecture

### High-Level Architecture

```
┌──────────────┐   ┌──────────────┐   ┌──────────────┐
│ Web Client   │   │ Mobile Client│   │ API Client   │
└──────┬───────┘   └──────┬───────┘   └──────┬───────┘
       │                  │                  │
       └────────────┬─────┴─────┬────────────┘
                    │           │
           ┌────────▼───────────▼───────────┐
           │         API Gateway            │
           └────────┬───────────┬───────────┘
                    │           │
      ┌─────────────▼───┐   ┌───▼────────────┐
      │  Auth Service   │   │  User Service  │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ Tweet Service   │   │ Timeline Svc   │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ Social Graph    │   │ Search Service │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ Like/RetweetSvc │   │ Trend Service  │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ NotificationSvc │   │ Media Service  │
      └────────┬────────┘   └────┬───────────┘
               │                 │
           ┌───▼─────────────────▼───┐
           │   Storage Layer (S3,    │
           │   Blob Store, CDN)      │
           └─────────────────────────┘
```

### Core Components

#### 1. **API Gateway**

- Entry point for all clients
- Handles authentication, rate limiting, routing

#### 2. **Auth Service**

- User authentication (OAuth, JWT)
- Session management

#### 3. **User Service**

- User profiles, settings, follow/unfollow
- Social graph management

#### 4. **Tweet Service**

- Handles tweet creation, deletion, media attachment
- Stores tweets in distributed DB
- Manages tweet metadata (likes, retweets, replies)

#### 5. **Timeline Service**

- Generates personalized timeline (pull, push, or hybrid)
- Fan-out on write (push) or read (pull) strategies
- Caches hot timelines in Redis

#### 6. **Social Graph Service**

- Manages follower/following relationships
- Efficient graph queries for recommendations

#### 7. **Like/Retweet Service**

- Stores likes, retweets, replies
- Ensures strong consistency for engagement
- Moderation and spam detection

#### 8. **Search Service**

- Indexes tweets, users, hashtags
- Full-text search, autocomplete, trending

#### 9. **Trend Service**

- Calculates trending topics and hashtags
- Real-time analytics and aggregation

#### 10. **Notification Service**

- Real-time and batch notifications
- Push, email, in-app notifications

#### 11. **Media Service**

- Handles image/video upload, processing
- Stores media in object storage (S3, CDN)
- Generates thumbnails, previews

#### 12. **Storage Layer**

- Object storage for media (S3, GCS, Azure Blob)
- CDN for global delivery
- SQL/NoSQL for metadata

## 💾 Data Models

### User Schema

```json
{
  "_id": "ObjectId",
  "username": "string",
  "email": "string",
  "password": "hashed",
  "profilePic": "url",
  "bio": "string",
  "followers": ["userId1", "userId2"],
  "following": ["userId1", "userId2"],
  "createdAt": "date"
}
```

### Tweet Schema

```json
{
  "_id": "ObjectId",
  "userId": "ObjectId",
  "text": "string",
  "mediaUrl": "string|null",
  "createdAt": "date",
  "likes": 123,
  "retweets": 45,
  "replies": 10,
  "hashtags": ["tag1", "tag2"],
  "mentions": ["userId1", "userId2"]
}
```

### Timeline Entry Schema

```json
{
  "_id": "ObjectId",
  "userId": "ObjectId",
  "tweetId": "ObjectId",
  "createdAt": "date"
}
```

### Like/Retweet Schema

```json
{
  "_id": "ObjectId",
  "tweetId": "ObjectId",
  "userId": "ObjectId",
  "type": "like|retweet|reply",
  "createdAt": "date"
}
```

## 🚀 Timeline Generation

### Strategies

- **Pull Model**: Generate timeline on read (query tweets from followed users)
- **Push Model**: Fan-out tweets to followers’ timelines on write
- **Hybrid**: Push for high-follower users, pull for others

### Caching

- Hot timelines cached in Redis
- Precompute timelines for active users
- Use background jobs for fan-out

### Pagination

- Use cursor-based pagination (createdAt, tweetId)
- Infinite scroll support

## 🔄 Tweet Posting & Delivery

### Posting Flow

1. Client posts tweet to Tweet Service
2. Tweet Service stores tweet, updates metadata
3. Timeline Service updates timelines (push/pull)
4. Notification Service notifies followers
5. Media Service processes and stores media (if any)

### Delivery

- Use CDN for fast, global delivery of media
- Lazy load images/videos in timeline

## 🔒 Security & Privacy

### Authentication & Authorization

- OAuth2, JWT for user auth
- Access control for private accounts, DMs
- Rate limiting, abuse prevention

### Data Protection

- Encryption at rest and in transit
- Secure media URLs (signed URLs, expiry)
- Moderation for tweets, replies

### Privacy

- User-controlled privacy settings
- Block/report users
- GDPR compliance

## 📊 Performance Optimization

### Tweets

- Use CDN for static content
- Compression and adaptive streaming for media
- Parallel uploads/downloads

### Timeline

- Precompute timelines for active users
- Use Redis for hot timeline caching
- Efficient fan-out with background workers

### Search

- Elasticsearch for indexing
- Autocomplete and trending queries

## 🧪 Testing Strategy

### Unit Testing

- Tweet creation/deletion logic
- Timeline generation
- Like/retweet operations

### Integration Testing

- End-to-end tweet to timeline
- Search and trend flows
- Notification and media flows

### Load Testing

- Simulate high tweet/view traffic
- Timeline latency under heavy load
- CDN and storage stress tests

### Security Testing

- Auth bypass attempts
- Media URL access control
- Spam/abuse detection

## 🚀 Implementation Phases

### Phase 1: MVP (2-3 months)

- User registration, authentication, profile
- Tweet posting, timeline, likes, retweets
- Follow/unfollow, notifications

### Phase 2: Enhanced Features (3-4 months)

- Search and trends
- Media attachments
- Explore/recommendation engine
- Media processing pipeline

### Phase 3: Advanced Features (2-3 months)

- Timeline optimization (push/pull, caching)
- Group messaging, analytics
- Advanced moderation, analytics
- Mobile and web apps

### Phase 4: Enterprise Features (2-3 months)

- SSO integration, business accounts
- Advanced analytics and reporting
- API for third-party integrations
- Global CDN optimization

## 🛠️ Technology Stack

### Backend

- **Language**: Go, Java, or Node.js
- **Framework**: Spring Boot, Express.js, Gin
- **Storage**: AWS S3, GCS, Azure Blob
- **Metadata DB**: Cassandra, DynamoDB, MongoDB
- **Timeline Cache**: Redis
- **Search**: Elasticsearch
- **Queue**: Kafka, SQS

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

- **Timeline Latency**: Time to load timeline
- **Tweet Post Latency**: Time to post tweet
- **Engagement**: Likes, retweets, replies per user
- **Storage Utilization**: Per user, per region
- **Error Rates**: Failed tweets, timeline errors

### Alerting

- High error rates
- Timeline or tweet latency spikes
- Storage/CDN capacity thresholds
- Security/abuse incidents

## 🔄 Disaster Recovery

### Backup Strategy

- Regular metadata DB backups
- Cross-region replication for storage
- Automated restore procedures

### Failover Strategy

- Multi-region deployment
- Automatic failover for storage and DB
- Graceful degradation for non-critical features
- Data consistency checks after failover

---

## 📚 Additional Resources

- [Twitter Engineering Blog](https://blog.twitter.com/engineering/en_us)
- [How Twitter Scales](https://blog.twitter.com/engineering/en_us/topics/infrastructure/2017/the-infrastructure-behind-twitter-scale.html)
- [Twitter Timeline Architecture](https://blog.twitter.com/engineering/en_us/topics/infrastructure/2017/the-infrastructure-behind-twitter-scale.html)
- [CDN for Media Delivery](https://aws.amazon.com/cloudfront/)
- [Elasticsearch for Search](https://www.elastic.co/)

---

**Note**: This is a comprehensive system design for educational purposes. Real-world implementations may vary based on specific requirements, constraints, and business needs.
