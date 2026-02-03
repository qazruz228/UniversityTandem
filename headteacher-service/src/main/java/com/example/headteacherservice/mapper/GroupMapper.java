package com.example.headteacherservice.mapper;

import com.example.headteacherservice.dto.GroupDto;
import com.example.headteacherservice.entity.Group;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDto toDto(Group group);

    Group toEntity(GroupDto groupDtoroup);

}
