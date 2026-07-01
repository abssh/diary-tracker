package com.abssh.diary_tracker.diary.types.exceptions;

import java.util.UUID;

import com.abssh.diary_tracker.common.exceptions.EntityNotFoundException;

public class DiaryNotFoundException extends EntityNotFoundException {

    public DiaryNotFoundException(UUID diaryId) {
        super("diary not found : " + diaryId);
    }
}
