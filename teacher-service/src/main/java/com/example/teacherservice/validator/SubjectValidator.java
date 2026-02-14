package com.example.teacherservice.validator;

import com.example.teacherservice.enums.Subject;
import com.example.teacherservice.repository.GradeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SubjectValidator {

    private final GradeRepository gradeRepository;

    public boolean canTeach(Subject subject, Authentication auth) {
        if (!(auth.getPrincipal() instanceof Jwt jwt)) {
            return false;
        }
        String teacherSubject = jwt.getClaim("subject");
        return subject.name().equalsIgnoreCase(teacherSubject);
    }


    public boolean canTeach(Long studentId, Authentication authentication) {

        Jwt jwt = (Jwt) authentication.getPrincipal();
        String teacherSubject = jwt.getClaim("subject");

        Subject studentSubject = gradeRepository.getSubjectByStudentId(studentId);

        return teacherSubject.equalsIgnoreCase(studentSubject.name());
    }




}
