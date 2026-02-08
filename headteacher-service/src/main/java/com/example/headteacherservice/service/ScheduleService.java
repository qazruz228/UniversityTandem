package com.example.headteacherservice.service;

import com.example.headteacherservice.dto.*;
import com.example.headteacherservice.entity.Schedule;
import com.example.headteacherservice.entity.enumType.Weekday;
import com.example.headteacherservice.exception.ScheduleNotFoundException;
import com.example.headteacherservice.mapper.ScheduleMapper;
import com.example.headteacherservice.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;


    @Transactional
    public ScheduleResponseDto createSchedule(ScheduleCreateOrUpdateDto dto) {
        log.info("Creating schedule for groupId={}, subjectId={}, teacherId={}, day={}, order={}",
                dto.getGroupId(), dto.getSubjectId(), dto.getTeacherId(),
                dto.getDayOfWeek(), dto.getLessonOrder());

        Schedule schedule = scheduleMapper.toEntity(dto);
        Schedule savedSchedule = scheduleRepository.save(schedule);

        log.info("Schedule created successfully with id={}", savedSchedule.getId());
        return scheduleMapper.toDto(savedSchedule);
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, ScheduleCreateOrUpdateDto dto) {
        log.info("Updating schedule id={}", id);

        Schedule schedule = findByIdOrThrow(scheduleRepository, id);
        Schedule updatedSchedule = scheduleMapper.updateEntityFromDto(dto, schedule);
        Schedule savedSchedule = scheduleRepository.save(updatedSchedule);

        log.info("Schedule updated successfully: id={}", savedSchedule.getId());
        return scheduleMapper.toDto(savedSchedule);
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getByGroupName(String groupName) {
        log.debug("Fetching schedules for group={}", groupName);

        List<Schedule> schedules = scheduleRepository.findByGroupName(groupName);
        if (schedules.isEmpty()) {
            throw new EmptyResultDataAccessException(1);
        }
        log.debug("Found {} schedules for group={}", schedules.size(), groupName);
        return schedules.stream().map(scheduleMapper::toDto).toList();
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponseDto> getByGroupNameAndWeek(String groupName, Weekday dayOfWeek) {
        log.debug("Fetching schedule for group={}, day={}", groupName, dayOfWeek);
        List<Schedule> schedules = scheduleRepository.findByGroupNameAndDay(groupName, dayOfWeek);
        if (schedules.isEmpty()) {
            log.warn("No schedule found for group={} and day={}", groupName, dayOfWeek);
            throw new ScheduleNotFoundException("Schedule not found for group: " + groupName +
                   " day: " + dayOfWeek);
        }
        log.debug("Found {} schedules for group={} and day={}",
                schedules.size(), groupName, dayOfWeek);
        return schedules.stream()
                .map(scheduleMapper::toDto).toList();
    }

    @Transactional
    public void delete(Long id) {
       Schedule schedule = findByIdOrThrow(scheduleRepository, id);
       scheduleRepository.delete(schedule);
    }


    private <T> T findByIdOrThrow(JpaRepository<T, Long> repository, Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule" + " not found: id=" + id));
    }

}



