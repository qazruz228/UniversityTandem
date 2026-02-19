package com.example.studentservice.controller;

import com.example.studentservice.client.HeadTeacherClient;
import com.example.studentservice.client.TeacherClient;
import com.example.studentservice.dto.ApiResponse;
import com.example.studentservice.dto.GradeResponseDto;
import com.example.studentservice.enums.Weekday;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final TeacherClient teacherClient;
    private final HeadTeacherClient headTeacherClient;

    @GetMapping("/grades/{subject}")
    public ResponseEntity<List<GradeResponseDto>> getAllGrades(@AuthenticationPrincipal JwtAuthenticationToken jwt,
                                                               @PathVariable String subject) {

        Long studentId = Long.valueOf(jwt.getTokenAttributes().get("studentId").toString());

        List<GradeResponseDto> grades = teacherClient.getAllGrades(studentId, subject).getBody();

        return ResponseEntity.ok(grades);
    }

    @GetMapping("/grades/day/{dateId}")
    public ResponseEntity<GradeResponseDto> getGradeByDay(@AuthenticationPrincipal JwtAuthenticationToken jwt,
                                                          @PathVariable Long dateId) {

        Long studentId = Long.valueOf(jwt.getTokenAttributes().get("studentId").toString());

        GradeResponseDto grade = teacherClient.getGradeByDay(studentId, dateId).getBody();

        return ResponseEntity.ok(grade);
    }


    @GetMapping("/{groupName}")
    ResponseEntity<ApiResponse> getScheduleByGroupAndWeek(@AuthenticationPrincipal JwtAuthenticationToken jwt,
                                                       @RequestParam(required = false) Weekday dayOfWeek) {

        String groupName = jwt.getTokenAttributes().get("group").toString();

        return ResponseEntity.ok(
                new ApiResponse(
                        LocalDateTime.now(),
                        "Schedule found",
                        headTeacherClient.getScheduleByGroupName(groupName, dayOfWeek)));
    }


    @GetMapping("/getScheduleByGroup")
    ResponseEntity<ApiResponse> getScheduleByGroup(@AuthenticationPrincipal JwtAuthenticationToken jwt) {

        String groupName = jwt.getTokenAttributes().get("group").toString();

        return ResponseEntity.ok(
                new ApiResponse(
                        LocalDateTime.now(),
                        "Schedule found",
                        headTeacherClient.getScheduleByGroup(groupName)));
    }

    }


