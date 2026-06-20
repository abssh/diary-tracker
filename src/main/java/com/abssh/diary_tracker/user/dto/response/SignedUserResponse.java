package com.abssh.diary_tracker.user.dto.response;

public record SignedUserResponse(
    String accessToken,
    String username
) { }
