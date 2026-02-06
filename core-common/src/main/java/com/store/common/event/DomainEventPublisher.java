package com.store.common.event;

public interface DomainEventPublisher {

    void publish(DomainEvent event);
}
