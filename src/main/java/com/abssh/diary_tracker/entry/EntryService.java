package com.abssh.diary_tracker.entry;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.abssh.diary_tracker.common.exceptions.DiaryNotFoundException;
import com.abssh.diary_tracker.diary.Diary;
import com.abssh.diary_tracker.diary.DiaryRepository;
import com.abssh.diary_tracker.entry.dto.request.CreateDiaryEntryRequest;
import com.abssh.diary_tracker.entry.dto.response.EntryResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntryService {

    private final EntryRepository entryRepository;
    private final DiaryRepository diaryRepository;

    public EntryResponse createDairyEntry(UUID userId, UUID diaryId, CreateDiaryEntryRequest request) {
        Diary diary = diaryRepository.findByIdAndUserId(diaryId, userId)
            .orElseThrow(() -> new DiaryNotFoundException(diaryId));
        
        Entry entry = Entry.builder()
            .diary(diary)
            .entryDate(request.entryDate())
            .content(request.content())
            .contentType(request.contentType())
            .build();

        Entry saved = entryRepository.save(entry);

        return EntryResponse.from(saved);
    }
}
