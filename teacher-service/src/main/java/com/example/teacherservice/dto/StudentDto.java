package com.example.teacherservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class StudentDto {

    @Schema(description = "Имя студента")
    @NotBlank(message = "Student name must not be blank")
    private String studentName;

    @Schema(description = "Фамилия студента")
    @NotBlank(message = "Student surname must not be blank")
    private String studentSurname;

    @Schema(description = "Id группы")
    @NotNull(message = "Group id must not be null")
    private Long groupId;


}
