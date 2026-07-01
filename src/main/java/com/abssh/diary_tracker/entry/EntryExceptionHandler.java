package com.abssh.diary_tracker.entry;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.abssh.diary_tracker.common.dto.ErrorResponse;
import com.abssh.diary_tracker.entry.types.exceptions.EntryNotFoundException;

@RestControllerAdvice
public class EntryExceptionHandler {
    @ExceptionHandler(EntryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEntryNotFound(EntryNotFoundException ex) {
        return ResponseEntity
            .status(HttpStatus.NOT_FOUND)
            .body(ErrorResponse.of(ex.getMessage()));
    }

}
