package com.example.scheduler.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {
    private final String username;
    private final String password;
    private final String email;

    public UserRequestDto(String username, String password , String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
