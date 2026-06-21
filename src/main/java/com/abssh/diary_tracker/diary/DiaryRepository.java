package com.abssh.diary_tracker.diary;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface DiaryRepository extends JpaRepository<Diary, UUID> {
    List<Diary> findByUserId(UUID userId);
    Optional<Diary> findByIdAndUserId(UUID id, UUID userId);
}
