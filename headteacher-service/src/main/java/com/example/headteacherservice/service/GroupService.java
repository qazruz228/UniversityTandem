package com.example.headteacherservice.service;

import com.example.headteacherservice.dto.GroupDto;
import com.example.headteacherservice.entity.Group;
import com.example.headteacherservice.exception.GroupNotFoundException;
import com.example.headteacherservice.mapper.GroupMapper;
import com.example.headteacherservice.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class GroupService {

    private final GroupRepository groupRepository;
    private final GroupMapper groupMapper;

    @Transactional
    public void createGroup(GroupDto groupDto) {
        Group group = groupMapper.toEntity(groupDto);
        groupRepository.save(group);
        log.info("Group created: {}", group.getGroupName());
    }

    @Transactional
    public void deleteGroup(String groupName) {
        Group group = groupRepository.findByGroupName(groupName)
                .orElseThrow(() -> {
                    log.warn("Attempt to delete non-existing group: {}", groupName);
                    return new GroupNotFoundException(
                            "Group with name '" + groupName + "' not found");
                });

        groupRepository.delete(group);
        log.info("Group deleted: {}", groupName);
    }
}
