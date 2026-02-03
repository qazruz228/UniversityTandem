package com.example.headteacherservice.docs.schedule;


import com.example.headteacherservice.exception.ErrorResponse;
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
        tags = "Teachers",
        summary = "Найти расписание по группе и дню",
        description = "Возвращает расписание для определнной группы в определнный день",
        parameters = {
                @Parameter(
                        name = "groupName",
                        description = "название группы",
                        required = true,
                        example = "РЛ-22"
                ),
                @Parameter(
                        name = "weekDay",
                        description = "день недели",
                        required = true,
                        example = "MONDAY"
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "расписание найдено",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "расписание не найдено",
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
public @interface GetSByGroupAndWeekDoc {
}
