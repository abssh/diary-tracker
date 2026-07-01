package com.abssh.diary_tracker.diary;

import java.util.UUID;

import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.abssh.diary_tracker.diary.types.dto.requests.CreateDiaryRequest;
import com.abssh.diary_tracker.diary.types.dto.response.DiaryResponse;
import com.abssh.diary_tracker.diary.types.entity.Diary;
import com.abssh.diary_tracker.diary.types.exceptions.DiaryNotFoundException;
import com.abssh.diary_tracker.user.UserRepository;
import com.abssh.diary_tracker.user.types.entity.User;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public DiaryResponse createUserDiary(UUID userId, CreateDiaryRequest request) {
        User user = userRepository.getReferenceById(userId);

        Diary diary = Diary.builder()
                .user(user)
                .title(request.title())
                .description(request.description())
                .build();

        Diary saved = diaryRepository.save(diary);
        return DiaryResponse.from(saved);
    }

    public DiaryResponse getUserDiary(UUID userId, UUID diaryId) {
        Diary diary = diaryRepository
                .findByIdAndUserId(diaryId, userId)
                .orElseThrow(() -> new DiaryNotFoundException(diaryId));

        return DiaryResponse.from(diary);
    }

    public Page<DiaryResponse> getAllUserDiaries(UUID userId, Pageable pagable) {
        Page<Diary> diaryPage = diaryRepository.findByUserId(userId, pagable);

        return diaryPage.map(diary -> DiaryResponse.from(diary));
    }
}
