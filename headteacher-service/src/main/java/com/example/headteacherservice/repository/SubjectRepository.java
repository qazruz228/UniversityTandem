package com.example.headteacherservice.repository;

import com.example.headteacherservice.entity.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {

    void deleteBySubjectName(String subjectName);
}
