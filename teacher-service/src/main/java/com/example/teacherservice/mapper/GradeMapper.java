package com.example.teacherservice.mapper;


import com.example.teacherservice.dto.GradeCreateDto;
import com.example.teacherservice.dto.GradeResponseDto;
import com.example.teacherservice.entity.Grade;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GradeMapper {


    Grade mapFromDto(GradeCreateDto dto);

    GradeCreateDto mapToDto(Grade grade);

    GradeResponseDto mapFromDto(Grade grade);


}
