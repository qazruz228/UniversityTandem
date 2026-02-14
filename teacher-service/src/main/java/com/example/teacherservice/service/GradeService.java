package com.example.teacherservice.service;

import com.example.teacherservice.dto.GradeRequestDto;
import com.example.teacherservice.dto.GradeResponseDto;
import com.example.teacherservice.entity.Grade;
import com.example.teacherservice.entity.Group;
import com.example.teacherservice.entity.Student;
import com.example.teacherservice.enums.EventType;
import com.example.teacherservice.enums.Subject;
import com.example.teacherservice.events.GradeEvent;
import com.example.teacherservice.mapper.GradeMapper;
import com.example.teacherservice.repository.GradeRepository;
import com.example.teacherservice.validator.GradeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final OutboxPublisher outboxPublisher;
    private final GradeValidator gradeValidator;
    private final GradeMapper gradeMapper;

    @Transactional
    @PreAuthorize("@subjectValidator.canTeach(#dto.subject, authentication)")
    public GradeResponseDto putGrade(GradeRequestDto dto) {

        log.info("Создание оценки для студента с id={} ", dto.getStudentId());

        validateGrade(dto);

        Grade grade = gradeMapper.mapFromDto(dto);
        Grade saved = gradeRepository.save(grade);

        log.debug("Оценка сохранена в БД: id={}", saved.getId());

        GradeEvent gradeEvent = GradeEvent.builder()
                .studentId(saved.getStudent().getId())
                .studentEmail(saved.getStudent().getEmail())
                .subject(saved.getSubject().name())
                .calendarDate(saved.getDate().getDate())
                .gradeValue(saved.getGrade())
                .eventType(EventType.CREATED)
                .build();

        outboxPublisher.saveEvent(gradeEvent);

        log.info("Событие CREATED отправлено в outbox для студента с id={}",
                saved.getStudent().getId());

        return gradeMapper.mapFromEntity(saved);
    }

    @Transactional
    @PreAuthorize("@subjectValidator.canTeach(#dto.subject, authentication)")
    public void updateGrade(GradeRequestDto dto) {

        log.info("Обновление оценки для студента с id={} ", dto.getStudentId());

        Grade grade = gradeValidator
                .getExistingGradeOrThrow(dto.getStudentId(), dto.getDateId());

        Grade saved = gradeMapper.updateEntityFromDto(dto, grade);
        gradeRepository.save(saved);

        log.debug("Оценка обновлена в БД: id={}", saved.getId());

        GradeEvent gradeEvent = GradeEvent.builder()
                .id(UUID.randomUUID())
                .studentId(saved.getStudent().getId())
                .studentEmail(saved.getStudent().getEmail())
                .subject(saved.getSubject().name())
                .calendarDate(saved.getDate().getDate())
                .gradeValue(saved.getGrade())
                .eventType(EventType.UPDATED)
                .build();

        outboxPublisher.saveEvent(gradeEvent);

        log.info("Событие UPDATED отправлено в outbox для studentId={}", saved.getStudent().getId());
    }

    @Transactional(readOnly = true)
    @PreAuthorize("@subjectValidator.canTeach(#studentId, authentication)")
    public GradeResponseDto getGradeByDay(Long dateId, Long studentId) {

        log.debug("Получение оценки за день: studentId={}, dateId={}", studentId, dateId);

        Grade grade = gradeRepository
                .getGradeByDateIdAndStudentId(dateId, studentId);

        return gradeMapper.mapFromEntity(grade);
    }

    @Transactional(readOnly = true)
    @PreAuthorize("@subjectValidator.canTeach(#studentId, authentication)")
    public List<GradeResponseDto> getGradesByStudentAndSubject(Long studentId, Subject subject) {

        log.debug("Получение оценок студента: studentId={}, subject={}",
                studentId, subject);

        List<Grade> grades =
                gradeRepository.getAllByStudentIdAndSubject(studentId, subject);

        log.debug("Найдено {} оценок для studentId={}", grades.size(), studentId);

        return grades.stream()
                .map(gradeMapper::mapFromEntity)
                .toList();
    }

    public void validateGrade(GradeRequestDto dto) {

        log.info("Валидация оценки");

        Group group = gradeValidator.getGroupOrThrow(dto.getGroupId());
        Student student = gradeValidator.getStudentOrThrow(dto.getStudentId());

        gradeValidator.validateStudentBelongsToGroup(student, group);
        gradeValidator.validateWorkingDay(dto.getDateId());
        gradeValidator.validateGradeNotExists(dto.getStudentId(), dto.getDateId());
    }
}









