package com.abssh.diary_tracker.user.dto.response;

import java.time.Instant;
import java.util.UUID;

import com.abssh.diary_tracker.user.User;

public record SignedUserResponse(
    UUID id,
    String username,
    Instant createdAt
) {

    public static SignedUserResponse from(User saved) {
        return new SignedUserResponse(
            saved.getId(),
            saved.getUsername(),
            saved.getCreatedAt()
        );
    }}
