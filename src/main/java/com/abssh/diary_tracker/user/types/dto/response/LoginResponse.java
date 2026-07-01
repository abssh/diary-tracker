package com.abssh.diary_tracker.user.types.dto.response;

public record LoginResponse(
    String accessToken,
    String username
) {}
