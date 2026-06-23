package com.abssh.diary_tracker.entry;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface EntryRepository extends JpaRepository<Entry, UUID> {

    @Query(
        """
        SELECT e FROM Entry e
        WHERE e.id = :entryId
        AND e.diary.id = :diaryId
        AND e.diary.user.id = :userId
        """
    ) Optional<Entry> findByIdAndDiaryIdAndUserId(
        @Param("entryId") UUID entryId,
        @Param("diaryId") UUID diaryId,
        @Param("userId") UUID userId
    );
}
