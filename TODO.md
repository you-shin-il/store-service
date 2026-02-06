# Store Service — TODO

> 진행 표기: `[ ]` 대기 / `[~]` 진행중 / `[x]` 완료
> 총 예상: **145h (약 18 영업일)**

---

## 기술 스택

| 구분       | 기술                                                    |
|:-----------|:--------------------------------------------------------|
| Language   | Java 17                                                 |
| Framework  | Spring Boot 3.4.1, Spring Security 6, Spring Data JPA   |
| Build      | Gradle 8.5 (Multi-Module)                               |
| Database   | PostgreSQL 16, Hibernate 6, QueryDSL 5.1.0              |
| Cache      | Redis 7                                                 |
| Auth       | JWT (JJWT 0.12.6), BCrypt                               |
| Storage    | MinIO (local) / AWS S3 (dev)                            |
| Mail       | Naver SMTP (JavaMailSender)                             |
| Event      | Spring ApplicationEvent (→ Kafka 전환 가능)             |
| API Doc    | Swagger (springdoc-openapi)                             |
| Frontend   | Vue 3, TypeScript, Vite, Pinia, Axios                   |
| Infra      | Docker Compose (PostgreSQL, Redis, MinIO)                |
| Test       | JUnit 5, H2 (테스트 DB), Spring Security Test           |

---

## Phase 1. 프로젝트 초기 세팅 (19.5h)

### 1.1 개발 환경 준비 (2h)

- [x] `1.1.1` Gradle Wrapper 생성 (0.5h)
- [x] `1.1.2` gradle.properties 작성 (0.5h)
- [x] `1.1.3` Docker Compose 작성 — PostgreSQL, Redis, MinIO (0.5h)
- [x] `1.1.4` Docker 컨테이너 실행 확인 (0.5h)

### 1.2 공통 모듈 만들기 — core-common (3.5h)

- [ ] `1.2.1` build.gradle 수정 — web, jackson 의존성 추가 (0.5h)
- [ ] `1.2.2` JPA Auditing 설정 — `JpaAuditingConfig.java` (0.25h)
- [ ] `1.2.3` 도메인 이벤트 기본 클래스 — `DomainEvent.java` (0.5h)
- [ ] `1.2.4` 이벤트 발행 인터페이스 — `DomainEventPublisher.java` (0.25h)
- [ ] `1.2.5` Spring 이벤트 구현체 — `SpringEventPublisher.java` (0.5h)
- [ ] `1.2.6` 공통 예외 클래스 — `BusinessException`, `EntityNotFoundException`, `GlobalExceptionHandler` (1h)
- [ ] `1.2.7` 공통 API 응답 — `ApiResponse.java` (0.5h)

### 1.3 도메인 모듈 뼈대 만들기 (10h)

- [ ] `1.3.1` module-product 완성 — `Category.java`, Product 보강 (1.5h)
- [ ] `1.3.2` module-order 생성 — `Order`, `OrderItem`, `OrderStatus`, 이벤트 (2h)
- [ ] `1.3.3` module-payment 생성 — `Payment`, `PaymentStatus`, `PaymentMethod`, 이벤트 (1.5h)
- [ ] `1.3.4` module-cart 생성 — `Cart`, `CartItem` (1h)
- [ ] `1.3.5` module-review 생성 — `Review`, `ReviewStatus` (0.5h)
- [ ] `1.3.6` module-delivery 생성 — `Delivery`, `DeliveryStatus`, 이벤트 (1.5h)
- [ ] `1.3.7` module-file 생성 — `FileMetadata`, `FileType`, `FileStorageService` (1h)
- [ ] `1.3.8` module-notification 생성 — `EmailNotification`, `NotificationType`, `EmailService` (0.5h)
- [ ] `1.3.9` module-user 보강 — `User`, `UserRole`, `AuthService`, `UserRepository` (0.5h)

### 1.4 서버 앱 만들기 (3h)

- [ ] `1.4.1` app-api 생성 — 구매자용 서버 :8080 (1h)
- [ ] `1.4.2` app-seller-api 생성 — 판매자용 서버 :8081 (0.5h)
- [ ] `1.4.3` app-admin-api 생성 — 관리자용 서버 :8082 (0.5h)
- [ ] `1.4.4` 테스트 설정 — H2 기반 `application-test.yml` (1h)

