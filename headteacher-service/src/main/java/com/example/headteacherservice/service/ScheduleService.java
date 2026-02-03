package com.example.headteacherservice.service;

import com.example.headteacherservice.dto.*;
import com.example.headteacherservice.entity.Group;
import com.example.headteacherservice.entity.Schedule;
import com.example.headteacherservice.entity.Subject;
import com.example.headteacherservice.entity.Teacher;
import com.example.headteacherservice.entity.enumType.Weekday;
import com.example.headteacherservice.exception.GroupNotFoundException;
import com.example.headteacherservice.exception.ScheduleNotFoundException;
import com.example.headteacherservice.mapper.ScheduleMapper;
import com.example.headteacherservice.repository.GroupRepository;
import com.example.headteacherservice.repository.ScheduleRepository;
import com.example.headteacherservice.repository.SubjectRepository;
import com.example.headteacherservice.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final GroupRepository groupRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final ScheduleMapper scheduleMapper;


    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleCreateOrUpdateDto scheduleCreateOrUpdateDto) {
        Schedule schedule = new Schedule();
        schedule.setLessonOrder(scheduleCreateOrUpdateDto.getLessonOrder());
        schedule.setDayOfWeek(scheduleCreateOrUpdateDto.getDayOfWeek());
        schedule.setGroup(getGroupById(scheduleCreateOrUpdateDto.getGroupId()));
        schedule.setSubject(getSubjectById(scheduleCreateOrUpdateDto.getSubjectId()));
        schedule.setTeacher(getTeacherById(scheduleCreateOrUpdateDto.getTeacherId()));

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return scheduleMapper.toDto(savedSchedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleCreateOrUpdateDto scheduleCreateOrUpdateDto) {

        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("this schedule not found"));

        Schedule schedule1 = scheduleMapper.updateEntityFromDto(scheduleCreateOrUpdateDto, schedule);
        return scheduleMapper.toDto(scheduleRepository.save(schedule1));

    }


    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getByGroupName(String groupName) {

        List<Schedule> schedules = scheduleRepository.findByGroupName(groupName);

        if (schedules.isEmpty()) {
            throw new ScheduleNotFoundException("Schedule not found for group: " + groupName);
        }

        return schedules.stream()
                .map(scheduleMapper::toDto)
                .toList();
    }


    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getByGroupNameAndWeek(String groupName, Weekday dayOfWeek) {

        List<Schedule> schedules =
                scheduleRepository.findByGroupNameAndDay(groupName, dayOfWeek);

        if (schedules.isEmpty()) {
            throw new ScheduleNotFoundException(
                    "Schedule not found for group: " + groupName +
                            (dayOfWeek != null ? ", day: " + dayOfWeek : "")
            );
        }

        return schedules.stream()
                .map(scheduleMapper::toDto)
                .toList();
    }


    @Transactional
    public void delete(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new ScheduleNotFoundException("Schedule not found");
        }
        scheduleRepository.deleteById(id);
    }


    public Group getGroupById(Long groupId) {
        return groupRepository.findById(groupId).orElseThrow(() ->
                new IllegalArgumentException("groupId is required"));
    }

    public Subject getSubjectById(Long subjectId) {
        return subjectRepository.findById(subjectId).orElseThrow(() ->
                new IllegalArgumentException("subjectId is required"));
    }


    public Teacher getTeacherById(Long teacherId) {
        return teacherRepository.findById(teacherId).orElseThrow(() ->
                new IllegalArgumentException("teacherId is required"));
    }




}


