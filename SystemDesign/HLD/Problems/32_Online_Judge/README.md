# Design Online Judge – Code Execution Platform

## 📋 Problem Statement

Design a scalable online judge system (like LeetCode, HackerRank, Codeforces) that allows users to:

- Submit code solutions to programming problems
- Compile and execute code in a secure sandbox
- Evaluate results against test cases (public and hidden)
- Provide real-time feedback (pass/fail, runtime, memory)
- Support multiple languages and versions
- Maintain user profiles, leaderboards, and contest support
- Ensure security, isolation, and scalability

## 🎯 Functional Requirements

### Core Features

1. **User Management**: Registration, authentication, profiles, submissions history
2. **Problem Management**: CRUD for problems, test cases, constraints, tags
3. **Code Submission & Execution**: Upload code, select language, run in sandbox
4. **Result Evaluation**: Compare output with test cases, handle time/memory limits
5. **Contest Support**: Timed contests, rankings, live scoreboard
6. **Leaderboards & Stats**: User, global, and contest leaderboards
7. **Discussion & Hints**: Problem discussions, hints, editorial support

### Non-Functional Requirements

- **Availability**: 99.9% uptime
- **Latency**: < 2s for result feedback
- **Scalability**: 1M+ users, 100K+ concurrent submissions
- **Security**: Code isolation, resource limits, anti-abuse
- **Consistency**: Strong for submissions, eventual for leaderboards

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
      │ Problem Service │   │ SubmissionSvc  │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ Judge Service   │   │ Contest Svc    │
      └────────┬────────┘   └────┬───────────┘
               │                 │
      ┌────────▼────────┐   ┌────▼───────────┐
      │ LeaderboardSvc  │   │ DiscussionSvc  │
      └────────┬────────┘   └────┬───────────┘
               │                 │
           ┌───▼─────────────────▼───┐
           │   Storage Layer (DB,    │
           │   S3, Redis, CDN)       │
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

- User profiles, submissions, stats
- Contest registration, history

#### 4. **Problem Service**

- CRUD for problems, test cases, tags
- Problem statements, constraints, editorial

#### 5. **Submission Service**

- Handles code submission, language selection
- Stores submission metadata
- Queues jobs for judging

#### 6. **Judge Service**

- Secure sandboxed code execution (Docker, Firecracker, gVisor)
- Compiles, runs code with resource limits
- Compares output with test cases
- Handles time/memory limits, runtime errors
- Returns verdict (AC, WA, TLE, MLE, RE, CE)

#### 7. **Contest Service**

- Manages contests, timing, problem sets
- Live scoreboard, freeze/unfreeze
- Anti-cheat, plagiarism detection

#### 8. **Leaderboard Service**

- Global, user, and contest leaderboards
- Real-time and batch updates

#### 9. **Discussion Service**

- Problem discussions, hints, editorial
- Moderation, reporting

#### 10. **Storage Layer**

- SQL/NoSQL for metadata
- S3 for problem assets, submissions
- Redis for queues, caching

## 💾 Data Models

### User Schema

```json
{
  "_id": "ObjectId",
  "username": "string",
  "email": "string",
  "profilePic": "url",
  "solvedProblems": ["problemId1", "problemId2"],
  "submissions": ["submissionId1", "submissionId2"],
  "rating": 1500,
  "contests": ["contestId1", "contestId2"],
  "createdAt": "date"
}
```

### Problem Schema

```json
{
  "_id": "ObjectId",
  "title": "string",
  "description": "string",
  "difficulty": "Easy|Medium|Hard",
  "tags": ["tag1", "tag2"],
  "testCases": [{ "input": "string", "output": "string", "isPublic": true }],
  "constraints": "string",
  "editorial": "string",
  "createdAt": "date"
}
```

### Submission Schema

```json
{
  "_id": "ObjectId",
  "userId": "ObjectId",
  "problemId": "ObjectId",
  "language": "string",
  "code": "string",
  "status": "Pending|Running|Accepted|Wrong Answer|TLE|MLE|RE|CE",
  "runtime": 123,
  "memory": 1024,
  "testCaseResults": [
    {
      "testCaseId": "id",
      "status": "AC|WA|TLE|MLE|RE",
      "runtime": 10,
      "memory": 32
    }
  ],
  "createdAt": "date"
}
```

### Contest Schema

