package com.example.scholarshipservice.repository;

import com.example.scholarshipservice.entity.ScholarshipOfStudent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface ScholarshipOfStudentRepository extends JpaRepository<ScholarshipOfStudent,Long> {


}
