package com.example.teacherservice.service;

import com.example.teacherservice.dto.GradeRequestDto;
import com.example.teacherservice.dto.GradeResponseDto;
import com.example.teacherservice.entity.Grade;
import com.example.teacherservice.entity.Group;
import com.example.teacherservice.entity.Student;
import com.example.teacherservice.mapper.GradeMapper;
import com.example.teacherservice.repository.GradeRepository;
import com.example.teacherservice.validator.GradeValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final GradeValidator gradeValidator;
    private final GradeMapper gradeMapper;

    @Transactional
    @PreAuthorize("@subjectValidator.canTeach(#dto.subject, authentication)")
    public GradeResponseDto putGrade(GradeRequestDto dto) {

        validateGrade(dto);

        Grade grade = gradeMapper.mapFromDto(dto);

        Grade saved = gradeRepository.save(grade);
        return gradeMapper.mapFromEntity(saved);
    }


    @Transactional(readOnly = true)
    @PreAuthorize("@subjectValidator.canTeach(#studentId, authentication)")
    public GradeResponseDto getGradeByDay(Long dateId, Long studentId) {

        Grade grade = gradeRepository.getGradeByDateIdAndStudentId(dateId, studentId);
        return gradeMapper.mapFromEntity(grade);

    }


    @Transactional(readOnly = true)
    @PreAuthorize("@subjectValidator.canTeach(#studentId, authentication)")
    public List<GradeResponseDto> getGradesByStudent(Long studentId) {

        List<Grade> grades = gradeRepository.getAllByStudentId(studentId);

        return grades.stream()
                .map(gradeMapper::mapFromEntity)
                .toList();
    }

    @Transactional
    @PreAuthorize("@subjectValidator.canTeach(#dto.subject, authentication)")
    public void updateGrade(GradeRequestDto dto) {

        Grade grade = gradeValidator.getExistingGradeOrThrow(dto.getStudentId(), dto.getDateId());

        gradeMapper.updateEntityFromDto(dto, grade);

        gradeRepository.save(grade);
    }


    public void validateGrade(GradeRequestDto dto) {

        Group group = gradeValidator.getGroupOrThrow(dto.getGroupId());
        Student student = gradeValidator.getStudentOrThrow(dto.getStudentId());

        gradeValidator.validateStudentBelongsToGroup(student, group);
        gradeValidator.validateWorkingDay(dto.getDateId());
        gradeValidator.validateGradeNotExists(dto.getStudentId(), dto.getDateId());

    }


}









