package com.example.headteacherservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GroupDto {

    @NotBlank(message = "Group name cannot be blank")
    private String groupName;

}
