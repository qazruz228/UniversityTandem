package com.example.teacherservice.repository;

import com.example.teacherservice.entity.Grade;
import com.example.teacherservice.entity.Student;
import com.example.teacherservice.enums.Subject;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface GradeRepository extends JpaRepository<Grade, Long> {


    List<Grade> findAllByStudentIdAndDateId(Long studentId, Long dateId);

    Optional<Grade> findByStudentIdAndDateIdAndSubjectName(Long studentId, Long dateId, String subjectName);


    Boolean existsByStudentIdAndDateId(Long studentId, Long dateId);

    Grade getGradeByDateIdAndStudentId(Long dateId, Long studentId);

    Grade getGradeByStudent(Student student);

    List<Grade> getAllByStudentId(Long studentId);

    Subject getSubjectByStudentId(Long studentId);

    Optional<Grade> findByStudentIdAndDateId(Long studentId, Long dateId);


//    @Query(value = """
//            SELECT *
//            FROM grades
//            WHERE student_id = :studentId
//            AND date_id = :dateId
//            """,
//            nativeQuery = true)
//    List<Grade> findByStudentIdAndDateId(@Param("studentId") Long studentId, @Param("dateId") Long dateId
//    );
//
//    @Query(value = """
//            SELECT *
//            FROM grades
//            WHERE student_id = :studentId
//            AND date_id = :dateId
//            AND subject_name = :subject
//            """,
//            nativeQuery = true)
//    Optional<Grade> findByStudentAndDateAndSubject(Long studentId, Long dateId, String subject);


}
