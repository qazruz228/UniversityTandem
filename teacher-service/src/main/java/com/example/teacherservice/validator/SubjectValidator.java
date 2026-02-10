package com.example.teacherservice.validator;

import com.example.teacherservice.enums.Subject;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class SubjectValidator {

    public boolean canTeach(Subject subject, Authentication auth) {
        if (!(auth.getPrincipal() instanceof Jwt jwt)) {
            return false;
        }
        String teacherSubject = jwt.getClaim("subject");
        return subject.name().equalsIgnoreCase(teacherSubject);
    }
}
