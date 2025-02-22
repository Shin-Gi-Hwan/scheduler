package com.example.scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.validation.annotation.Validated;

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

    @Column(name = "PASSWORD", columnDefinition = "NVARCHAR(128)", nullable = false)
    private String password;

    @Column(name = "EMAIL", columnDefinition = "NVARCHAR(256)", nullable = false)
    private String email;

    public User() {}

    public User(String username, String password , String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public User(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public void updateUser(String email) {
        this.email = email;
    }
}
