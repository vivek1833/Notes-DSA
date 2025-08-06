# HLD Problems Index

This file contains metadata for all High-Level Design problems in this repository.

## Problems by Category

### General Web App / SaaS

- **Design Google Drive / Dropbox – File Storage & Sync** (`02_Google_Drive`)
  - Difficulty: Hard
  - Key Concepts: CDN, File Versioning, Sync Algorithms, Cloud Storage
  - Description: File storage, sharing, and synchronization platform

- **Design Instagram – Media Sharing Platform** (`03_Instagram`)
  - Difficulty: Hard
  - Key Concepts: Media Storage, Feed Generation, Real-time Updates, CDN
  - Description: Media sharing platform with stories, comments, likes

- **Design Twitter – Social Media Platform** (`04_Twitter`)
  - Difficulty: Hard
  - Key Concepts: Timeline Generation, Fan-out, Caching, Real-time
  - Description: Social media with timelines, tweets, followers

- **Design YouTube – Video Hosting & Streaming** (`05_YouTube`)
  - Difficulty: Very Hard
  - Key Concepts: Video Streaming, CDN, ML Recommendations, Content Delivery
  - Description: Video hosting, streaming, recommendation engine

- **Design Quora / Reddit – Q&A Platform** (`06_Quora`)
  - Difficulty: Medium
  - Key Concepts: Content Moderation, Voting Systems, Search, Community
  - Description: Q&A or discussion platform with voting, moderation

- **Design Pinterest – Image Discovery Platform** (`07_Pinterest`)
  - Difficulty: Medium
  - Key Concepts: Image Processing, Recommendation Engine, Discovery, Boards
  - Description: Image-based content discovery with pinning and boards

- **Design WhatsApp / Messenger – Messaging App** (`08_WhatsApp`)
  - Difficulty: Hard
  - Key Concepts: E2E Encryption, Message Queuing, Real-time, Privacy
  - Description: End-to-end encrypted messaging app

- **Design Zoom / Google Meet – Video Conferencing** (`09_Zoom`)
  - Difficulty: Very Hard
  - Key Concepts: WebRTC, Media Streaming, Room Management, Scalability
  - Description: Video conferencing system

- **Design Calendly / Google Calendar – Scheduling System** (`10_Calendly`)
  - Difficulty: Medium
  - Key Concepts: Calendar Integration, Timezone Handling, Scheduling, Availability
  - Description: Scheduling meetings with availability tracking

### E-commerce / Marketplace

- **Design Swiggy / DoorDash – Food Delivery Platform** (`12_Swiggy`)
  - Difficulty: Hard
  - Key Concepts: Real-time Tracking, Order Management, Geolocation, Delivery
  - Description: Food delivery with real-time order tracking

- **Design Uber / Ola – Ride-sharing Platform** (`13_Uber`)
  - Difficulty: Very Hard
  - Key Concepts: Geolocation, Matching Algorithms, Real-time Updates, Routing
  - Description: Ride-sharing with geolocation, matching, routing

- **Design Airbnb – Booking Platform** (`14_Airbnb`)
  - Difficulty: Hard
  - Key Concepts: Booking System, Calendar Management, Search, Reviews
  - Description: Booking platform for stays with calendar availability

- **Design Ticketmaster / BookMyShow – Ticket Booking** (`15_Ticketmaster`)
  - Difficulty: Medium
  - Key Concepts: Seat Management, Payment Processing, Concurrency, Booking
  - Description: Ticket booking with seat selection and payment

### Search / Recommendation

- **Design Google Search – Search Engine** (`16_Google_Search`)
  - Difficulty: Very Hard
  - Key Concepts: Search Indexing, PageRank, Caching, Ranking
  - Description: Indexing, ranking, caching search results

- **Design Netflix – Video Streaming Platform** (`17_Netflix`)
  - Difficulty: Very Hard
  - Key Concepts: Recommendation Engine, Content Delivery, ML, Streaming
  - Description: Video streaming with recommendations and personalization

- **Design Spotify – Music Streaming Platform** (`18_Spotify`)
  - Difficulty: Hard
  - Key Concepts: Audio Streaming, Playlist Management, Recommendations, Real-time
  - Description: Music streaming with playlists and real-time playback

