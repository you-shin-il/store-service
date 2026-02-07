package com.store.payment.event;

import com.store.common.event.DomainEvent;
import lombok.Getter;

@Getter
public class PaymentCompletedEvent extends DomainEvent {

    private final Long paymentId;
    private final Long orderId;
    private final Long buyerId;

    public PaymentCompletedEvent(Long paymentId, Long orderId, Long buyerId) {
        super();
        this.paymentId = paymentId;
        this.orderId = orderId;
        this.buyerId = buyerId;
    }
}
