package com.example.scheduler.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private final String username;
    private final String title;
    private final String content;

    public ScheduleRequestDto(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }
}
