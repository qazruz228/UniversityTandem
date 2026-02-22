package com.example.teacherservice.dto;

import com.example.teacherservice.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentAverageGradeDto {

    private Long studentId;
    Subject subject;
    private double averageGrade;



}
