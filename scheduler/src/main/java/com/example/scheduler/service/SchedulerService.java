package com.example.scheduler.service;

import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import com.example.scheduler.entity.User;
import com.example.scheduler.repository.ScheduleRepository;
import com.example.scheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SchedulerService {

    // Schedule, User Repository 주입
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    public ScheduleResponseDto saveSchedule(String username, String title, String content) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없는 " + username + "입니다.");
        }

        Schedule sc = new Schedule(username, title, content);
        sc.setUser(user);

        scheduleRepository.save(sc);

        return new ScheduleResponseDto(sc.getId(), sc.getUsername(), sc.getTitle(),
                sc.getContent(), sc.getCreatedAt(), sc.getModifiedAt());
    }

    public List<ScheduleResponseDto> findAllByUsername(String username) {
        return scheduleRepository.findAllByUsername(username);
    }

    public ScheduleResponseDto findByScheduleId(Long id) {
        Optional<Schedule> optional = scheduleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없는 " + id +"값 입니다.");
        }

        Schedule schedule = optional.get();

        return new ScheduleResponseDto(schedule.getId(), schedule.getUsername(), schedule.getTitle(),
                schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }

    @Transactional
    public ScheduleResponseDto updateSchedule(Long id, String title, String content) {
        Optional<Schedule> optional = scheduleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없는 " + id +"값 입니다.");
        }

        Schedule schedule = optional.get();
        schedule.update(title, content);
        scheduleRepository.save(schedule);

        return new ScheduleResponseDto(schedule.getId(), schedule.getUsername(), schedule.getTitle(),
                schedule.getContent(), schedule.getCreatedAt(), schedule.getModifiedAt());
    }

    public void deleteSchedule(Long id) {
        Optional<Schedule> optional = scheduleRepository.findById(id);
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "찾을 수 없는 " + id +"값 입니다.");
        }

        Schedule schedule = optional.get();
        scheduleRepository.delete(schedule);
    }
}
