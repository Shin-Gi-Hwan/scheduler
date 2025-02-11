package com.example.scheduler.controller;

import com.example.scheduler.dto.ScheduleRequestDto;
import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.service.SchedulerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/scheduler")
@RequiredArgsConstructor
public class SchedulerController {
    private final SchedulerService schedulerService;

    @PostMapping("/created")
    public ResponseEntity<ScheduleResponseDto> createScheduler(@RequestBody ScheduleRequestDto dto) {
        ScheduleResponseDto responseDto = schedulerService.saveSchedule(
                dto.getUsername(),
                dto.getTitle(),
                dto.getContent()
        );

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<List<ScheduleResponseDto>> getSchedule(@PathVariable String username) {
        List<ScheduleResponseDto> responseDtos = schedulerService.findAllByUsername(username);
        return new ResponseEntity<>(responseDtos, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> updateSchedule(@PathVariable Long id, @RequestBody ScheduleRequestDto dto) {
        ScheduleResponseDto responseDto = schedulerService.updateSchedule(
                id,
                dto.getTitle(),
                dto.getContent()
        );

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ScheduleResponseDto> deleteSchedule(@PathVariable Long id) {
        schedulerService.deleteSchedule(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
