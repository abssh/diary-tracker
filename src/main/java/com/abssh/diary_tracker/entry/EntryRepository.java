package com.abssh.diary_tracker.entry;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, UUID> {
}
