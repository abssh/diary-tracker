package com.abssh.diary_tracker.entry;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.abssh.diary_tracker.diary.DiaryRepository;
import com.abssh.diary_tracker.diary.types.entity.Diary;
import com.abssh.diary_tracker.diary.types.exceptions.DiaryNotFoundException;
import com.abssh.diary_tracker.entry.types.dto.request.CreateDiaryEntryRequest;
import com.abssh.diary_tracker.entry.types.dto.response.EntryResponse;
import com.abssh.diary_tracker.entry.types.entity.Entry;
import com.abssh.diary_tracker.entry.types.exceptions.EntryNotFoundException;

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

    public EntryResponse getDiaryEntry(UUID userId, UUID diaryId, UUID entryId) {
        Entry entry = entryRepository.findByIdAndDiaryIdAndUserId(entryId, diaryId, userId)
            .orElseThrow(() -> new EntryNotFoundException(entryId));

        return EntryResponse.from(entry);
    }

    public Page<EntryResponse> getManyDiaryEntries(UUID userId, UUID diaryId, Pageable pageable) {
        Page<Entry> entries = entryRepository.findByDiaryIdAndUserId(diaryId, userId, pageable);

        return entries.map(entry -> EntryResponse.from(entry));
    }
}
