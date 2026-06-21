package com.abssh.diary_tracker.diary;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abssh.diary_tracker.diary.dto.requests.CreateDiaryRequest;
import com.abssh.diary_tracker.diary.dto.response.DiaryResponse;
import com.abssh.diary_tracker.security.UserWrapper;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/diaries")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @PostMapping("")
    public ResponseEntity<DiaryResponse> createDiary(
            @AuthenticationPrincipal UserWrapper userWrapper,
            @Valid @RequestBody CreateDiaryRequest entity) {
        DiaryResponse response = diaryService.createUserDiary(userWrapper.getUser().getId(), entity);
        return ResponseEntity
                .created(URI.create("/diaries/" + response.id()))
                .body(response);
    }

    @GetMapping("{id}")
    public ResponseEntity<DiaryResponse> getDiary(
            @AuthenticationPrincipal UserWrapper userWrapper,
            @PathVariable UUID id) {
        DiaryResponse response = diaryService.getUserDiary(userWrapper.getUser().getId(), id);
        return ResponseEntity
                .ok(response);
    }

    @GetMapping("")
    public ResponseEntity<Page<DiaryResponse>> getAllDiaries(
            @AuthenticationPrincipal UserWrapper userWrapper,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<DiaryResponse> responsPage = diaryService.getAllUserDiaries(userWrapper.getUser().getId(), pageable);
        return ResponseEntity.ok(responsPage);
    }

}
