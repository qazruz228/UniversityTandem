package com.example.scholarshipservice.scheduler;

import com.example.scholarshipservice.service.ScholarshipService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ScholarshipScheduler {

    private final ScholarshipService scholarshipService;

    @Scheduled(cron = "0 0 0 31 12 *")
    public void assignDecemberScholarships() {
        scholarshipService.assignScholarship();
    }

    @Scheduled(cron = "0 0 0 30 6 *")
    public void assignJuneScholarships() {
        scholarshipService.assignScholarship();
    }
}
