package com.store.common.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 공통 BaseEntity — 생성/수정 시간 자동 관리
 *
 * ID 생성 전략은 각 엔티티에서 직접 정의한다.
 * Hibernate 6에서는 엔티티별 시퀀스가 기본이므로 명시적 선언이 필요하다.
 *
 * 사용 예시:
 * <pre>
 * {@code
 * @Entity
 * @Table(name = "products")
 * public class Product extends BaseEntity {
 *
 *     @Id
 *     @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
 *     @SequenceGenerator(name = "product_seq", sequenceName = "product_seq", allocationSize = 50)
 *     private Long id;
 * }
 * }
 * </pre>
 *
 * CRITICAL (Hibernate 6 변경사항):
 *   - Hibernate 5: 공유 시퀀스 hibernate_sequence 사용
 *   - Hibernate 6: 엔티티별 {entity}_SEQ 시퀀스가 기본
 *   - GenerationType.IDENTITY 대신 SEQUENCE 권장 (batch insert 성능)
 *   - allocationSize=50 으로 시퀀스 호출 횟수 최소화
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
