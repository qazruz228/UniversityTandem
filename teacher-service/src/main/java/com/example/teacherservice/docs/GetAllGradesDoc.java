package com.example.teacherservice.docs;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        summary = "Получить все оценки студента за определенный предмет",
        description = "Возвращает все оценки студента по его id и названию предмета",
        parameters = {
                @Parameter(
                        name = "studentId",
                        description = "Id студента",
                        required = true,
                        example = "4"
                ),
                @Parameter(
                        name = "subject",
                        description = "учебный предмет",
                        required = true,
                        example = "MATH"
                )
        },

        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Оценки студента найдены"
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
public @interface GetAllGradesDoc {
}
