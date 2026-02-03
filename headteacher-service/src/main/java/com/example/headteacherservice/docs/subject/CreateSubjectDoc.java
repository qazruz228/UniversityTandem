package com.example.headteacherservice.docs.subject;


import com.example.headteacherservice.dto.SubjectDto;
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
        tags = "Subjects",
        summary = "Добавить предмет",
        description = "Принимает данные предмета, валидирует их и сохраняте в бд",
        requestBody = @RequestBody(
                description = "Данные создаваемой группы",
                required = true,
                content = @Content(
                        schema = @Schema(implementation = SubjectDto.class),
                        mediaType = MediaType.APPLICATION_JSON_VALUE
                )
        ), responses = {

        @ApiResponse(
                responseCode = "200",
                description = "Предмет успешно добавлен",
                content = @Content(
                        schema = @Schema(implementation = String.class),
                        mediaType = MediaType.TEXT_PLAIN_VALUE,
                        examples = @ExampleObject(value = "Предмет успешно добавлен")
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
public @interface CreateSubjectDoc {
}
