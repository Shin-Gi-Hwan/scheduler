package com.example.scheduler.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.annotations.processing.Pattern;

@Getter
public class UserRequestDto {
    @NotBlank(message = "유저 이름은 필수 입력 값입니다.")
    private final String username;
    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    private final String password;
    @Email
    @NotBlank(message = "이메일은 필수 입력 값입니다.")
    private final String email;

    public UserRequestDto(String username, String password , String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
