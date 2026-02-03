package com.example.headteacherservice.controller;

import com.example.headteacherservice.docs.group.CreateGroupDoc;
import com.example.headteacherservice.docs.group.DeleteGroupDoc;
import com.example.headteacherservice.dto.GroupDto;
import com.example.headteacherservice.service.GroupService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Groups", description = "Операции с группами")
@RestController
@RequestMapping("/api/group")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @CreateGroupDoc
    @PostMapping("/create")
    public ResponseEntity<String> createGroup(@Valid @RequestBody GroupDto groupDto) {
        groupService.createGroup(groupDto);
        String addedGroup = "Группа успешно создана";
        return ResponseEntity.status(HttpStatus.CREATED).body(addedGroup);
    }

    @DeleteGroupDoc
    @DeleteMapping("/{groupName}")
    public ResponseEntity<Void> deleteGroup(@PathVariable String groupName) {
        groupService.deleteGroup(groupName);
        return ResponseEntity.ok().build();
    }
}


