# module-product 모듈

## Entity (`com.store.product.entity`)

### Product
- 상품 엔티티 (`products` 테이블)
- `Category`와 ManyToOne 연관관계 (LAZY)
- `sellerId`로 판매자 식별 (FK 없이 ID만 보관)
- 주요 메서드:
  - `update(name, price, description, category)` — 상품 정보 수정
  - `deductStock(quantity)` — 재고 차감 (부족 시 예외)
  - `restoreStock(quantity)` — 재고 복원
  - `delete()` — 소프트 삭제 (status → DELETED)
  - `updateStock(stockQuantity)` — 재고 직접 설정

### ProductStatus
- `ON_SALE`, `SOLD_OUT`, `HIDDEN`, `DELETED`

### Category
- 카테고리 엔티티 (`categories` 테이블)
- 자기 참조 ManyToOne으로 계층 구조 표현 (`parent`)
- `depth` — 0부터 시작하는 깊이 (부모 기준 자동 계산)
- `sortOrder` — 같은 depth 내 정렬 순서
- 주요 메서드:
  - `Category(name, parent, sortOrder)` — 생성자 (depth 자동 계산)
  - `update(name, sortOrder)` — 이름/정렬순서 수정

## Repository (`com.store.product.repository`)

### ProductRepository
- `JpaRepository<Product, Long>` 기본 CRUD

### CategoryRepository
- `JpaRepository<Category, Long>` 기본 CRUD
- `findAllByOrderByDepthAscSortOrderAsc()` — depth → sortOrder 순 전체 조회
