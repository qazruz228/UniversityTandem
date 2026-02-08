package com.example.headteacherservice.docs.group;


import com.example.headteacherservice.dto.GroupDto;
import com.example.headteacherservice.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        tags = "Groups",
        summary = "Добавить группу",
        description = "Принимает данные группы, валидирует их и сохраняет в базе данных",
        requestBody = @RequestBody(
                description = "Данные создаваемой группы",
                required = true,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = GroupDto.class)
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Группа успешно создана",
                        content = @Content(
                                mediaType = MediaType.TEXT_PLAIN_VALUE,
                                schema = @Schema(implementation = String.class),
                                examples = @ExampleObject("Группа успешно создана")
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Ошибка формата запроса"
                ),
                @ApiResponse(
                        responseCode = "422",
                        description = "Ошибка валидации данных",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ErrorResponse.class)
                        )
                )
        }
)
public @interface CreateGroupDoc {
}


