package com.example.teacherservice.repository;

import com.example.teacherservice.entity.CalendarDate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CalendarDateRepository extends JpaRepository<CalendarDate,Long> {

    Optional<CalendarDate> findById(Long id);


}
