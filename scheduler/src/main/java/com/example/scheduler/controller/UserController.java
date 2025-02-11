package com.example.scheduler.controller;

import com.example.scheduler.dto.UserRequestDto;
import com.example.scheduler.dto.UserResponseDto;
import com.example.scheduler.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {

        UserResponseDto dto = userService.findByUserId(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
