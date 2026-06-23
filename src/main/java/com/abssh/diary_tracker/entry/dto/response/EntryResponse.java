package com.abssh.diary_tracker.entry.dto.response;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

import com.abssh.diary_tracker.entry.ContentType;
import com.abssh.diary_tracker.entry.Entry;

public record EntryResponse(
    UUID entryId,
    UUID dairyId,
    LocalDate entryDate,
    String content,
    ContentType contentType,
    Instant createdAt,
    Instant updatedAt
) {

    public static EntryResponse from(Entry entity) {
        return new EntryResponse(
            entity.getId(),
            entity.getDiary().getId(),
            entity.getEntryDate(),
            entity.getContent(),
            entity.getContentType(),
            entity.getCreatedAt(),
            entity.getUpdatedAt());
    }
}
