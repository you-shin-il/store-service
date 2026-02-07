package com.store.order.event;

import com.store.common.event.DomainEvent;
import lombok.Getter;

@Getter
public class OrderPlacedEvent extends DomainEvent {

    private final Long orderId;
    private final Long buyerId;

    public OrderPlacedEvent(Long orderId, Long buyerId) {
        this.orderId = orderId;
        this.buyerId = buyerId;
    }
}
