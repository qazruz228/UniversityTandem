package com.example.teacherservice.repository;

import com.example.teacherservice.entity.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Group,Long> {

}
