package com.example.teacherservice.repository;

import com.example.teacherservice.dto.StudentAverageGradeDto;
import com.example.teacherservice.entity.Grade;
import com.example.teacherservice.enums.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {

    Boolean existsByStudentIdAndDateId(Long studentId, Long dateId);

    Grade getGradeByDateIdAndStudentId(Long dateId, Long studentId);

    List<Grade> getAllByStudentIdAndSubject(Long studentId, Subject subject);

    Subject getSubjectByStudentId(Long studentId);

    Optional<Grade> findByStudentIdAndDateId(Long studentId, Long dateId);


    @Query("""
           SELECT new com.example.teacherservice.dto.StudentAverageGradeDto(
               g.student.id,
               g.subject,
               AVG(g.grade)
           )
           FROM Grade g
           GROUP BY g.student.id, g.subject
           """)
    List<StudentAverageGradeDto> findAverageGradeByStudentAndSubject();

}