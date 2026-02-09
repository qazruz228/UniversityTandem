package com.example.teacherservice.entity;

import com.example.teacherservice.enums.Subject;
import com.example.teacherservice.enums.converter.SubjectConverter;
import jakarta.persistence.*;

@Entity
@Table(name = "grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private short grade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @Convert(converter = SubjectConverter.class)
    @Column(name = "subject_name", nullable = false)
    private Subject subject;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "date_id", nullable = false)
    private CalendarDate date;


}