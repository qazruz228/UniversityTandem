package com.example.teacherservice.events;

import com.example.teacherservice.enums.OutboxStatus;
import com.example.teacherservice.enums.converter.OutboxStatusConverter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "outbox_events")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OutboxEvent {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String payload;

    @Convert(converter = OutboxStatusConverter.class)
    @Column(nullable = false)
    private OutboxStatus outboxStatus;

    private int retryCount;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    private LocalDateTime processedAt;
}

