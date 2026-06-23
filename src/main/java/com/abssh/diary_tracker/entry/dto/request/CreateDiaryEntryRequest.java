package com.abssh.diary_tracker.entry.dto.request;


import java.time.LocalDate;

import com.abssh.diary_tracker.entry.ContentType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public record CreateDiaryEntryRequest(
    @NotNull(message = "Entry date is required")
    @PastOrPresent(message = "Entry date can't be in the future")
    LocalDate entryDate,

    @NotBlank(message = "Content is required")
    String content,

    ContentType contentType

) {
    public CreateDiaryEntryRequest {
        if (contentType == null) {
            contentType = ContentType.MARKDOWN;
        }
    }
}