### 1.5 빌드 확인 및 Git 저장 (1h)

- [ ] `1.5.1` 루트 src 삭제 (0.25h)
- [ ] `1.5.2` `./gradlew clean build` 빌드 검증 (0.5h)
- [ ] `1.5.3` Git 초기화 및 첫 커밋 (0.25h)

---

## Phase 2. 회원 기능 (18h)

### 2.1 회원가입 / 로그인 API (7h)

- [ ] `2.1.1` 회원가입 API — `AuthController`, `AuthService` (2h)
- [ ] `2.1.2` 로그인 API — JWT 토큰 발급 (1.5h)
- [ ] `2.1.3` 토큰 갱신 API — Redis 기반 Refresh Token (1h)
- [ ] `2.1.4` JPA Repository 구현 — `UserRepository` (2.5h)

### 2.2 이메일 인증 (3h)

- [ ] `2.2.1` 이메일 인증 발송 — `EmailService` (2h)
- [ ] `2.2.2` 이메일 인증 확인 API — `EmailVerificationController` (1h)

### 2.3 로그인 화면 — Vue (8h)

- [ ] `2.3.1` Vue 프로젝트 초기화 — Vite + Vue 3 + TS + Pinia (1.5h)
- [ ] `2.3.2` 로그인 화면 — `LoginView.vue` (2h)
- [ ] `2.3.3` 회원가입 화면 — `SignupView.vue` (2h)
- [ ] `2.3.4` 인증 상태 관리 — `useAuthStore.ts`, `axiosInstance.ts` (2.5h)

---

## Phase 3. 상품 기능 (26h)

### 3.1 상품 등록/수정/삭제 API (9h)

- [ ] `3.1.1` 상품 등록 API — `ProductController`, `ProductService` (2.5h)
- [ ] `3.1.2` 상품 수정 API — 본인 상품만 변경 (1h)
- [ ] `3.1.3` 상품 삭제 API — 소프트 삭제 (0.5h)
- [ ] `3.1.4` 상품 목록 조회 API — QueryDSL 동적 쿼리 + 페이지네이션 (3h)
- [ ] `3.1.5` 상품 상세 조회 API (2h)

### 3.2 카테고리 관리 (3.5h)

- [ ] `3.2.1` 카테고리 CRUD API — `CategoryController`, `CategoryService` (2h)
- [ ] `3.2.2` 카테고리 트리 조회 — 계층형 트리 구조 반환 (1.5h)

### 3.3 파일 업로드 (7.5h)

- [ ] `3.3.1` 파일 업로드 API — `FileController`, `FileStorageService` (3h)
- [ ] `3.3.2` 이미지 썸네일 생성 — `ImageResizeService` (2h)
- [ ] `3.3.3` 파일 다운로드/조회 — Presigned URL (1h)
- [ ] `3.3.4` 파일 검증 — `FileValidator` (1.5h)

### 3.4 상품 화면 — Vue (6h)

- [ ] `3.4.1` 상품 목록 화면 — `ProductListView.vue` (2.5h)
- [ ] `3.4.2` 상품 상세 화면 — `ProductDetailView.vue` (1.5h)
- [ ] `3.4.3` 상품 등록 화면 (판매자) — `ProductFormView.vue` (2h)

---

## Phase 4. 장바구니 + 주문 (25h)

### 4.1 장바구니 담기/삭제 (4h)

- [ ] `4.1.1` 장바구니 담기 API — `CartController`, `CartService` (2h)
- [ ] `4.1.2` 장바구니 조회 API (1h)
- [ ] `4.1.3` 장바구니 수량 변경/삭제 API (1h)

### 4.2 주문 생성 (8h)

- [ ] `4.2.1` 주문 생성 API — `OrderController`, `OrderService` (3h)
- [ ] `4.2.2` 주문 목록 조회 API — 구매자/판매자 분리 (1.5h)
- [ ] `4.2.3` 주문 상세 조회 API (1h)
- [ ] `4.2.4` 주문 취소 API — 재고 복원 이벤트 발행 (2.5h)

