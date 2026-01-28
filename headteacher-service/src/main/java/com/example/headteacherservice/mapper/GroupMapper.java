package com.example.headteacherservice.mapper;

import com.example.headteacherservice.dto.SubjectDto;
import com.example.headteacherservice.entity.Subject;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    SubjectDto toDto(Subject subject);

    Subject toEntity(SubjectDto subjectDto);

}
