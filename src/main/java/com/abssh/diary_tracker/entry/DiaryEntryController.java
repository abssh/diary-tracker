package com.abssh.diary_tracker.entry;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abssh.diary_tracker.entry.dto.request.CreateDiaryEntryRequest;
import com.abssh.diary_tracker.entry.dto.response.EntryResponse;
import com.abssh.diary_tracker.security.UserWrapper;

import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        @RequestBody CreateDiaryEntryRequest request
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
    
}
