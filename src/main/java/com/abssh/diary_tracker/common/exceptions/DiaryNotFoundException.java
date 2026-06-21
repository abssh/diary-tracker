package com.abssh.diary_tracker.common.exceptions;

import java.util.UUID;

public class DiaryNotFoundException extends RuntimeException {

    public DiaryNotFoundException(UUID diaryId) {
        super("diary not found : " + diaryId);
    }
}
