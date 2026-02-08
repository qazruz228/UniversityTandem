package com.example.headteacherservice.repository;

import com.example.headteacherservice.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {

    void deleteByGroupName(String groupName);

    Optional<Group> findByGroupName(String groupName);
}