- **Design Amazon Recommendations – Recommendation System** (`19_Amazon_Recommendations`)
  - Difficulty: Hard
  - Key Concepts: ML, Collaborative Filtering, A/B Testing, Personalization
  - Description: Personalized suggestions and collaborative filtering

### Real-Time and Event-Based

- **Design COVID Contact Tracing App – Privacy-Preserving System** (`20_COVID_Contact_Tracing`)
  - Difficulty: Medium
  - Key Concepts: Bluetooth, Privacy, Contact Tracing, Security
  - Description: Bluetooth-based proximity detection, privacy-preserving alerts

- **Design Real-Time Analytics System – Data Processing** (`22_Real_Time_Analytics`)
  - Difficulty: Hard
  - Key Concepts: Data Streaming, Aggregation, Real-time Processing, Analytics
  - Description: Metrics collection and aggregation (like Google Analytics)

- **Design Notification System – Multi-channel Messaging** (`23_Notification_System`)
  - Difficulty: Medium
  - Key Concepts: Message Queuing, Retry Logic, Batching, Multi-channel
  - Description: Push/email/SMS system with retries, batching

- **Design Rate Limiter – Abuse Prevention** (`24_Rate_Limiter`)
  - Difficulty: Easy
  - Key Concepts: Rate Limiting, Token Bucket, Leaky Bucket, Throttling
  - Description: To prevent abuse (token bucket, leaky bucket)

- **Design Monitoring System – Metrics & Alerting** (`25_Monitoring_System`)
  - Difficulty: Medium
  - Key Concepts: Metrics Collection, Alerting, Time Series DB, Monitoring
  - Description: Metrics scraping, alerting (like Prometheus)

### Content Delivery & Scalability

- **Design CDN – Content Delivery Network** (`26_CDN`)
  - Difficulty: Hard
  - Key Concepts: Edge Computing, Caching, Global Distribution, Load Balancing
  - Description: Content Delivery Network – Edge caching, content replication

- **Design Pastebin / Gist – Text Storage Service** (`27_Pastebin`)
  - Difficulty: Easy
  - Key Concepts: URL Shortening, Expiration, Text Storage, Sharing
  - Description: Text storage with public/private links and expiration

- **Design URL Shortener – Link Management** (`28_URL_Shortener`)
  - Difficulty: Easy
  - Key Concepts: URL Shortening, Redirection, Analytics, Caching
  - Description: Short links with redirection, tracking (like Bitly)

- **Design Web Crawler – Scalable Crawling System** (`29_Web_Crawler`)
  - Difficulty: Hard
  - Key Concepts: Web Crawling, Distributed Systems, Politeness, Indexing
  - Description: Scalable crawling and indexing (like Googlebot)

- **Design Ad Server – Real-time Bidding System** (`30_Ad_Server`)
  - Difficulty: Hard
  - Key Concepts: Real-time Bidding, Ad Targeting, Auction Systems, RTB
  - Description: Real-time bidding for ad slots, targeting

### Bonus / Niche Systems

- **Design Payment Gateway – Transaction Processing** (`31_Payment_Gateway`)
  - Difficulty: Very Hard
  - Key Concepts: Payment Processing, Fraud Detection, Security, Compliance
  - Description: Transaction processing, fraud detection (like Stripe)

- **Design Online Judge – Code Execution Platform** (`32_Online_Judge`)
  - Difficulty: Hard
  - Key Concepts: Code Execution, Sandboxing, Result Evaluation, Security
  - Description: Code submission, sandboxing, result evaluation (like LeetCode)

- **Design Blogging Platform – Content Management** (`33_Blogging_Platform`)
  - Difficulty: Medium
  - Key Concepts: Content Management, Comment Systems, Feeds, Publishing
  - Description: Publishing, commenting, feeds (like Medium)

- **Design Slack / Discord – Team Communication** (`34_Slack`)
  - Difficulty: Hard
  - Key Concepts: Real-time Messaging, Bot Framework, Integrations, Channels
  - Description: Messaging with channels, bots, integrations

- **Design Multiplayer Game Backend – Real-time Gaming** (`35_Multiplayer_Game_Backend`)
  - Difficulty: Very Hard
  - Key Concepts: Game State Management, Real-time Communication, Latency, Scaling
  - Description: Real-time communication and state sync

