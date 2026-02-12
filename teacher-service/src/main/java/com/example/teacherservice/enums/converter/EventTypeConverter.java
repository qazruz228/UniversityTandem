package com.example.teacherservice.enums.converter;

import com.example.teacherservice.enums.EventType;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class EventTypeConverter implements AttributeConverter<EventType, String> {

    @Override
    public String convertToDatabaseColumn(EventType eventEnum) {
        return (eventEnum == null) ? null : eventEnum.toString();
    }

    @Override
    public EventType convertToEntityAttribute(String eventEnum) {
        return (eventEnum == null) ? null : EventType.valueOf(eventEnum);
    }
}
