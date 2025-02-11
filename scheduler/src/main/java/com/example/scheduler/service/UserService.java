package com.example.scheduler.service;

import com.example.scheduler.dto.UserResponseDto;
import com.example.scheduler.entity.User;
import com.example.scheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public UserResponseDto saveUser(String username, String email) {
        // 유저 객체 생성
        User user = new User(username, email);
        // 유저 객체 저장
        User save = userRepository.save(user);

        return new UserResponseDto(save.getId(), save.getUsername(), save.getEmail());
    }
}
