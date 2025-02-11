package com.example.scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT", nullable = false)
    private Long id;

    @Column(name = "USERNAME", columnDefinition = "NVARCHAR(128)", nullable = false)
    private String username;

    @Column(name = "EMAIL", columnDefinition = "NVARCHAR(256)", nullable = false)
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public void updateUser(String email) {
        this.email = email;
    }

    public User() {}
}
