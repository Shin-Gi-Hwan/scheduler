package com.example.scheduler.service;

import com.example.scheduler.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SchedulerService {
    private final ScheduleRepository scheduleRepository;

}
