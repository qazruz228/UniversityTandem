package com.example.teacherservice.validator;

import com.example.teacherservice.entity.CalendarDate;
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
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class GradeValidator {

    private final GroupRepository groupRepository;
    private final StudentRepository studentRepository;
    private final CalendarDateRepository calendarDateRepository;
    private final GradeRepository gradeRepository;

    public Group getGroupOrThrow(Long groupId) {
        log.debug("Поиск группы с id={}", groupId);

        return groupRepository.findById(groupId)
                .orElseThrow(() -> {
                    log.error("Группа не найдена: id={}", groupId);
                    return new ResourceNotFoundException("Группа не найдена: " + groupId);
                });
    }

    public Student getStudentOrThrow(Long studentId) {
        log.debug("Поиск студента с id={}", studentId);

        return studentRepository.findById(studentId)
                .orElseThrow(() -> {
                    log.error("Студент не найден: id={}", studentId);
                    return new ResourceNotFoundException("Студент не найден: " + studentId);
                });
    }

    public void validateStudentBelongsToGroup(Student student, Group group) {
        log.debug("Проверка принадлежности студента id={} к группе id={}",
                student.getId(), group.getId());

        if (!student.getGroup().getId().equals(group.getId())) {
            log.warn("Студент с id={} не состоит в группе с id={}",
                    student.getId(), group.getId());

            throw new BusinessValidationException(
                    "Студент не состоит в группе " + group.getGroupName()
            );
        }
    }

    public void validateWorkingDay(Long dateId) {
        log.debug("Проверка рабочего дня для dateId={}", dateId);

        CalendarDate date = calendarDateRepository.findById(dateId)
                .orElseThrow(() -> {
                    log.error("Дата не найдена: id={}", dateId);
                    return new ResourceNotFoundException("Дата не найдена: " + dateId);
                });

        if (!date.isWorking()) {
            log.warn("Дата id={} является выходным днем", dateId);
            throw new DayIsNotWorkingException("Это выходной " + dateId);
        }
    }

    public void validateGradeNotExists(Long studentId, Long dateId) {
        log.debug("Проверка существования оценки studentId={}, dateId={}",
                studentId, dateId);

        if (gradeRepository.existsByStudentIdAndDateId(studentId, dateId)) {
            log.warn("Оценка уже существует: studentId={}, dateId={}",
                    studentId, dateId);

            throw new GradeAlreadyExistsException("Оценка за этот день уже стоит");
        }
    }

    public Grade getExistingGradeOrThrow(Long studentId, Long dateId) {
        log.debug("Получение оценки studentId={}, dateId={}",
                studentId, dateId);

        return gradeRepository
                .findByStudentIdAndDateId(studentId, dateId)
                .orElseThrow(() -> {
                    log.error("Оценка не найдена: studentId={}, dateId={}",
                            studentId, dateId);
                    return new ResourceNotFoundException("Grade not found");
                });
    }
}
