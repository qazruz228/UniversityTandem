package com.example.headteacherservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "student_name", nullable = false)
    private String studentName;

    @Column(name = "student_surname")
    private String studentSurname;

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

}
