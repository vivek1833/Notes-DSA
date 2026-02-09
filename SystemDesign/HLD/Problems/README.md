# High-Level Design (HLD) Problems

This directory contains comprehensive system design solutions for various real-world applications and services. Each problem includes detailed architectural designs, scalability considerations, and implementation strategies.

## 📚 Problem Categories

### 🟢 Phase 1 – Warm-up (Easy / Foundation)

| Problem                                     | Description                                          | Difficulty | Key Concepts                                           |
| ------------------------------------------- | ---------------------------------------------------- | ---------- | ------------------------------------------------------ |
| [URL Shortener](./01_URL_Shortener/README.md) | Short links with redirection, tracking (like Bitly)  | Easy       | URL Shortening, Redirection, Analytics                 |
| [Pastebin](./02_Pastebin/README.md)         | Text storage with public/private links and expiration| Easy       | URL Shortening, Expiration, Text Storage               |
| [Rate Limiter](./03_Rate_Limiter/README.md) | To prevent abuse (token bucket, leaky bucket)        | Easy       | Rate Limiting, Token Bucket, Leaky Bucket              |
| [Notification System](./04_Notification_System/README.md) | Push/email/SMS system with retries, batching | Medium     | Message Queuing, Retry Logic, Batching                 |

---

### 🟡 Phase 2 – Medium Systems (Bread & Butter of Interviews)

| Problem                                     | Description                                          | Difficulty | Key Concepts                                           |
| ------------------------------------------- | ---------------------------------------------------- | ---------- | ------------------------------------------------------ |
| [Blogging Platform](./05_Blogging_Platform/README.md) | Publishing, commenting, feeds (like Medium)        | Medium     | Content Management, Comment Systems, Feeds             |
| [Quora](./06_Quora/README.md)               | Q&A platform with voting and moderation              | Medium     | Content Moderation, Voting Systems                     |
| [Pinterest](./07_Pinterest/README.md)       | Image-based content discovery with pinning           | Medium     | Image Processing, Recommendation Engine                |
| [Calendly](./08_Calendly/README.md)         | Scheduling meetings with availability tracking       | Medium     | Calendar Integration, Timezone Handling                |
| [Monitoring System](./09_Monitoring_System/README.md) | Metrics scraping, alerting (like Prometheus)      | Medium     | Metrics Collection, Alerting, Time Series DB           |

---

### 🔵 Phase 3 – Social / Media Systems (Fan-out, Real-time, Storage Heavy)

| Problem                                     | Description                                          | Difficulty | Key Concepts                                           |
| ------------------------------------------- | ---------------------------------------------------- | ---------- | ------------------------------------------------------ |
| [Twitter](./10_Twitter/README.md)           | Social media with timelines, tweets, followers       | Hard       | Timeline Generation, Fan-out, Caching                  |
| [Instagram](./11_Instagram/README.md)       | Media sharing platform with stories, comments, likes | Hard       | Media Storage, Feed Generation, Real-time Updates      |
| [WhatsApp](./12_WhatsApp/README.md)         | End-to-end encrypted messaging app                   | Hard       | E2E Encryption, Message Queuing                        |
| [Slack](./13_Slack/README.md)               | Messaging with channels, bots, integrations          | Hard       | Real-time Messaging, Bot Framework, Integrations       |

---

### 🛒 Phase 4 – E-commerce & Marketplaces (Business + Scaling)

| Problem                                     | Description                                           | Difficulty | Key Concepts                                        |
| ------------------------------------------- | ----------------------------------------------------- | ---------- | --------------------------------------------------- |
| [Amazon](./14_Amazon/README.md)             | E-commerce with catalog, search, cart, checkout       | Very Hard  | Inventory Management, Search, Payment Processing    |
| [Swiggy](./15_Swiggy/README.md)             | Food delivery with real-time order tracking           | Hard       | Real-time Tracking, Order Management, Geolocation   |
| [Airbnb](./16_Airbnb/README.md)             | Booking platform for stays with calendar availability | Hard       | Booking System, Calendar Management, Search         |
| [Ticketmaster](./17_Ticketmaster/README.md) | Ticket booking with seat selection and payment        | Medium     | Seat Management, Payment Processing, Concurrency    |

---

### 🌐 Phase 5 – Content Delivery & Streaming Systems (Heavy on Scale)

