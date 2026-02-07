package com.store.delivery.event;

import com.store.common.event.DomainEvent;
import lombok.Getter;

@Getter
public class DeliveryCompletedEvent extends DomainEvent {

    private final Long deliveryId;
    private final Long orderId;

    public DeliveryCompletedEvent(Long deliveryId, Long orderId) {
        this.deliveryId = deliveryId;
        this.orderId = orderId;
    }
}
