package com.example.teacherservice.enums.converter;

import com.example.teacherservice.enums.OutboxStatus;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class OutboxStatusConverter implements AttributeConverter<OutboxStatus, String> {

    @Override
    public String convertToDatabaseColumn(OutboxStatus outboxStatusEnum) {
        return (outboxStatusEnum == null) ? null : outboxStatusEnum.toString();
    }

    @Override
    public OutboxStatus convertToEntityAttribute(String outboxStatusEnum) {
        return (outboxStatusEnum == null) ? null : OutboxStatus.valueOf(outboxStatusEnum);
    }
}
