package com.example.headteacherservice.controller;

import com.example.headteacherservice.dto.ApiResponse;
import com.example.headteacherservice.dto.TeacherDto;
import com.example.headteacherservice.service.TeacherService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/teacher")
@RequiredArgsConstructor
public class TeacherController {

    private final TeacherService teacherService;


    @PostMapping("/createTeacher")
    public ResponseEntity<ApiResponse> createTeacher(@Valid @RequestBody TeacherDto teacherDto) {
        TeacherDto savedTeacher = teacherService.create(teacherDto);

        return ResponseEntity.ok(
                new ApiResponse(
                        LocalDateTime.now(),
                        "Учитель успешно создан",
                        savedTeacher));
    }

    @GetMapping("/getAllTeachers")
    public ResponseEntity<ApiResponse> getAllTeachers() {

        List<TeacherDto> teachers = teacherService.getAll();

        return ResponseEntity.ok(
                new ApiResponse(
                        LocalDateTime.now(),
                        "Список учителей",
                        teachers
                )
        );
    }


    @GetMapping("/getTeacher")
    public ResponseEntity<ApiResponse> getTeacherByName(@RequestParam String name,
                                                        @RequestParam String surname) {

        TeacherDto teacher = teacherService.getByNameAndSurname(name, surname);

        return ResponseEntity.ok(
                new ApiResponse(
                        LocalDateTime.now(),
                        "Учитель найден",
                        teacher));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateTeacher(@PathVariable Long id,
                                                     @Valid @RequestBody TeacherDto teacherDto) {

        TeacherDto updatedTeacher = teacherService.update(id, teacherDto);

        return ResponseEntity.ok(
                new ApiResponse(
                        LocalDateTime.now(),
                        "Учитель обновлён",
                        updatedTeacher));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteTeacher(@PathVariable Long id) {
        teacherService.delete(id);
        return ResponseEntity.ok(
                new ApiResponse(
                        LocalDateTime.now(),
                        "Учитель удалён",
                        null));
    }


}
