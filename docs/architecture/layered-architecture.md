# 4. 레이어드 아키텍처 (모듈 내부 구조)

```mermaid
graph TB
    subgraph External["외부"]
        CLIENT[Vue.js Client]
        DB[(PostgreSQL)]
        EVENT_BUS[Event Bus<br/>Spring / Kafka]
        S3[(MinIO / S3)]
    end

    subgraph Controller["Controller 레이어"]
        CTRL[REST Controller<br/>+ DTO]
        EVT_LISTENER[Event Listener<br/>이벤트 수신]
    end

    subgraph Service["Service 레이어"]
        SVC[Service<br/>비즈니스 로직]
    end

    subgraph Entity["Entity 레이어"]
        ENT[JPA Entity]
    end

    subgraph Repository["Repository 레이어"]
        REPO[JPA Repository]
        EVT_PUB[Event Publisher]
        FILE_SVC[File Storage]
    end

    CLIENT --> CTRL
    CTRL --> SVC
    EVT_LISTENER --> SVC
    SVC --> ENT
    SVC --> REPO & EVT_PUB & FILE_SVC
    REPO --> DB
    EVT_PUB --> EVENT_BUS
    FILE_SVC --> S3

    style Entity fill:#1a73e8,color:#fff
    style Service fill:#4285f4,color:#fff
    style Controller fill:#34a853,color:#fff
    style Repository fill:#ea4335,color:#fff
    style External fill:#f5f5f5,color:#333
```

## 모듈 내부 패키지 구조

```
module-xxx/
└── src/main/java/com/store/xxx/
    ├── controller/     ← REST Controller + DTO
    ├── service/        ← 비즈니스 로직
    ├── repository/     ← JPA Repository
    └── entity/         ← JPA Entity
```
