package com.example.scheduler.controller;

import com.example.scheduler.dto.UserRequestDto;
import com.example.scheduler.dto.UserResponseDto;
import com.example.scheduler.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/user/create")
    public ResponseEntity<UserResponseDto> createdUser(@RequestBody UserRequestDto dto) {
        UserResponseDto userResponseDto = userService.saveUser(
                dto.getUsername(),
                dto.getEmail()
        );

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }
}
