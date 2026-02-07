package com.store.order.event;

import com.store.common.event.DomainEvent;
import lombok.Getter;

@Getter
public class OrderCancelledEvent extends DomainEvent {

    private final Long orderId;
    private final Long buyerId;

    public OrderCancelledEvent(Long orderId, Long buyerId) {
        this.orderId = orderId;
        this.buyerId = buyerId;
    }
}
