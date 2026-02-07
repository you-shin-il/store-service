# module-order 모듈

## Entity (`com.store.order.entity`)

### Order
- 주문 엔티티 (`orders` 테이블)
- `buyerId`로 구매자 식별
- `OrderItem` 목록을 OneToMany(cascade ALL)로 관리
- `totalAmount` — 주문 총액 (아이템 추가 시 자동 합산)
- 주요 메서드:
  - `Order(buyerId, deliveryAddress)` — 생성 시 CREATED 상태
  - `addItem(productId, productName, productPrice, quantity)` — 주문 항목 추가 + 총액 합산
  - `cancel()` — CREATED/PAID 상태에서만 취소 가능
  - `changeStatus(newStatus)` — 상태 변경

### OrderItem
- 주문 항목 엔티티 (`order_items` 테이블)
- Order와 ManyToOne 연관관계
- 주문 시점의 상품 정보를 스냅샷으로 보관 (productName, productPrice)
- `getTotalPrice()` — 단가 x 수량

### OrderStatus
- `CREATED` → `PAID` → `SHIPPING` → `DELIVERED` → `CONFIRMED`
- `CANCELLED` (CREATED/PAID에서만 전이 가능)

## Event (`com.store.order.event`)

### OrderPlacedEvent
- 주문 생성 시 발행 (orderId, buyerId)
- 리스너: 재고 차감 등

### OrderCancelledEvent
- 주문 취소 시 발행 (orderId, buyerId)
- 리스너: 재고 복원 등

## Repository (`com.store.order.repository`)

### OrderRepository
- `JpaRepository<Order, Long>` 기본 CRUD
- `findByBuyerIdOrderByCreatedAtDesc(buyerId)` — 구매자별 주문 목록 (최신순)
