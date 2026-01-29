package com.example.headteacherservice.controller;

import com.example.headteacherservice.dto.GroupDto;
import com.example.headteacherservice.service.GroupService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping("/create")
    public ResponseEntity<Void> createGroup(@Valid @RequestBody GroupDto groupDto) {
        groupService.createGroup(groupDto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete")
    public ResponseEntity<Void> deleteGroup(@Valid @RequestBody GroupDto groupDto) {
        groupService.deleteGroup(groupDto);
        return ResponseEntity.ok().build();
    }

}