### 4.3 이벤트 연동 — 주문→재고차감 (5h)

- [ ] `4.3.1` 주문 생성 이벤트 발행 — `OrderPlacedEvent` (1.5h)
- [ ] `4.3.2` 재고 차감 이벤트 리스너 — `OrderEventListener` (2h)
- [ ] `4.3.3` 재고 복원 이벤트 리스너 — `OrderCancelledEvent` 수신 (1.5h)

### 4.4 장바구니/주문 화면 — Vue (8h)

- [ ] `4.4.1` 장바구니 화면 — `CartView.vue` (2.5h)
- [ ] `4.4.2` 주문서 화면 — `OrderFormView.vue` (3h)
- [ ] `4.4.3` 주문 내역 화면 — `OrderListView.vue`, `OrderDetailView.vue` (2.5h)

---

## Phase 5. 결제 (10h)

### 5.1 가상 결제 처리 (6h)

- [ ] `5.1.1` 결제 요청 API — `PaymentController`, `PaymentService` (2.5h)
- [ ] `5.1.2` 결제 상태 조회 API (1h)
- [ ] `5.1.3` 환불 처리 API (2.5h)

### 5.2 결제 완료 이벤트 → 메일 발송 (4h)

- [ ] `5.2.1` 결제 완료 이벤트 발행 — `PaymentCompletedEvent` (1h)
- [ ] `5.2.2` 주문 상태 변경 리스너 — 주문 상태를 PAID로 변경 (1.5h)
- [ ] `5.2.3` 결제 확인 메일 발송 리스너 (1.5h)

---

## Phase 6. 배송 + 리뷰 (18.5h)

### 6.1 배송 상태 관리 (8h)

- [ ] `6.1.1` 운송장 입력 API — `DeliveryController`, `DeliveryService` (2.5h)
- [ ] `6.1.2` 배송 상태 변경 API — 상태 머신 규칙 적용 (2h)
- [ ] `6.1.3` 배송 조회 API (1h)
- [ ] `6.1.4` 배송 상태 이벤트 — 주문 상태 동기화 + 메일 발송 (2.5h)

### 6.2 리뷰 작성/조회 (6h)

- [ ] `6.2.1` 리뷰 작성 API — `ReviewController`, `ReviewService` (3h)
- [ ] `6.2.2` 리뷰 목록 조회 API — 최신순/평점순, 페이지네이션 (1.5h)
- [ ] `6.2.3` 리뷰 삭제 API — 소프트 삭제 (1.5h)

### 6.3 자동 구매확정 (4.5h)

- [ ] `6.3.1` 자동 구매확정 스케줄러 — `AutoConfirmScheduler` (2.5h)
- [ ] `6.3.2` 구매확정 이벤트 연동 — 주문 상태 동기화 + 알림 (2h)

---

## Phase 7. 관리자 (16h)

### 7.1 관리자 대시보드 API (7h)

- [ ] `7.1.1` 회원 관리 API — `AdminUserController` (2h)
- [ ] `7.1.2` 상품 관리 API — `AdminProductController` (1.5h)
- [ ] `7.1.3` 주문 통계 API — `AdminOrderController` (2h)
- [ ] `7.1.4` 리뷰 신고 관리 API — `AdminReviewController` (1.5h)

### 7.2 관리자 화면 — Vue (9h)

- [ ] `7.2.1` 관리자 Vue 프로젝트 초기화 — `frontend/admin-app/` (1.5h)
- [ ] `7.2.2` 대시보드 화면 — `DashboardView.vue` (3h)
- [ ] `7.2.3` 회원/상품/주문 관리 화면 (4.5h)

---

## Phase 8. 마무리 (12h)

- [ ] `8.1.1` 전체 흐름 테스트 — 가입→상품→장바구니→주문→결제→배송→리뷰 (5h)
- [ ] `8.1.2` API 문서 확인 — Swagger UI 전체 API 노출 확인 (1h)
- [ ] `8.2.1` 버그 수정 (4h)
- [ ] `8.2.2` 코드 정리 — 미사용 코드 삭제, 중복 제거 (2h)
