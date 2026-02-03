package com.example.headteacherservice.service;

import com.example.headteacherservice.dto.GroupDto;
import com.example.headteacherservice.entity.Group;
import com.example.headteacherservice.exception.GroupNotFoundException;
import com.example.headteacherservice.mapper.GroupMapper;
import com.example.headteacherservice.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    @Transactional
    public void createGroup(GroupDto groupDto) {
        groupRepository.save(groupMapper.toEntity(groupDto));
    }

    @Transactional
    public void deleteGroup(String groupName) {
        Group group = groupRepository.findByGroupName(groupName)
                .orElseThrow(() -> new GroupNotFoundException(
                        "Group with name '" + groupName + "' not found"));
        groupRepository.delete(group);
    }
}
