package com.example.headteacherservice.docs.schedule;

import com.example.headteacherservice.dto.ScheduleResponseDto;
import com.example.headteacherservice.dto.TeacherDto;
import com.example.headteacherservice.exception.ErrorResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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
        tags = "Schedules",
        summary = "Обновить данные расписания",
        description = "Обновляет данные расписания по идентификатору",
        parameters = {
                @Parameter(
                        name = "id",
                        description = "Идентификатор расписания",
                        required = true,
                        example = "1"
                )
        },
        requestBody = @RequestBody(
                description = "Новые данные расписания",
                required = true,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ScheduleResponseDto.class)
                )
        ),
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "расписание успешно обновлено",
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
                        responseCode = "422",
                        description = "Ошибка валидации данных",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ErrorResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Некорректный запрос"
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
public @interface UpdateScheduleDoc {
}
