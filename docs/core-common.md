# core-common 모듈

## Exception (`com.store.common.exception`)

### BusinessException
- 비즈니스 로직 예외의 기본 클래스
- `HttpStatus`를 포함하여 응답 상태코드 지정 가능
- 기본 상태코드: `400 BAD_REQUEST`
- 사용 예: `throw new BusinessException("재고가 부족합니다")`
- 상태코드 지정: `throw new BusinessException("접근 권한이 없습니다", HttpStatus.FORBIDDEN)`

### EntityNotFoundException
- `BusinessException` 상속, 상태코드 `404 NOT_FOUND` 고정
- 엔티티명 + ID: `throw new EntityNotFoundException("Product", 1L)` → `"Product not found: id=1"`
- 메시지 직접 지정: `throw new EntityNotFoundException("해당 상품을 찾을 수 없습니다")`

### GlobalExceptionHandler
- `@RestControllerAdvice` 기반 전역 예외 처리
- `BusinessException` → 해당 status + message 반환
- `MethodArgumentNotValidException` → 400 + 필드별 에러 메시지 맵
- `Exception` → 500, 내부 오류 메시지 노출 안 함 (로그만 기록)

## Response (`com.store.common.response`)

### ApiResponse\<T>
- 성공 응답 래퍼 클래스 (status, message, data)
- `@JsonInclude(NON_NULL)` — data가 null이면 JSON에서 제외
- `ApiResponse.ok(data)` → `{ "status": 200, "message": "OK", "data": ... }`
- `ApiResponse.ok()` → `{ "status": 200, "message": "OK" }`
- `ApiResponse.created(data)` → `{ "status": 201, "message": "Created", "data": ... }`

### ErrorResponse
- 에러 응답 래퍼 클래스 (status, message, errors)
- `GlobalExceptionHandler`에서 사용
- `ErrorResponse.of(400, "message")` → 기본 에러
- `ErrorResponse.of(400, "message", fieldErrors)` → Validation 에러 + 필드별 메시지
