package com.example.headteacherservice.docs.teacher;


import com.example.headteacherservice.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.http.MediaType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        tags = "Teachers",
        summary = "Найти учителя по имени и фамилии",
        description = "Возвращает учителя по имени и фамилии",
        parameters = {
                @Parameter(
                        name = "name",
                        description = "Имя учителя",
                        required = true,
                        example = "Роман"
                ),
                @Parameter(
                        name = "surname",
                        description = "Фамилия учителя",
                        required = true,
                        example = "Куприянов"
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Учитель найден",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Учитель не найден",
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
public @interface GetTeacherByNameDoc {
}