```json
{
  "_id": "ObjectId",
  "title": "string",
  "problems": ["problemId1", "problemId2"],
  "startTime": "date",
  "endTime": "date",
  "participants": ["userId1", "userId2"],
  "scoreboard": [{ "userId": "ObjectId", "score": 100, "rank": 1 }]
}
```

## 🚀 Code Execution & Sandboxing

### Execution Flow

1. User submits code via Submission Service
2. Submission is queued for Judge Service
3. Judge Service spins up sandbox (Docker, Firecracker, gVisor)
4. Compiles and runs code with resource limits
5. Compares output with test cases
6. Returns verdict and feedback

### Security

- Run code in isolated containers/VMs
- Limit CPU, memory, disk, network
- No external network access
- Monitor for malicious activity
- Auto-scale judge workers

### Language Support

- Pre-built images for each language/version
- Compile/run scripts for each language
- Caching for common dependencies

## 🔒 Security & Anti-Abuse

### Authentication & Authorization

- OAuth2, JWT for user auth
- Role-based access for admin/moderator

### Code Isolation

- Sandboxing (Docker, Firecracker, gVisor)
- Resource limits, timeouts
- No persistent storage between runs

### Anti-Abuse

- Rate limiting submissions
- Plagiarism detection (Moss, JPlag)
- Ban/suspend abusive users

## 📊 Performance Optimization

### Judging

- Auto-scale judge workers (Kubernetes, ECS)
- Batch submissions for contests
- Caching for common builds

### Storage

- Use S3 for assets, logs
- Redis for queues, caching
- Shard DBs by user/problem

### Search

- Elasticsearch for problems, users, discussions

## 🧪 Testing Strategy

### Unit Testing

- Problem CRUD, submission logic
- Judge scripts, language runners
- Permission checks

### Integration Testing

- End-to-end submission to verdict
- Contest flows, scoreboard updates
- Discussion and moderation

### Load Testing

- Simulate high submission rates
- Judge worker scaling
- DB and storage stress tests

### Security Testing

- Sandbox escape attempts
- Plagiarism and abuse detection
- Auth bypass attempts

## 🚀 Implementation Phases

### Phase 1: MVP (2-3 months)

- User registration, authentication, problem browsing
- Code submission, basic judging
- Problem CRUD, test cases
- Basic leaderboard, stats

### Phase 2: Enhanced Features (3-4 months)

- Contest support, live scoreboard
- Multiple language support
- Discussion, hints, editorial
- Plagiarism detection

### Phase 3: Advanced Features (2-3 months)

- Auto-scaling judge workers
- Advanced analytics, reporting
- API for third-party integrations
- Mobile/web apps

### Phase 4: Enterprise Features (2-3 months)

- Enterprise SSO, audit logs
- Advanced anti-abuse, compliance
- Custom problem sets, private contests
- Global CDN optimization

## 🛠️ Technology Stack

### Backend

- **Language**: Go, Java, or Node.js
- **Framework**: Spring Boot, Express.js, Gin
- **Judge Engine**: Docker, Firecracker, gVisor
- **Storage**: AWS S3, GCS, Azure Blob
- **Metadata DB**: Cassandra, DynamoDB, MongoDB
- **Queue**: Kafka, RabbitMQ
- **Search**: Elasticsearch
- **Cache**: Redis

### Frontend

- **Web**: React, Angular, Vue.js
- **Mobile**: React Native, Flutter, native

### Infrastructure

- **Cloud**: AWS, GCP, Azure
- **Orchestration**: Kubernetes, ECS
- **Load Balancer**: AWS ALB, Nginx
- **CDN**: CloudFront, Cloud CDN
- **Monitoring**: Prometheus, Grafana
- **Logging**: ELK Stack

## 📈 Monitoring & Analytics

### Key Metrics

- **Submission Latency**: Time from submit to verdict
- **Judge Worker Utilization**: Active/idle workers
- **Contest Participation**: Active users, submissions
- **Error Rates**: Failed submissions, judge errors
- **Plagiarism Incidents**: Detected cases

### Alerting

- High error rates
- Judge worker overload
- Submission latency spikes
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

- [LeetCode Engineering Blog](https://leetcode.com/articles/)
- [HackerRank Engineering](https://engineering.hackerrank.com/)
- [Firecracker MicroVMs](https://firecracker-microvm.github.io/)
- [Docker Security](https://docs.docker.com/engine/security/)
- [Plagiarism Detection](https://theory.stanford.edu/~aiken/moss/)

---

**Note**: This is a comprehensive system design for educational purposes. Real-world implementations may vary based on specific requirements, constraints, and business needs.
