package com.example.headteacherservice.dto;

import com.example.headteacherservice.entity.enumType.Weekday;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ScheduleResponseDto {

    @Size(min = 1, max = 6)
    private int lessonOrder;

    private Weekday dayOfWeek;


    @Valid
    private GroupDto groupDto;

    @Valid
    private SubjectDto subjectDto;

    @Valid
    private TeacherDto teacherDto;




}
