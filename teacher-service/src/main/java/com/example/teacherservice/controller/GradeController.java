package com.example.teacherservice.controller;

import com.example.teacherservice.dto.GradeRequestDto;
import com.example.teacherservice.dto.GradeResponseDto;
import com.example.teacherservice.service.GradeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/grades")
@RequiredArgsConstructor
public class GradeController {

    private final GradeService gradeService;


    @PostMapping("/createGrade")
    public ResponseEntity<GradeResponseDto> createGrade(@Valid @RequestBody GradeRequestDto dto) {

        GradeResponseDto response = gradeService.putGrade(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }



    @GetMapping("/getGrade/{studentId}/{dateId}")
    public ResponseEntity<GradeResponseDto> getGradeByDay(@PathVariable Long studentId,
                                                          @PathVariable Long dateId) {

        GradeResponseDto response = gradeService.getGradeByDay(dateId, studentId);

        return ResponseEntity.ok(response);
    }



    @GetMapping("/getGrades/{studentId}")
    public ResponseEntity<List<GradeResponseDto>> getGradesByStudent(@PathVariable Long studentId) {

        List<GradeResponseDto> response = gradeService.getGradesByStudent(studentId);

        return ResponseEntity.ok(response);
    }



    @PutMapping("/updateGrade")
    public ResponseEntity<Void> updateGrade(@Valid @RequestBody GradeRequestDto dto) {

        gradeService.updateGrade(dto);

        return ResponseEntity.noContent().build();
    }
}