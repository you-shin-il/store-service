package com.store.notification.entity;

import com.store.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "email_notifications")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmailNotification extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "email_notification_seq")
    @SequenceGenerator(name = "email_notification_seq", sequenceName = "email_notification_seq", allocationSize = 50)
    private Long id;

    @Column(nullable = false, length = 255)
    private String toEmail;

    @Column(nullable = false, length = 255)
    private String subject;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private NotificationType type;

    @Column(nullable = false)
    private boolean sent = false;

    public EmailNotification(String toEmail, String subject, String content, NotificationType type) {
        this.toEmail = toEmail;
        this.subject = subject;
        this.content = content;
        this.type = type;
    }

    public void markSent() {
        this.sent = true;
    }
}
