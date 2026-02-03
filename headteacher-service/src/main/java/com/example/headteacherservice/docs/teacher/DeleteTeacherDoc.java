package com.example.headteacherservice.docs.teacher;

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
        summary = "Удалить учителя",
        description = "Удаляет учителя по идентификатору",
        parameters = {
                @Parameter(
                        name = "id",
                        description = "Идентификатор учителя",
                        required = true,
                        example = "1"
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "200",
                        description = "Учитель успешно удалён",
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
                        responseCode = "401",
                        description = "Не авторизован"
                ),
                @ApiResponse(
                        responseCode = "403",
                        description = "Нет прав доступа"
                )
        }
)
public @interface DeleteTeacherDoc {
}
