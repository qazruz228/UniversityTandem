package com.example.headteacherservice.service;

import com.example.headteacherservice.dto.SubjectDto;
import com.example.headteacherservice.entity.Subject;
import com.example.headteacherservice.repository.SubjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepository subjectRepository;

    public void createSubject(SubjectDto subjectDto) {
        Subject subject = new Subject();
            subject.setSubjectName(subjectDto.getSubjectName());
            subjectRepository.save(subject);
    }

    public void deleteGroup(SubjectDto subjectDto) {
        String subjectName = subjectDto.getSubjectName();
        subjectRepository.deleteBySubjectName(subjectName);
    }

}
