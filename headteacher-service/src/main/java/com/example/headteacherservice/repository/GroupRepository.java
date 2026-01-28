package com.example.headteacherservice.repository;

import com.example.headteacherservice.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

    void deleteByGroupName(String groupName);
}
