package com.example.headteacherservice.docs.group;

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
        tags = "Groups",
        summary = "Удалить группу",
        description = "Удаляет группу по её названию",
        parameters = {
                @Parameter(
                        name = "groupName",
                        description = "Название группы",
                        required = true,
                        example = "РЛ-22"
                )
        },
        responses = {
                @ApiResponse(
                        responseCode = "204",
                        description = "Группа успешно удалена"
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Группа не найдена",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ErrorResponse.class)
                        )
                ),
               @ApiResponse(
                        responseCode = "400",
                        description = "Некорректное название группы",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ErrorResponse.class)
                        )
                )
        }
)
public @interface DeleteGroupDoc {
}
