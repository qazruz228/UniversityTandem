package com.example.studentservice.client;

import com.example.studentservice.dto.ApiResponse;
import com.example.studentservice.enums.Weekday;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "headteacher-service", url = "http://localhost:10000")
public interface HeadTeacherClient {


    @GetMapping("/{groupName}")
    ResponseEntity<ApiResponse> getScheduleByGroupName(@PathVariable String groupName,
                                                       @RequestParam(required = false) Weekday dayOfWeek);


    @GetMapping("/getScheduleByGroup")
    ResponseEntity<ApiResponse> getScheduleByGroup(@RequestParam String groupName);
}
