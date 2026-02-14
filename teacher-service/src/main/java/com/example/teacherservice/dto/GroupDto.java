package com.example.teacherservice.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GroupDto {

    @Schema(description = "название группы")
    @NotBlank
    private String groupName;


}
