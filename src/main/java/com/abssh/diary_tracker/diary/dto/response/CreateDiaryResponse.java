package com.abssh.diary_tracker.diary.dto.response;

import java.time.Instant;
import java.util.UUID;

import com.abssh.diary_tracker.diary.Diary;

public record CreateDiaryResponse(
    UUID id,
    String title,
    String description,
    Instant createdAt,
    Instant updatedAt
) {

    public static CreateDiaryResponse from(Diary entity) {
        return new CreateDiaryResponse(
            entity.getId(),
            entity.getTitle(),
            entity.getDescription(),
            entity.getCreatedAt(),
            entity.getUpdatedAt()
        );
    }
}
