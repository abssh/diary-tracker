package com.abssh.diary_tracker.diary;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import com.abssh.diary_tracker.diary.types.entity.Diary;

public interface DiaryRepository extends JpaRepository<Diary, UUID> {
    Page<Diary> findByUserId(UUID userId, Pageable pageable);

    Optional<Diary> findByIdAndUserId(UUID id, UUID userId);
}
