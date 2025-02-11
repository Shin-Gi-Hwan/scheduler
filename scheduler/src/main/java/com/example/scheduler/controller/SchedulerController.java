package com.example.scheduler.controller;

import com.example.scheduler.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SchedulerController {
    private final SchedulerService schedulerService;

}
