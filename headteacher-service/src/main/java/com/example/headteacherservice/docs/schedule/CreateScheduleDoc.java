package com.example.headteacherservice.docs.schedule;

import com.example.headteacherservice.dto.ScheduleResponseDto;
import com.example.headteacherservice.entity.Schedule;
import com.example.headteacherservice.exception.ErrorResponse;
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
import java.lang.reflect.Type;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Operation(
        tags = "Schedules",
        summary = "Добавить расписание",
        description = "Добавить расписание опредленной группе",
        requestBody = @RequestBody(
                description = "Данные создаваемого расписания",
                required = true,
                content = @Content(
                        mediaType = MediaType.APPLICATION_JSON_VALUE,
                        schema = @Schema(implementation = ScheduleResponseDto.class)
                )
        ),
        responses = {

                @ApiResponse(
                        responseCode = "201",
                        description = "группа успешно добавлена",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema =  @Schema(implementation = ApiResponse.class)
                        )
                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Некорректный формат запроса"
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
                        responseCode = "401",
                        description = "Не авторизован"
                ),
                @ApiResponse(
                        responseCode = "403",
                        description = "Нет прав доступа"
                )
        }
)
public @interface CreateScheduleDoc {
}
