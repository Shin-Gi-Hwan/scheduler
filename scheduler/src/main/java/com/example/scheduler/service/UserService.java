package com.example.scheduler.service;

import com.example.scheduler.dto.UserResponseDto;
import com.example.scheduler.entity.User;
import com.example.scheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto saveUser(String username, String email) {
        // 유저 객체 생성
        User user = new User(username, email);
        // 유저 객체 저장
        User save = userRepository.save(user);
        // UserResponseDto 로 반환
        return new UserResponseDto(save.getId(), save.getUsername(), save.getEmail(), save.getCreatedAt(), save.getModifiedAt());
    }

    public UserResponseDto findByUserId(Long userId) {
        Optional<User> optional = userRepository.findById(userId);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 " + userId + "를 찾을수 없습니다.");
        }

        User user = optional.get();

        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt(), user.getModifiedAt());
    }
}
