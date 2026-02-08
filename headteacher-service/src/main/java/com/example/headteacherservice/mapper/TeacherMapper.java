package com.example.headteacherservice.mapper;

import com.example.headteacherservice.dto.TeacherDto;
import com.example.headteacherservice.entity.Teacher;
import jakarta.persistence.Id;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {SubjectMapper.class})
public interface TeacherMapper {

    @Mapping(source = "subject", target = "subjectDto")
    TeacherDto toDto(Teacher teacher);

    @Mapping(source = "subjectDto", target = "subject")
    Teacher toEntity(TeacherDto teacherDto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(TeacherDto dto, @MappingTarget Teacher entity);

}
