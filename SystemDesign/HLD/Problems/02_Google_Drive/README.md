# Design Google Drive / Dropbox – File Storage, Sharing, and Sync

## 📋 Problem Statement

Design a cloud-based file storage and synchronization service like Google Drive or Dropbox. The system should allow users to:

- Upload, download, and organize files/folders
- Share files/folders with others (with permissions)
- Sync files across multiple devices in real-time
- Support versioning and conflict resolution
- Provide search and preview capabilities
- Ensure data durability, security, and scalability

## 🎯 Functional Requirements

### Core Features

1. **File Upload/Download**: Users can upload, download, and organize files/folders
2. **File Sharing & Permissions**: Share files/folders with view/edit rights
3. **Real-time Sync**: Sync files across devices instantly
4. **Versioning & Conflict Resolution**: Maintain file history and resolve sync conflicts
5. **Search & Preview**: Search files by name/content and preview common file types
6. **User Management**: Authentication, authorization, and user profiles
7. **Trash/Restore**: Soft-delete and restore files

### Non-Functional Requirements

- **Durability**: 99.999999999% (11 9’s) data durability
- **Availability**: 99.9% uptime
- **Latency**: < 200ms for file operations
- **Scalability**: Support millions of users and petabytes of data
- **Consistency**: Eventual consistency for sync, strong for metadata
- **Security**: Encryption at rest and in transit, access control

## 🏗️ System Architecture

### High-Level Architecture

```
┌──────────────┐   ┌──────────────┐   ┌──────────────┐
│ Web Client   │   │ Mobile Client│   │ Desktop Sync │
└──────┬───────┘   └──────┬───────┘   └──────┬───────┘
       │                  │                  │
       └────────────┬─────┴─────┬────────────┘
                    │           │
           ┌────────▼───────────▼───────────┐
           │         API Gateway            │
           └────────┬───────────┬───────────┘
                    │           │
      ┌─────────────▼───┐   ┌───▼────────────┐
      │  Auth Service   │   │  Sync Service  │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ Metadata Svc    │   │ File Service   │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ Search Service  │   │ Versioning Svc │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ NotificationSvc │   │ Sharing Svc    │
      └────────┬────────┘   └────┬───────────┘
               │                 │
           ┌───▼─────────────────▼───┐
           │   Storage Layer (S3,    │
           │   Blob Store, CDN)      │
           └─────────────────────────┘
```

### Core Components

#### 1. **API Gateway**

- Entry point for all clients (web, mobile, desktop)
- Handles authentication, rate limiting, and routing

#### 2. **Auth Service**

- User authentication (OAuth, SSO, JWT)
- Session management
- Access control enforcement

#### 3. **Sync Service**

- Device registration and session management
- Real-time sync using long polling, WebSockets, or push notifications
- Delta sync (only changed parts of files)
- Conflict detection and resolution

#### 4. **Metadata Service**

- Stores file/folder metadata (name, path, size, owner, permissions, version)
- Handles directory structure and hierarchy
- Strong consistency for metadata operations

#### 5. **File Service**

- Handles file upload/download (chunked uploads, resumable)
- Stores files in distributed object storage (e.g., S3, GCS, Azure Blob)
- Manages file sharding and replication

#### 6. **Versioning Service**

- Maintains file version history
- Supports rollback and recovery
- Handles conflict resolution (last-writer-wins, manual merge)

#### 7. **Sharing Service**

- Manages file/folder sharing and permissions
- Generates shareable links (with expiry, password, access level)
- Handles group and public sharing

#### 8. **Search Service**

- Indexes file metadata and content (for supported types)
- Full-text search, filters, and sorting
- Real-time index updates on file changes

#### 9. **Notification Service**

- Sends notifications for file changes, shares, comments
- Supports email, push, and in-app notifications

#### 10. **Storage Layer**

- Distributed object storage (S3, GCS, Azure Blob)
- CDN for fast file delivery
- Replication and erasure coding for durability

## 💾 Data Models

### User Schema

```json
{
  "_id": "ObjectId",
  "email": "string",
  "password": "hashed",
  "name": "string",
  "devices": ["deviceId1", "deviceId2"],
  "createdAt": "date",
  "lastLogin": "date"
}
```

### File Metadata Schema

```json
{
  "_id": "ObjectId",
  "ownerId": "ObjectId",
  "name": "string",
  "type": "file|folder",
  "parentId": "ObjectId|null",
  "path": "/user/docs/file.pdf",
  "size": 123456,
  "version": 5,
  "createdAt": "date",
  "updatedAt": "date",
  "permissions": [{ "userId": "ObjectId", "role": "owner|editor|viewer" }],
  "sharedLinks": [
    { "url": "string", "expiresAt": "date", "access": "view|edit" }
  ],
  "isDeleted": false
}
```

### File Version Schema

```json
{
  "_id": "ObjectId",
  "fileId": "ObjectId",
  "version": 5,
  "createdAt": "date",
  "createdBy": "ObjectId",
  "storagePath": "string",
  "size": 123456,
  "checksum": "string"
}
```

### Sync State Schema

```json
{
  "_id": "ObjectId",
  "userId": "ObjectId",
  "deviceId": "string",
  "lastSyncedAt": "date",
  "pendingChanges": [
    {
      "fileId": "ObjectId",
      "changeType": "upload|delete|move|edit",
      "timestamp": "date"
    }
  ]
}
```

