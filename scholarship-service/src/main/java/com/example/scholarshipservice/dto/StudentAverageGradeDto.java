package com.example.scholarshipservice.dto;

import com.example.scholarshipservice.dto.enums.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class StudentAverageGradeDto {

    private String studentId;
    Subject subject;
    private double averageGrade;



}
