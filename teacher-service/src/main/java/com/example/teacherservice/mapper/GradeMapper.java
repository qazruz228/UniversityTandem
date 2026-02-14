package com.example.teacherservice.mapper;


import com.example.teacherservice.dto.GradeRequestDto;
import com.example.teacherservice.dto.GradeResponseDto;
import com.example.teacherservice.entity.Grade;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface GradeMapper {

    Grade mapFromDto(GradeRequestDto dto);

    GradeResponseDto mapFromEntity(Grade grade);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Grade updateEntityFromDto(GradeRequestDto dto, @MappingTarget Grade grade);



}
