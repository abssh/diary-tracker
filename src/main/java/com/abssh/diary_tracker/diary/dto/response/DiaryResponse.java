package com.abssh.diary_tracker.diary.dto.response;

import java.time.Instant;
import java.util.UUID;

import com.abssh.diary_tracker.diary.Diary;

public record DiaryResponse(
    UUID id,
    String title,
    String description,
    Instant createdAt,
    Instant UpdatedAt
) {
    public static DiaryResponse from(Diary entity) {
        return new DiaryResponse(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}
