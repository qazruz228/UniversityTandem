package com.example.scholarshipservice.client;

import com.example.scholarshipservice.dto.StudentAverageGradeDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "teacher-service", url = "http://localhost:10001")
public interface TeacherClient {

    @GetMapping("/getAverageGrades")
    List<StudentAverageGradeDto> getAverageGrades();
}


