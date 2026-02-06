# 2. 이벤트 흐름 (ApplicationEvent → Kafka 교체 가능)

```mermaid
graph LR
    subgraph Producer["이벤트 발행 모듈"]
        ORDER_SVC[Order Service]
        PAY_SVC[Payment Service]
        DEL_SVC[Delivery Service]
    end

    subgraph Publisher["DomainEventPublisher"]
        PUB{{"publish(event)"}}
    end

    subgraph Impl["구현체 (Profile 전환)"]
        SPRING[SpringEventPublisher<br/>Profile: local]
        KAFKA[KafkaEventPublisher<br/>Profile: kafka]
    end

    subgraph Consumer["이벤트 소비 모듈"]
        C_PRODUCT[Product Module<br/>재고 차감]
        C_PAYMENT[Payment Module<br/>결제 요청]
        C_ORDER[Order Module<br/>상태 변경]
        C_NOTI[Notification Module<br/>이메일 발송]
    end

    ORDER_SVC -->|OrderPlacedEvent| PUB
    PAY_SVC -->|PaymentCompletedEvent| PUB
    DEL_SVC -->|DeliveryStatusChangedEvent| PUB

    PUB --> SPRING
    PUB --> KAFKA

    SPRING -->|@EventListener| C_PRODUCT & C_PAYMENT & C_ORDER & C_NOTI
    KAFKA -->|@KafkaListener| C_PRODUCT & C_PAYMENT & C_ORDER & C_NOTI

    style Publisher fill:#f9a825,color:#000
    style SPRING fill:#6db33f,color:#fff
    style KAFKA fill:#231f20,color:#fff
```
