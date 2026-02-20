package com.example.scholarshipservice.service;

import ch.qos.logback.classic.pattern.LineSeparatorConverter;
import com.example.scholarshipservice.client.TeacherClient;
import com.example.scholarshipservice.dto.StudentAverageGradeDto;
import com.example.scholarshipservice.dto.enums.Subject;
import com.example.scholarshipservice.entity.ScholarshipOfStudent;
import com.example.scholarshipservice.repository.ScholarshipOfStudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScholarshipService {
     
    private final ScholarshipOfStudentRepository scholarshipOfStudentRepository;
    private final TeacherClient teacherClient;

    public void assignScholarship() {

        List<StudentAverageGradeDto> dtos = teacherClient.getAverageGrades();
        List<ScholarshipOfStudent> scholarshipToSave = new ArrayList<>();
        Map<Subject, List<StudentAverageGradeDto>> groupedBySubject =
                dtos.stream()
                        .collect(Collectors.groupingBy(StudentAverageGradeDto::getSubject));

        for (Subject subject : groupedBySubject.keySet()) {

            List<StudentAverageGradeDto> gradeDtos = groupedBySubject.get(subject);

            for (StudentAverageGradeDto studentAverageGradeDto : gradeDtos) {
                int amount = calculateAmount(studentAverageGradeDto.getAverageGrade());
                ScholarshipOfStudent scholarshipOfStudent = new ScholarshipOfStudent();
                scholarshipOfStudent.setStudentId(studentAverageGradeDto.getStudentId());
                scholarshipOfStudent.setAmount(amount);
                scholarshipToSave.add(scholarshipOfStudent);
            }
        }
        scholarshipOfStudentRepository.saveAll(scholarshipToSave);

    }


    public int calculateAmount(Double avGrade) {

        if (avGrade == 5.0) {
            return 5000;
        }
        if (avGrade >= 4.0) {
            return 3800;
        } else return 0;
    }

}
