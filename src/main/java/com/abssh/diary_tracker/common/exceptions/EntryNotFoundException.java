package com.abssh.diary_tracker.common.exceptions;

import java.util.UUID;

public class EntryNotFoundException extends RuntimeException {

    public EntryNotFoundException(UUID entryId) {
        super("Entry not found : " + entryId);
    }
}