## 🔄 File Sync & Conflict Resolution

### Sync Flow

1. **Device detects file change** (edit, add, delete)
2. **Client computes delta** (changed bytes/blocks)
3. **Client uploads delta** to Sync Service
4. **Sync Service updates metadata, stores new version**
5. **Other devices receive notification** (push/poll)
6. **Devices download delta and update local copy**

### Conflict Resolution

- **Last-writer-wins** for simple cases
- **Manual merge** for complex conflicts (user notified)
- **Version history** allows rollback

## 🚀 Scalability Considerations

### Storage

- Use distributed object storage (S3, GCS, Azure Blob)
- Data sharding by user or file ID
- Replication and erasure coding for durability
- CDN for global file delivery

### Metadata

- Store in distributed NoSQL DB (e.g., DynamoDB, Cassandra)
- Strong consistency for metadata
- Indexes for fast lookup (by path, name, owner)

### Sync

- Delta sync to minimize bandwidth
- Batching and compression for efficiency
- Use message queues (Kafka, SQS) for event propagation

### Search

- Use Elasticsearch or Solr for indexing
- Real-time updates on file changes

### Load Balancing

- API Gateway and service layer behind load balancers
- Sticky sessions for sync if needed

## 🔒 Security Considerations

### Authentication & Authorization

- OAuth2, SSO, JWT for user auth
- Role-based access control (RBAC) for files/folders
- Expiring share links with optional password

### Data Protection

- Encryption at rest (AES-256) and in transit (TLS)
- Per-file encryption keys (optional for sensitive data)
- Regular security audits and penetration testing

### Privacy

- GDPR compliance for user data
- User-controlled sharing and access
- Audit logs for file access and changes

## 📊 Performance Optimization

### File Operations

- Chunked and resumable uploads/downloads
- Parallel uploads for large files
- Caching frequently accessed files/metadata in Redis

### Sync

- Delta sync to reduce data transfer
- Push notifications for real-time updates
- Efficient conflict detection algorithms

### Search

- Index only relevant file types for content search
- Use background jobs for heavy indexing

## 🧪 Testing Strategy

### Unit Testing

- File upload/download logic
- Metadata CRUD operations
- Permission checks

### Integration Testing

- End-to-end sync across devices
- Sharing and permission scenarios
- Conflict resolution cases

### Load Testing

- Simulate thousands of concurrent uploads/downloads
- Test sync latency under heavy load
- Storage and metadata DB stress tests

### Security Testing

- Auth bypass attempts
- File access control
- Link expiration and brute-force

## 🚀 Implementation Phases

### Phase 1: MVP (2-3 months)

- User registration and authentication
- File/folder upload, download, organization
- Basic sharing (view/edit links)
- Basic sync (manual refresh)

### Phase 2: Enhanced Features (3-4 months)

- Real-time sync across devices
- File versioning and history
- Trash/restore functionality
- Search and preview

### Phase 3: Advanced Features (2-3 months)

- Delta sync and conflict resolution
- Group and public sharing
- Mobile and desktop apps
- CDN integration for file delivery

### Phase 4: Enterprise Features (2-3 months)

- SSO integration
- Per-file encryption
- Advanced analytics and reporting
- API for third-party integrations

## 🛠️ Technology Stack

### Backend

- **Language**: Go, Java, or Node.js
- **Framework**: Spring Boot, Express.js, or Gin
- **Storage**: AWS S3, GCS, Azure Blob
- **Metadata DB**: DynamoDB, Cassandra, MongoDB
- **Search**: Elasticsearch
- **Cache**: Redis
- **Queue**: Kafka, SQS

### Frontend

- **Web**: React, Angular, or Vue.js
- **Mobile**: React Native, Flutter, or native
- **Desktop**: Electron, native apps

### Infrastructure

- **Cloud**: AWS, GCP, Azure
- **Load Balancer**: AWS ALB, Nginx
- **CDN**: CloudFront, Cloud CDN
- **Monitoring**: Prometheus, Grafana
- **Logging**: ELK Stack

## 📈 Monitoring & Analytics

### Key Metrics

- **File Operation Latency**: Upload/download times
- **Sync Latency**: Time to propagate changes
- **Storage Utilization**: Per user, per region
- **Error Rates**: Failed uploads/downloads
- **User Engagement**: Active users, files shared

### Alerting

- High error rates
- Sync delays
- Storage capacity thresholds
- Security incidents

## 🔄 Disaster Recovery

### Backup Strategy

- Regular metadata DB backups
- Cross-region replication for storage
- Version history for file recovery
- Automated restore procedures

### Failover Strategy

- Multi-region deployment
- Automatic failover for storage and DB
- Graceful degradation for non-critical features
- Data consistency checks after failover

---

## 📚 Additional Resources

- [Dropbox Tech Blog](https://dropbox.tech/)
- [Google Drive Engineering](https://cloud.google.com/blog/products/gcp/how-google-drive-works)
- [Designing Dropbox](https://www.highscalability.com/blog/2016/7/25/design-of-dropbox.html)
- [Delta Sync Algorithms](https://en.wikipedia.org/wiki/Delta_encoding)
- [Object Storage Best Practices](https://aws.amazon.com/s3/)

---

**Note**: This is a comprehensive system design for educational purposes. Real-world implementations may vary based on specific requirements, constraints, and business needs.
