package com.store.notification.service;

public interface EmailService {

    void send(String to, String subject, String content);
}
