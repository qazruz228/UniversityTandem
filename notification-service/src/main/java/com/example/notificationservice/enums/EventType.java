package com.example.notificationservice.enums;

public enum EventType {
    CREATED,
    UPDATED;

    public static EventType fromString(String eventEnum) {
        for (EventType eventType : EventType.values()) {
            if (eventType.name().equalsIgnoreCase(eventEnum)) {
                return eventType;
            }
        }
        throw new IllegalArgumentException("Unknown eventType " + eventEnum);
    }



}
