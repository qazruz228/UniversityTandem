package com.example.teacherservice.enums.converter;

import com.example.teacherservice.enums.Subject;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class SubjectConverter implements AttributeConverter<Subject, String> {


    @Override
    public String convertToDatabaseColumn(Subject subjectEnum) {
        return (subjectEnum == null) ? null : subjectEnum.toString();
    }

    @Override
    public Subject convertToEntityAttribute(String dbData) {
        return (dbData == null) ? null : Subject.valueOf(dbData);
    }
}
