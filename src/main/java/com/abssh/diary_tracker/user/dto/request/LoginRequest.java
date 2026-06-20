package com.abssh.diary_tracker.user.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest(
    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50, message = "username most be of a length between 3 and 50 charecters")
    String username,

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 72, message = "password must be of a length between 8 and 72 charecters")
    String plainPassword
) {}
