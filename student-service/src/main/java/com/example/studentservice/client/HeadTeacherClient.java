package com.example.studentservice.client;

import com.example.studentservice.config.FeignConfig;
import com.example.studentservice.dto.ApiResponse;
import com.example.studentservice.enums.Weekday;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "headteacher-service", url = "http://localhost:10001", configuration = FeignConfig.class)
public interface HeadTeacherClient {


    @GetMapping(value = "/{groupName}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiResponse> getScheduleByGroup(@PathVariable String groupName,
                                                   @RequestParam(required = false) Weekday dayOfWeek);


    @GetMapping(value = "/getScheduleByGroup", produces =  MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<ApiResponse> getScheduleByGroup(@RequestParam String groupName);
}
