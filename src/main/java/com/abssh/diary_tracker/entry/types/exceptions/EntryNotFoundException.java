package com.abssh.diary_tracker.entry.types.exceptions;

import java.util.UUID;

public class EntryNotFoundException extends RuntimeException {

    public EntryNotFoundException(UUID entryId) {
        super("Entry not found : " + entryId);
    }
}
