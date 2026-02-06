package com.store.product.entity;

import com.store.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * 상품 엔티티
 *
 * Hibernate 6 시퀀스 전략 적용:
 *   - GenerationType.SEQUENCE + @SequenceGenerator 명시적 선언
 *   - allocationSize=50 으로 DB 시퀀스 호출 최소화 (batch insert 최적화)
 *   - 시퀀스명은 테이블명과 일치시켜 관리 용이성 확보
 */
@Entity
@Table(name = "products")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Product extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
    @SequenceGenerator(
            name = "product_seq",
            sequenceName = "product_seq",
            allocationSize = 50
    )
    private Long id;

    @Column(nullable = false, length = 200)
    private String name;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stockQuantity;

    @Column(length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private ProductStatus status;

    @Column(nullable = false)
    private Long sellerId;

    @Builder
    private Product(String name, BigDecimal price, Integer stockQuantity,
                    String description, Long sellerId) {
        this.name = name;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.description = description;
        this.sellerId = sellerId;
        this.status = ProductStatus.ON_SALE;
    }

    public void deductStock(int quantity) {
        if (this.stockQuantity < quantity) {
            throw new IllegalStateException("재고가 부족합니다. 현재 재고: " + this.stockQuantity);
        }
        this.stockQuantity -= quantity;
    }

    public void restoreStock(int quantity) {
        this.stockQuantity += quantity;
    }
}
