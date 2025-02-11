package com.example.scheduler.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import org.hibernate.annotations.processing.Pattern;

@Getter
public class UserRequestDto {
    private final String username;

    @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private final String password;
    private final String email;

    public UserRequestDto(String username, String password , String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
}
