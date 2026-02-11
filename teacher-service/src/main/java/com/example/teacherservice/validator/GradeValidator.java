package com.example.teacherservice.validator;

import com.example.teacherservice.entity.Grade;
import com.example.teacherservice.entity.Group;
import com.example.teacherservice.entity.Student;
import com.example.teacherservice.exception.BusinessValidationException;
import com.example.teacherservice.exception.DayIsNotWorkingException;
import com.example.teacherservice.exception.GradeAlreadyExistsException;
import com.example.teacherservice.repository.CalendarDateRepository;
import com.example.teacherservice.repository.GradeRepository;
import com.example.teacherservice.repository.GroupRepository;
import com.example.teacherservice.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GradeValidator {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final CalendarDateRepository calendarDateRepository;
    private final GradeRepository gradeRepository;

    public Group getGroupOrThrow(Long groupId) {
        return groupRepository.findById(groupId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Группа не найдена: " + groupId));
    }

    public Student getStudentOrThrow(Long studentId) {
        return studentRepository.findById(studentId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Студент не найден: " + studentId));
    }

    public void validateStudentBelongsToGroup(Student student, Group group) {
        if (!student.getGroup().getId().equals(group.getId())) {
            throw new BusinessValidationException(
                    "Студент не состоит в группе " + group.getGroupName()
            );
        }
    }

    public void validateWorkingDay(Long dateId) {
        boolean isWorking = calendarDateRepository.dayIsWorking(dateId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Дата не найдена: " + dateId));

        if (!isWorking) {
            throw new DayIsNotWorkingException("Это выходной "+ dateId);
        }
    }

    public void validateGradeNotExists(Long studentId, Long dateId) {
        if (gradeRepository.existsByStudentIdAndDateId(studentId, dateId)) {
            throw new GradeAlreadyExistsException("Оценка за этот день уже стоит");
        }

    }


    public Grade getExistingGradeOrThrow(Long studentId, Long dateId) {

        return gradeRepository
                .findByStudentIdAndDateId(studentId, dateId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Grade not found"));
    }



}