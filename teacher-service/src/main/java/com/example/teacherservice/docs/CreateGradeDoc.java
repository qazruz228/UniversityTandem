package com.example.teacherservice.docs;


import com.example.teacherservice.dto.GradeRequestDto;
import com.example.teacherservice.dto.GradeResponseDto;
import com.example.teacherservice.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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
        summary = "Добавить оценку",
        description = "принимает GradeRequestDto, валидирует, добавляет в бд" +
                " и отправляет сообщение в кафку",
        requestBody = @RequestBody(
                description = "Данные добавляемой оценки",
                required = true,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = GradeRequestDto.class)
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "201",
                        description = "Группа успешно создана",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = GradeResponseDto.class)
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
public @interface CreateGradeDoc {
}




