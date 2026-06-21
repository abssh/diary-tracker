package com.abssh.diary_tracker.diary;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.abssh.diary_tracker.common.exceptions.DiaryNotFoundException;
import com.abssh.diary_tracker.diary.dto.requests.CreateDiaryRequest;
import com.abssh.diary_tracker.diary.dto.response.CreateDiaryResponse;
import com.abssh.diary_tracker.diary.dto.response.GetDiaryResponse;
import com.abssh.diary_tracker.user.User;
import com.abssh.diary_tracker.user.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DiaryService {
    private final DiaryRepository diaryRepository;
    private final UserRepository userRepository;

    public CreateDiaryResponse createUserDiary(UUID userId, CreateDiaryRequest request) {
        User user = userRepository.getReferenceById(userId);

        Diary diary = Diary.builder()
            .user(user)
            .title(request.title())
            .description(request.description())
            .build();
        
        Diary saved = diaryRepository.save(diary);
        return CreateDiaryResponse.from(saved);
    }

    public GetDiaryResponse getUserDiary(UUID userId, UUID diaryId) {
        Diary diary = diaryRepository
            .findByIdAndUserId(diaryId, userId)
            .orElseThrow(() -> new DiaryNotFoundException(diaryId));

        return GetDiaryResponse.from(diary);
    }
}
