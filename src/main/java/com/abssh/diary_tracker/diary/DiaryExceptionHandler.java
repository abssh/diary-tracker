package com.abssh.diary_tracker.diary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.abssh.diary_tracker.common.dto.ErrorResponse;
import com.abssh.diary_tracker.diary.types.exceptions.DiaryNotFoundException;

@RestControllerAdvice
public class DiaryExceptionHandler {

    @ExceptionHandler(DiaryNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleDiaryNotFound(DiaryNotFoundException ex) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ErrorResponse.of(ex.getMessage()));
    }
    
}
