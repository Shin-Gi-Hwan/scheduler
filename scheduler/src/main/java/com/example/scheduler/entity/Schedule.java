package com.example.scheduler.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Getter
public class Schedule extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", columnDefinition = "BIGINT", nullable = false)
    private Long id;

    @Column(name = "USERNAME", columnDefinition = "NVARCHAR(128)", nullable = false)
    private String username;

    @Column(name = "TITLE", columnDefinition = "NVARCHAR(256)", nullable = false)
    private String title;

    @Column(name = "CONTENT", columnDefinition = "NVARCHAR(1000)", nullable = false)
    private String content;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User user;

    public Schedule(String username, String title, String content) {
        this.username = username;
        this.title = title;
        this.content = content;
    }
}
