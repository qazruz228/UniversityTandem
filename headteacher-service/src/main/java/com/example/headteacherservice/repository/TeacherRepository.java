package com.example.headteacherservice.repository;

import com.example.headteacherservice.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    boolean existsTeacherByTeacherName(String teacherName);


    Optional<Teacher> findByTeacherNameAndTeacherSurname(String teacherName, String teacherSurname);
}