| Problem                                       | Description                                                  | Difficulty | Key Concepts                                     |
| --------------------------------------------- | ------------------------------------------------------------ | ---------- | ------------------------------------------------ |
| [CDN](./18_CDN/README.md)                     | Content Delivery Network – Edge caching, content replication | Hard       | Edge Computing, Caching, Global Distribution     |
| [YouTube](./19_YouTube/README.md)             | Video hosting, streaming, recommendation engine              | Very Hard  | Video Streaming, CDN, ML Recommendations         |
| [Netflix](./20_Netflix/README.md)             | Video streaming with recommendations and personalization     | Very Hard  | Recommendation Engine, Content Delivery, ML      |
| [Spotify](./21_Spotify/README.md)             | Music streaming with playlists and real-time playback        | Hard       | Audio Streaming, Playlist Management, Recommendations |

---

### 🔍 Phase 6 – Search, Recommendations & Data-Intensive

| Problem                                                         | Description                                              | Difficulty | Key Concepts                                          |
| --------------------------------------------------------------- | -------------------------------------------------------- | ---------- | ----------------------------------------------------- |
| [Google Search](./22_Google_Search/README.md)                   | Indexing, ranking, caching search results                | Very Hard  | Search Indexing, PageRank, Caching                    |
| [Amazon Recommendations](./23_Amazon_Recommendations/README.md) | Personalized suggestions and collaborative filtering     | Hard       | ML, Collaborative Filtering, A/B Testing              |
| [Ad Server](./24_Ad_Server/README.md)                           | Real-time bidding for ad slots, targeting                | Hard       | Real-time Bidding, Ad Targeting, Auction Systems      |
| [Web Crawler](./25_Web_Crawler/README.md)                       | Scalable crawling and indexing (like Googlebot)          | Hard       | Web Crawling, Distributed Systems, Politeness         |

---

### ⚡ Phase 7 – Real-Time & Finance-Grade Systems (Advanced)

| Problem                                                         | Description                                                    | Difficulty | Key Concepts                                      |
| --------------------------------------------------------------- | -------------------------------------------------------------- | ---------- | ------------------------------------------------- |
| [Stock Trading Platform](./26_Stock_Trading_Platform/README.md) | Real-time quotes, high availability, trade matching            | Very Hard  | Low Latency, Order Matching, High Availability    |
| [COVID Contact Tracing](./27_COVID_Contact_Tracing/README.md)   | Bluetooth-based proximity detection, privacy-preserving alerts | Medium     | Bluetooth, Privacy, Contact Tracing               |
| [Real-Time Analytics](./28_Real_Time_Analytics/README.md)       | Metrics collection and aggregation (like Google Analytics)     | Hard       | Data Streaming, Aggregation, Real-time Processing |
| [Multiplayer Game Backend](./29_Multiplayer_Game_Backend/README.md) | Real-time communication and state sync                     | Very Hard  | Game State Management, Real-time Communication, Latency |
| [Payment Gateway](./30_Payment_Gateway/README.md)               | Transaction processing, fraud detection (like Stripe)          | Very Hard  | Payment Processing, Fraud Detection, Security     |

---

## 🛠️ Key Technologies Covered

- **Databases**: SQL, NoSQL, Time-series, Graph databases
- **Caching**: Redis, Memcached, CDN
- **Message Queues**: Kafka, RabbitMQ, SQS
- **Search**: Elasticsearch, Solr
- **Real-time**: WebSockets, Server-Sent Events, WebRTC
- **Cloud Services**: AWS, GCP, Azure
- **Monitoring**: Prometheus, Grafana, ELK Stack
- **Containerization**: Docker, Kubernetes

## 📈 System Design Principles

Each solution follows these core principles:

1. **Scalability**: Handle increasing load efficiently
2. **Availability**: Ensure system uptime and reliability
3. **Consistency**: Maintain data integrity across the system
4. **Performance**: Optimize for speed and responsiveness
5. **Security**: Protect against threats and vulnerabilities
6. **Maintainability**: Design for easy updates and modifications

## 📚 Additional Resources

- [System Design Primer](https://github.com/donnemartin/system-design-primer)
- [High Scalability](http://highscalability.com/)
- [Martin Fowler's Blog](https://martinfowler.com/)
- [AWS Architecture Center](https://aws.amazon.com/architecture/)
