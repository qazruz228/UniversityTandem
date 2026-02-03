package com.example.headteacherservice.controller;

import com.example.headteacherservice.docs.schedule.*;
import com.example.headteacherservice.docs.teacher.CreateTeacherDoc;
import com.example.headteacherservice.dto.ApiResponse;
import com.example.headteacherservice.dto.ScheduleCreateOrUpdateDto;
import com.example.headteacherservice.dto.ScheduleResponseDto;
import com.example.headteacherservice.entity.enumType.Weekday;
import com.example.headteacherservice.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/schedule")
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @CreateScheduleDoc
    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createSchedule(@Valid @RequestBody ScheduleCreateOrUpdateDto dto) {
        ScheduleResponseDto created = scheduleService.createSchedule(dto);

        return ResponseEntity.status(HttpStatus.CREATED).body(
                new ApiResponse(
                        LocalDateTime.now(),
                        "Schedule created successfully",
                        created));
    }

    @UpdateScheduleDoc
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateSchedule(@PathVariable Long id,
                                                      @Valid @RequestBody ScheduleCreateOrUpdateDto dto) {
        ScheduleResponseDto updated = scheduleService.updateSchedule(id, dto);

        return ResponseEntity.ok(
                new ApiResponse(
                        LocalDateTime.now(),
                        "Schedule updated successfully",
                        updated));
    }

    @GetScheduleByGroupAndWeekDoc
    @GetMapping("/{groupName}")
    public ResponseEntity<ApiResponse> getScheduleByGroupAndWeek(@PathVariable String groupName,
                                                                 @RequestParam(required = false) Weekday dayOfWeek) {
        return ResponseEntity.ok(
                new ApiResponse(
                        LocalDateTime.now(),
                        "Schedule found",
                        scheduleService.getByGroupNameAndWeek(groupName, dayOfWeek)));
    }

    @GetScheduleByGroupDoc
    @GetMapping("/getScheduleByGroup")
    public ResponseEntity<ApiResponse> getScheduleByGroup(@RequestParam String groupName) {
        return ResponseEntity.ok(
                new ApiResponse(
                        LocalDateTime.now(),
                        "Schedule found",
                        scheduleService.getByGroupName(groupName)));
    }

    @DeleteScheduleDoc
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteSchedule(@PathVariable Long id) {
        scheduleService.delete(id);

        return ResponseEntity.ok(
                new ApiResponse(
                        LocalDateTime.now(),
                        "Schedule deleted successfully",
                        null
                )
        );
    }
}
