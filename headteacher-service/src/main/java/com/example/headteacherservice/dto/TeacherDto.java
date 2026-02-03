package com.example.headteacherservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TeacherDto {

    @Schema(description = "Имя учителя", example = "Роман")
    @NotBlank(message = "name can't be empty")
    private String teacherName;

    @Schema(description = "Фамилия учителя", example = "Куприянов")
    @NotBlank(message = "surname can't be empty")
    private String teacherSurname;

    @Schema(description = "Информация о преподаваемом предмете")
    @Valid
    private SubjectDto subjectDto;



}
