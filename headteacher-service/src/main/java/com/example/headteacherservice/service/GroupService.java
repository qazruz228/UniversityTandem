package com.example.headteacherservice.service;

import com.example.headteacherservice.dto.GroupDto;
import com.example.headteacherservice.entity.Group;
import com.example.headteacherservice.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public void createGroup(GroupDto GroupDto){
        Group group = new Group();
        group.setGroupName(GroupDto.getGroupName());
        groupRepository.save(group);

    }

    public void deleteGroup(String groupName){
        groupRepository.deleteByGroupName(groupName);
    }


}
