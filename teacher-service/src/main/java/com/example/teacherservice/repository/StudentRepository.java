package com.example.teacherservice.repository;

import com.example.teacherservice.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student,Long> {
}
