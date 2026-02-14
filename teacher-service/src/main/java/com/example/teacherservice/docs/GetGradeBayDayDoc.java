package com.example.teacherservice.docs;


import com.example.teacherservice.dto.GradeResponseDto;
import com.example.teacherservice.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Получить оценку",
        description = "Получить оценку по studentId и dateId",
        parameters = {

                @Parameter(
                        name = "studentId",
                        description = "Id студента",
                        required = true,
                        example = "4"
                ),

                @Parameter(
                        name = "dateId",
                        description = "Id дня месяца",
                        required = true,
                        example = "4"
                )

        },
        responses = {

                @ApiResponse(
                        responseCode = "200",
                        description = "Оценка за этот день найдена",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = GradeResponseDto.class)
                        )
                ),

                @ApiResponse(
                        responseCode = "404",
                        description = "Студент не получал оценку в этот день",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ErrorResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Некорректные параметры запроса"
                ),
                @ApiResponse(
                        responseCode = "401",
                        description = "Не авторизован"
                ),
                @ApiResponse(
                        responseCode = "403",
                        description = "Нет прав доступа"
                )
        }
)

public @interface GetGradeBayDayDoc {
}

