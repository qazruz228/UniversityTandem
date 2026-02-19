package com.example.studentservice.client;

import com.example.studentservice.dto.GradeResponseDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "teacher-service", url = "http://localhost:10001")
public interface TeacherClient {

    @GetMapping("/getGrade/{studentId}/{dateId}")
    ResponseEntity<GradeResponseDto> getGradeByDay(@PathVariable Long studentId,
                                                   @PathVariable Long dateId);

    @GetMapping("/getGrades/{studentId}/{subject}")
    ResponseEntity<List<GradeResponseDto>> getAllGrades(@PathVariable Long studentId,
                                                        @PathVariable String subject);
}










