package com.example.headteacherservice.controller;

import com.example.headteacherservice.dto.SubjectDto;
import com.example.headteacherservice.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/subjectApi")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("/create")
    public ResponseEntity<Void> createSubject(@Valid @RequestBody SubjectDto subjectDto) {
        subjectService.createSubject(subjectDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteGroup(@Valid @RequestBody SubjectDto subjectDto) {
        subjectService.deleteGroup(subjectDto);
        return ResponseEntity.ok().build();
    }

}
