\# Store Service Project Memory



\## 핵심 개발 원칙

\- \*\*SOLID 원칙을 참고하되, 단순함을 최우선으로 한다\*\*

\- \*\*현재 요구사항 기준으로만 설계한다\*\* (YAGNI)

\- 불필요한 추상화/인터페이스 남발 금지, 실제 필요할 때만 분리

\- \*\*추상화는 실제 필요한 상황에서만 도입한다\*\* (선제적 인터페이스 금지)

\- 과도한 패턴 적용보다 읽기 쉬운 코드 우선

\- \*\*소규모 팀/1인 개발 기준으로 설계한다\*\*

\- \*\*단순한 레이어드 구조 사용\*\* (Controller → Service → Repository)

\- \*\*복잡한 도메인 패턴 사용 금지\*\* (DDD Aggregate, Value Object, Domain Service 등 불필요)

\- \*\*핵심 로직 위주로 테스트한다\*\* (CRUD 보일러플레이트 테스트 불필요)

\- \*\*변경이 실제로 발생할 때 구조를 수정한다\*\* (선제적 설계 금지)

\- \*\*중복이 생길 때만 공통화한다\*\* (premature abstraction 금지)

\- \*\*이 프로젝트 맥락에 맞는 구현을 우선한다\*\* (일반론적 베스트 프랙티스보다 현재 프로젝트 상황 우선)

\- \*\*초기 운영은 단순하게 유지한다\*\* (모니터링/로깅/배포 등 최소 구성으로 시작)

\- \*\*MVP 수준의 구현을 우선시한다\*\* (완벽보다 동작하는 코드 우선)

\- \*\*각 모듈은 명확한 하나의 책임만 가진다\*\* (모듈 단위 SRP)

\- \*\*동작이 명확히 드러나는 코드를 작성한다\*\* (자기 설명적 코드, 불필요한 추상 레이어 지양)

\- \*\*리팩토링은 기능 구현과 분리해서 진행한다\*\* (기능 개발 중 구조 변경 금지)



\## 개발 추적

\- TODO.md 기준으로 개발 진행

\- 현재 상태: Phase 1 시작 전



\## 기술 스택 핵심 메모

\- Spring Boot 3.4.1, Java 17, Jakarta EE (not javax)

\- QueryDSL 5.1.0 with `:jakarta` classifier

\- JJWT 0.12.6: `verifyWith()`, `parseSignedClaims()` API

\- Hibernate 6: SEQUENCE strategy (allocationSize=50)

\- spring.data.redis (NOT spring.redis)

\- Spring Security 6 Lambda DSL

\- Layered architecture per module (Controller → Service → Repository)

\- 3 BFF apps: app-api(:8080), app-seller-api(:8081), app-admin-api(:8082)



\## 주의사항

\- module-product에 build.gradle 없음 (생성 필요)

\- ddl-auto: validate → update로 변경 필요 (초기 개발)

\- 대부분의 모듈 디렉토리 아직 미생성



