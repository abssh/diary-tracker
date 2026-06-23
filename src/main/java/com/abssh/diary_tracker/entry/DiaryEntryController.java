package com.abssh.diary_tracker.entry;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abssh.diary_tracker.entry.dto.request.CreateDiaryEntryRequest;
import com.abssh.diary_tracker.entry.dto.response.EntryResponse;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/v1/diaries/{diaryId}/entries")
@RequiredArgsConstructor
public class DiaryEntryController {

    private final EntryService entryService;

    @PostMapping("")
    public ResponseEntity<EntryResponse> createEntry(
        @AuthenticationPrincipal UserWrapper userWrapper,
        @PathVariable UUID diaryId,
        @Valid @RequestBody CreateDiaryEntryRequest request
    ) {
        
        EntryResponse response = entryService.createDairyEntry(userWrapper.getUser().getId(), diaryId, request);

        String urlString = new StringBuilder().append("/api/v1/diaries/")
            .append(diaryId)
            .append("/entries/")
            .append(response.entryId())
            .toString();
            
        return ResponseEntity
            .created(URI.create(urlString))
            .body(response);
    }

    @GetMapping("{entryId}")
    public ResponseEntity<EntryResponse> getEntry(
        @AuthenticationPrincipal UserWrapper userWrapper,
        @PathVariable UUID diaryId,
        @PathVariable UUID entryId
    ) {
        EntryResponse response = entryService.getDiaryEntry(userWrapper.getUser().getId(), diaryId, entryId);
        return ResponseEntity.ok(response);   
    }

    @GetMapping("")
    public ResponseEntity<Page<EntryResponse>> getManyEntries(
        @AuthenticationPrincipal UserWrapper userWrapper,
        @PathVariable UUID diaryId,
        @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        Page<EntryResponse> responsePage = entryService.getManyDiaryEntries(userWrapper.getUser().getId(), diaryId, pageable);
        return ResponseEntity.ok(responsePage);
    }
    
    
}
