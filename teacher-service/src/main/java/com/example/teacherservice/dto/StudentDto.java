package com.example.teacherservice.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDto {

    @NotBlank(message = "Student name must not be blank")
    private String studentName;

    @NotBlank(message = "Student surname must not be blank")
    private String studentSurname;

    @NotNull(message = "Group id must not be null")
    private Long groupId;


}
