package com.example.teacherservice.controller;

import com.example.teacherservice.docs.CreateGradeDoc;
import com.example.teacherservice.docs.GetAllGradesDoc;
import com.example.teacherservice.docs.GetGradeBayDayDoc;
import com.example.teacherservice.docs.UpdateGradeDoc;
import com.example.teacherservice.dto.GradeRequestDto;
import com.example.teacherservice.dto.GradeResponseDto;
import com.example.teacherservice.dto.StudentAverageGradeDto;
import com.example.teacherservice.enums.Subject;
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


    @CreateGradeDoc
    @PostMapping("/createGrade")
    public ResponseEntity<GradeResponseDto> createGrade(@Valid @RequestBody GradeRequestDto dto) {

        GradeResponseDto response = gradeService.putGrade(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @GetGradeBayDayDoc
    @GetMapping("/getGrade/{studentId}/{dateId}")
    public ResponseEntity<GradeResponseDto> getGradeByDay(@PathVariable Long studentId,
                                                          @PathVariable Long dateId) {

        GradeResponseDto response = gradeService.getGradeByDay(dateId, studentId);

        return ResponseEntity.ok(response);
    }


    @GetAllGradesDoc
    @GetMapping("/getGrades/{studentId}/{subject}")
    public ResponseEntity<List<GradeResponseDto>> getAllGrades(@PathVariable Long studentId,
                                                                     @PathVariable Subject subject) {

        List<GradeResponseDto> response = gradeService.getGradesByStudentAndSubject(studentId, subject);

        return ResponseEntity.ok(response);
    }



    @GetMapping("/getAverageGrades")
    public ResponseEntity<List<StudentAverageGradeDto>> getAverageGrades() {

        return ResponseEntity.ok().body(gradeService.getAverageGradesOfAllStudents());

    }



    @UpdateGradeDoc
    @PutMapping("/updateGrade")
    public ResponseEntity<Void> updateGrade(@Valid @RequestBody GradeRequestDto dto) {

        gradeService.updateGrade(dto);

        return ResponseEntity.noContent().build();
    }
}