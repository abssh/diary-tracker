package com.abssh.diary_tracker.user.types.dto.response;

public record SignedUserResponse(
    String accessToken,
    String username
) { }
