package com.example.scheduler.service;

import com.example.scheduler.dto.UserResponseDto;
import com.example.scheduler.entity.User;
import com.example.scheduler.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    // User Repository 주입
    private final UserRepository userRepository;

    public UserResponseDto saveUser(String username, String email) {
        // 유저 객체 생성
        User user = new User(username, email);
        // 유저 객체 저장
        User save = userRepository.save(user);
        // UserResponseDto 로 반환
        return new UserResponseDto(save.getId(), save.getUsername(), save.getEmail(), save.getCreatedAt(), save.getModifiedAt());
    }

    public UserResponseDto findByUserId(Long id) {
        // DB에 있는 ID 값 조회
        Optional<User> optional = userRepository.findById(id);

        // NPE 방지
        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 " + id + "를 찾을수 없습니다.");
        }

        // Optional 에서 user 를 가져옴
        User user = optional.get();

        // UserResponseDto 로 반환
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt(), user.getModifiedAt());
    }

    @Transactional
    public UserResponseDto updateUser(Long id, String email) {
        Optional<User> optional = userRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 " + id + "를 찾을수 없습니다.");
        }

        User user = optional.get(); // 조회 데이터 가져오기
        user.updateUser(email); // 수정할 email 입력
        userRepository.save(user); // 이메일 업데이트
        return new UserResponseDto(user.getId(), user.getUsername(), user.getEmail(), user.getCreatedAt(), user.getModifiedAt());
    }

    public void deleteUser(Long id) {
        Optional<User> optional = userRepository.findById(id);

        if (optional.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "해당 " + id + "를 찾을수 없습니다.");
        }

        User user = optional.get(); // 데이터 가져오기
        userRepository.delete(user); // 유저 삭제
    }
}
