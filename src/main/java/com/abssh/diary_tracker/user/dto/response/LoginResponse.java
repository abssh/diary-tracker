package com.abssh.diary_tracker.user.dto.response;

public record LoginResponse(
    String accessToken,
    String username
) {}
