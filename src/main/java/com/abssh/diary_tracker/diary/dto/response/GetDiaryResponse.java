package com.abssh.diary_tracker.diary.dto.response;

import java.time.Instant;
import java.util.UUID;

import com.abssh.diary_tracker.diary.Diary;

public record GetDiaryResponse(
    UUID id,
    String title,
    String description,
    Instant createdAt,
    Instant UpdatedAt
) {
    public static GetDiaryResponse from(Diary entity) {
        return new GetDiaryResponse(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}
