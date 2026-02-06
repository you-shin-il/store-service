package com.store.common.event;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public abstract class DomainEvent {

    private final LocalDateTime occurredAt = LocalDateTime.now();
}
