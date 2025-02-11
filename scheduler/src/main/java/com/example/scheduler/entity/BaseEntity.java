package com.example.scheduler.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @CreatedDate // Entity 가 생성되어 저장될 때 시간이 자동 저장 된다.
    @Column(updatable = false)
//    @Temporal(TemporalType.TIMESTAMP) 생략가능
    private LocalDateTime createdAt;

    @LastModifiedDate // 조회한 Entity 값을 변경할 때 시간이 자동 저장 된다.
    private LocalDateTime modifiedAt;
}
