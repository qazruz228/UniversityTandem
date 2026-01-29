package com.example.headteacherservice.mapper;

import com.example.headteacherservice.dto.ScheduleCreateOrUpdateDto;
import com.example.headteacherservice.dto.ScheduleResponseDto;
import com.example.headteacherservice.entity.Schedule;
import org.mapstruct.*;

@Mapper(componentModel = "spring",
        uses = {GroupMapper.class, SubjectMapper.class, TeacherMapper.class})
public interface ScheduleMapper {

    @Mapping(source = "group", target = "groupDto")
    @Mapping(source = "subject", target = "subjectDto")
    @Mapping(source = "teacher", target = "teacherDto")
    ScheduleResponseDto toDto(Schedule schedule);

    @Mapping(source = "groupDto", target = "group")
    @Mapping(source = "subjectDto", target = "subject")
    @Mapping(source = "teacherDto", target = "teacher")
    Schedule toEntity(ScheduleResponseDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Schedule updateEntityFromDto(ScheduleResponseDto dto, @MappingTarget Schedule entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Schedule updateEntityFromDto(ScheduleCreateOrUpdateDto dto, @MappingTarget Schedule entity);

}