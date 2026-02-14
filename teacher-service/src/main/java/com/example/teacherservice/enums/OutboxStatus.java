package com.example.teacherservice.enums;

public enum OutboxStatus {

    NEW,
    SENT,
    FAILED;


    public static OutboxStatus fromString(String outboxEnum) {
        for (OutboxStatus outbox : OutboxStatus.values()) {
            if (outbox.name().equalsIgnoreCase(outboxEnum)) {
                return outbox;
            }
        }
        throw new IllegalArgumentException("Unknown eventType " + outboxEnum);
    }





}
