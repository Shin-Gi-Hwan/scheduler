package com.example.scheduler.controller;

import com.example.scheduler.dto.LoginRequestDto;
import com.example.scheduler.dto.UserRequestDto;
import com.example.scheduler.dto.UserResponseDto;
import com.example.scheduler.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<UserResponseDto> signupUser(@RequestBody UserRequestDto dto) {
        UserResponseDto userResponseDto = userService.saveUser(
                dto.getUsername(),
                dto.getPassword(),
                dto.getEmail()
        );

        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/signin")
    public ResponseEntity<UserResponseDto> signInUser(@RequestBody LoginRequestDto dto, HttpServletRequest request) {
        UserResponseDto userResponseDto = userService.loginUser(dto.getEmail(), dto.getPassword());

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> getUserById(@PathVariable Long id) {
        UserResponseDto dto = userService.findByUserId(id);

        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserResponseDto> updatedUser(@PathVariable Long id,
                                                       @RequestBody UserRequestDto dto) {
        UserResponseDto userResponseDto = userService.updateUser(id, dto.getEmail());

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK); // 데이터가 정상적으로 삭제 되었슴.
    }
}
