package com.store.delivery.entity;

import com.store.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "deliveries")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "delivery_seq")
    @SequenceGenerator(name = "delivery_seq", sequenceName = "delivery_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long orderId;

    @Column(length = 100)
    private String trackingNumber;

    @Column(length = 100)
    private String carrier;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private DeliveryStatus status = DeliveryStatus.READY;

    public Delivery(Long orderId) {
        this.orderId = orderId;
    }

    public void startShipping(String trackingNumber, String carrier) {
        if (this.status != DeliveryStatus.READY) {
            throw new IllegalStateException("Cannot start shipping in status: " + this.status);
        }
        this.trackingNumber = trackingNumber;
        this.carrier = carrier;
        this.status = DeliveryStatus.SHIPPING;
    }

    public void complete() {
        if (this.status != DeliveryStatus.SHIPPING) {
            throw new IllegalStateException("Cannot complete delivery in status: " + this.status);
        }
        this.status = DeliveryStatus.DELIVERED;
    }
}
