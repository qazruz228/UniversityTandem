package com.example.headteacherservice.controller;

import com.example.headteacherservice.docs.subject.CreateSubjectDoc;
import com.example.headteacherservice.dto.SubjectDto;
import com.example.headteacherservice.service.SubjectService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subject")
@RequiredArgsConstructor
public class SubjectController {

    private final SubjectService subjectService;

    @CreateSubjectDoc
    @PostMapping("/create")
    public ResponseEntity<String> createSubject(@Valid @RequestBody SubjectDto subjectDto) {
        subjectService.createSubject(subjectDto);
        String addedSubject = "Предмет успешно добавлен";
        return ResponseEntity.status(HttpStatus.CREATED).body(addedSubject);
    }

    @DeleteMapping("/{subjectName}")
    public ResponseEntity<Void> deleteSubject(@PathVariable String subjectName) {
        subjectService.deleteGroup(subjectName);
        return ResponseEntity.ok().build();
    }

}
