# Design Instagram – Media Sharing Platform

## 📋 Problem Statement

Design a large-scale media sharing platform like Instagram that allows users to:

- Upload, view, and share photos and videos
- Follow other users and view their posts in a feed
- Like, comment, and save posts
- Create and view ephemeral stories
- Discover content via search, hashtags, and recommendations
- Receive notifications for relevant activities
- Ensure high availability, scalability, and security

## 🎯 Functional Requirements

### Core Features

1. **User Management**: Registration, authentication, profiles, follow/unfollow
2. **Media Upload & Storage**: Upload, store, and serve images/videos
3. **Feed Generation**: Personalized timeline of posts from followed users
4. **Stories**: Ephemeral media (24-hour expiry), story highlights
5. **Likes, Comments, Saves**: Interact with posts, view engagement
6. **Search & Discovery**: Search users, hashtags, explore trending content
7. **Notifications**: Real-time and batch notifications for likes, comments, follows, etc.
8. **Direct Messaging**: (Optional) Private chat between users

### Non-Functional Requirements

- **Availability**: 99.9% uptime
- **Latency**: < 200ms for feed load, < 1s for uploads
- **Scalability**: 100M+ users, 1B+ posts, global access
- **Consistency**: Eventual for feed, strong for likes/comments
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
      │ Media Service   │   │ Feed Service   │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ Story Service   │   │ Social Graph   │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ Like/CommentSvc │   │ Search Service │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ NotificationSvc │   │ Recommendation │
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

#### 4. **Media Service**

- Handles image/video upload, processing (resize, compress, transcode)
- Stores media in distributed object storage (S3, CDN)
- Generates thumbnails, previews

#### 5. **Feed Service**

- Generates personalized feed (pull, push, or hybrid)
- Fan-out on write (push) or read (pull) strategies
- Caches hot feeds in Redis

#### 6. **Story Service**

- Manages ephemeral stories (24-hour expiry)
- Handles highlights, story views

#### 7. **Like/Comment Service**

- Stores likes, comments, saves
- Ensures strong consistency for engagement
- Moderation and spam detection

#### 8. **Search Service**

- Indexes users, hashtags, posts
- Full-text search, autocomplete, trending

#### 9. **Recommendation Service**

- Suggests users, posts, hashtags
- ML-based ranking, collaborative filtering

#### 10. **Notification Service**

- Real-time and batch notifications
- Push, email, in-app notifications

#### 11. **Storage Layer**

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

### Post Schema

```json
{
  "_id": "ObjectId",
  "userId": "ObjectId",
  "mediaUrl": "string",
  "caption": "string",
  "tags": ["tag1", "tag2"],
  "location": "string",
  "createdAt": "date",
  "likes": 123,
  "comments": 45,
  "savedBy": ["userId1", "userId2"]
}
```

### Story Schema

```json
{
  "_id": "ObjectId",
  "userId": "ObjectId",
  "mediaUrl": "string",
  "createdAt": "date",
  "expiresAt": "date",
  "viewedBy": ["userId1", "userId2"]
}
```

### Comment Schema

```json
{
  "_id": "ObjectId",
  "postId": "ObjectId",
  "userId": "ObjectId",
  "text": "string",
  "createdAt": "date",
  "likes": 5
}
```

## 🚀 Feed Generation

### Strategies

- **Pull Model**: Generate feed on read (query posts from followed users)
- **Push Model**: Fan-out posts to followers’ feeds on write
- **Hybrid**: Push for high-follower users, pull for others

### Caching

- Hot feeds cached in Redis
- Precompute feeds for active users
- Use background jobs for fan-out

### Pagination

- Use cursor-based pagination (createdAt, postId)
- Infinite scroll support

## 🔄 Media Upload & Delivery

### Upload Flow

1. Client uploads media to Media Service (chunked, resumable)
2. Media Service processes (resize, transcode, compress)
3. Store in object storage (S3, CDN)
4. Generate thumbnails, previews
5. Update Post metadata

### Delivery

- Use CDN for fast, global delivery
- Adaptive bitrate streaming for videos
- Lazy load images/videos in feed

## 🔒 Security & Privacy

### Authentication & Authorization

- OAuth2, JWT for user auth
- Access control for private accounts, stories
- Rate limiting, abuse prevention

### Data Protection

- Encryption at rest and in transit
- Secure media URLs (signed URLs, expiry)
- Moderation for comments, posts

### Privacy

- User-controlled privacy settings
- Block/report users
- GDPR compliance

## 📊 Performance Optimization

### Media

- Use CDN for static content
- Image/video compression and adaptive streaming
- Parallel uploads/downloads

### Feed

- Precompute feeds for active users
- Use Redis for hot feed caching
- Efficient fan-out with background workers

### Search

- Elasticsearch for indexing
- Autocomplete and trending queries

## 🧪 Testing Strategy

### Unit Testing

- Media upload/download logic
- Feed generation
- Like/comment operations

### Integration Testing

- End-to-end post creation to feed
- Story expiry and highlights
- Search and recommendation flows

### Load Testing

- Simulate high upload/view traffic
- Feed latency under heavy load
- CDN and storage stress tests

### Security Testing

- Auth bypass attempts
- Media URL access control
- Spam/abuse detection

## 🚀 Implementation Phases

### Phase 1: MVP (2-3 months)

- User registration, authentication, profile
- Media upload, post creation
- Basic feed, likes, comments
- Follow/unfollow, notifications

### Phase 2: Enhanced Features (3-4 months)

- Stories (ephemeral media)
- Search and discovery
- Explore/recommendation engine
- Media processing pipeline

### Phase 3: Advanced Features (2-3 months)

- Feed optimization (push/pull, caching)
- Group messaging, story highlights
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
- **Feed Cache**: Redis
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

- **Feed Latency**: Time to load feed
- **Media Upload/Download Latency**: Time to upload/view media
- **Engagement**: Likes, comments, shares per user
- **Storage Utilization**: Per user, per region
- **Error Rates**: Failed uploads, feed errors

### Alerting

- High error rates
- Feed or media latency spikes
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

- [Instagram Engineering Blog](https://instagram-engineering.com/)
- [How Instagram Scales](https://instagram-engineering.com/sharding-ids-at-instagram-1cf5a71e5a5c)
- [Instagram Feed Architecture](https://medium.com/@danielkador/how-instagram-feed-works-1c7f5c8b7b7)
- [CDN for Media Delivery](https://aws.amazon.com/cloudfront/)
- [Elasticsearch for Search](https://www.elastic.co/)

---

**Note**: This is a comprehensive system design for educational purposes. Real-world implementations may vary based on specific requirements, constraints, and business needs.
