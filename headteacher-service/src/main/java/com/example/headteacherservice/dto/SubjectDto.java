package com.example.headteacherservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class SubjectDto {

    @Schema(description = "Название предмета")
    @NotBlank(message = "Group name cannot be blank")
    private String subjectName;

}
