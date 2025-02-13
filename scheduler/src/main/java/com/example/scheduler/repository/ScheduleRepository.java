package com.example.scheduler.repository;

import com.example.scheduler.dto.ScheduleResponseDto;
import com.example.scheduler.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<ScheduleResponseDto> findAllByUsername(String username);

    @Modifying
    @Query("UPDATE Schedule s set s.deleted = 'true' WHERE s.user.id = :id")
    void updateDeletedFlagByUserId(Long id);

    @Modifying
    @Query("UPDATE Schedule s SET s.user = null WHERE s.user.id = :id")
    void detachUserFromScheduler(@Param("id") Long id);
}
