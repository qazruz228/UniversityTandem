package com.example.headteacherservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeacherDto {

    @NotBlank(message = "name can't be empty")
    private String teacherName;

    @NotBlank(message = "surname can't be empty")
    private String teacherSurname;

    @Valid
    private SubjectDto subjectDto;

}
