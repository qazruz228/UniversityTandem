package com.example.teacherservice.repository;

import com.example.teacherservice.events.OutboxEvent;
import jakarta.persistence.LockModeType;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface OutboxEventRepository  extends JpaRepository<OutboxEvent,Long> {

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("""
        select e from OutboxEvent e
        where e.outboxStatus = 'NEW'
        order by e.createdAt asc
    """)
        List<OutboxEvent> findBatch(Pageable pageable);
    }





