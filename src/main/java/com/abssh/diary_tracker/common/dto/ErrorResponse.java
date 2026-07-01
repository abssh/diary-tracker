package com.abssh.diary_tracker.common.dto;

import java.util.List;

public record ErrorResponse(
    String message,
    List<FieldError> fieldErrors
) {
   
    public record FieldError(
        String Field,
        String massage
    ){}

    public static ErrorResponse of(String message) {
        return new ErrorResponse(message, null);
    }

    public static ErrorResponse ofErrors(List<FieldError> errors) {
        return new ErrorResponse(null, errors);
    }
}