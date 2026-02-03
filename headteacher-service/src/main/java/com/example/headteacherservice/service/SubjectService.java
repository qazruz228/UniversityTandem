package com.example.headteacherservice.service;


import com.example.headteacherservice.dto.SubjectDto;
import com.example.headteacherservice.entity.Subject;
import com.example.headteacherservice.exception.SubjectNotFoundException;
import com.example.headteacherservice.mapper.SubjectMapper;
import com.example.headteacherservice.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class SubjectService {
    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    @Transactional
    public void createSubject(SubjectDto subjectDto) {
        subjectRepository.save(subjectMapper.toEntity(subjectDto));
    }

    @Transactional
    public void deleteSubject(String subjectName) {
        Subject subject = subjectRepository.findBySubjectName(subjectName)
                .orElseThrow(() -> new SubjectNotFoundException(
                        "Subject with name '" + subjectName + "' not found"));
        subjectRepository.delete(subject);
    }
}
