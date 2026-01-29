package com.example.headteacherservice.repository;

import com.example.headteacherservice.entity.Schedule;
import com.example.headteacherservice.entity.enumType.Weekday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

    @Query("""
        select s from Schedule s
        join fetch s.group g
        join fetch s.subject
        join fetch s.teacher
        where g.groupName = :groupName
        order by s.dayOfWeek, s.lessonOrder
    """)
    List<Schedule> findByGroupName(@Param("groupName") String groupName);




    @Query("""
        select s from Schedule s
        join fetch s.group g
        join fetch s.subject
        join fetch s.teacher
        where g.groupName = :groupName
          and (:dayOfWeek is null or s.dayOfWeek = :dayOfWeek)
        order by s.dayOfWeek, s.lessonOrder
    """)
    List<Schedule> findByGroupNameAndDay(@Param("groupName") String groupName,
                                         @Param("dayOfWeek") Weekday dayOfWeek
    );


}

