package com.example.teacherservice.docs;


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

        summary = "Обновить оценку",
        description = "Обновляет оценку студента",
        requestBody = @RequestBody(
                description = "Данные обновляемой оценки",
                required = true,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = GradeResponseDto.class)
                )
        ),

        responses = {
                @ApiResponse(
                responseCode = "201",
                description = "Оценка успешно обновлена",
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
public @interface UpdateGradeDoc {
}
