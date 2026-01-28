package com.example.headteacherservice.service;

import com.example.headteacherservice.dto.TeacherDto;
import com.example.headteacherservice.entity.Teacher;
import com.example.headteacherservice.exception.TeacherAlreadyExistException;
import com.example.headteacherservice.exception.TeacherNotFoundException;
import com.example.headteacherservice.mapper.TeacherMapper;
import com.example.headteacherservice.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeacherService {


    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;

    @Transactional
    public TeacherDto create(TeacherDto teacherDto) {
        if (teacherRepository.existsTeacherByTeacherName(teacherDto.getTeacherName())){
            throw new TeacherAlreadyExistException("Teacher already exist");
        }
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        Teacher savedTeacher = teacherRepository.save(teacher);
        return teacherMapper.toDto(savedTeacher);
    }

    @Transactional(readOnly = true)
    public TeacherDto getByNameAndSurname(String teacherName, String surname) {
        Teacher teacher = teacherRepository.findByTeacherNameAndTeacherSurname(teacherName, surname)
                .orElseThrow(() ->
                        new TeacherNotFoundException("Teacher not found"));

        return teacherMapper.toDto(teacher);
    }

    @Transactional(readOnly = true)
    public List<TeacherDto> getAll() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toDto)
                .toList();
    }

    @Transactional
    public TeacherDto update(Long id, TeacherDto teacherDto) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() ->
                        new TeacherNotFoundException("Teacher not found"));
        teacherMapper.updateEntityFromDto(teacherDto, teacher);
        Teacher updatedTeacher = teacherRepository.save(teacher);
        return teacherMapper.toDto(updatedTeacher);
    }

    @Transactional
    public void delete(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new TeacherNotFoundException("Teacher not found");
        }
        teacherRepository.deleteById(id);
    }
}
