package com.example.headteacherservice.docs.subject;


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
        tags = "Subjects",
        summary = "Удалить предмет",
        description = "Удаляет группу по ее названи",
        parameters = {
                @Parameter(
                        name = "subjectName",
                        description = "Название предмета",
                        required = true,
                        example = "Math"
                )
        },
        responses = {

                @ApiResponse(
                        responseCode = "204",
                        description = "Предмет успешно удалена"
                ),
                @ApiResponse(
                        responseCode = "404",
                        description = "Предмет не найден",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ErrorResponse.class)
                        )

                ),
                @ApiResponse(
                        responseCode = "400",
                        description = "Некорректное название предмета",
                        content = @Content(
                                mediaType = MediaType.APPLICATION_JSON_VALUE,
                                schema = @Schema(implementation = ErrorResponse.class)
                        )
                )
        }
)


public @interface DeleteSubjectDoc {
}
