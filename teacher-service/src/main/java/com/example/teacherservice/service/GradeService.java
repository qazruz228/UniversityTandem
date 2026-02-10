package com.example.teacherservice.service;

import com.example.teacherservice.dto.GradeCreateDto;
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


@Service
@RequiredArgsConstructor
public class GradeService {

    private final GradeRepository gradeRepository;
    private final GradeValidator  gradeValidator;

    private final GradeMapper gradeMapper;


    //    @Transactional
//    @PreAuthorize("@subjectValidator.canTeach(#dto.subject, authentication)")
//    public GradeResponseDto putGrade(GradeCreateDto dto) {
//
//        Group group = groupRepository.findById(dto.getGroupId())
//                .orElseThrow(() -> new ResourceNotFoundException("Группа не найдена"));
//
//        Student student = studentRepository.findById(dto.getStudentId())
//                .orElseThrow(() -> new ResourceNotFoundException("Студент не найден"));
//
//        if (!student.getGroup().getId().equals(group.getId())) {
//            throw new BusinessValidationException("Студент не состоит в данной группе");
//        }
//
//        boolean isWorking = calendarDateRepository.dayIsWorking(dto.getDateId());
//
//        if (!isWorking) {
//            throw new DayIsNotWorkingException("Это выходной "+ dto.getDateId());
//        }
//
//        Grade grade = gradeMapper.mapFromDto(dto);
//
//            Grade saved = gradeRepository.save(grade);
//            return gradeMapper.mapFromDto(saved);
//    }
    @Transactional
    @PreAuthorize("@subjectValidator.canTeach(#dto.subject, authentication)")
    public GradeResponseDto putGrade(GradeCreateDto dto) {

        Group group = gradeValidator.getGroupOrThrow(dto.getGroupId());
        Student student = gradeValidator.getStudentOrThrow(dto.getStudentId());

        gradeValidator.validateStudentBelongsToGroup(student, group);
        gradeValidator.validateWorkingDay(dto.getDateId());
        gradeValidator.validateGradeNotExists(dto.getStudentId(), dto.getDateId());

        Grade grade = gradeMapper.mapFromDto(dto);

        Grade saved = gradeRepository.save(grade);
        return gradeMapper.mapFromDto(saved);
    }





}









