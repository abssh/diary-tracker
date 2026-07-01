package com.abssh.diary_tracker.entry;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.abssh.diary_tracker.entry.types.entity.Entry;

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

    @Query(
        """
        SELECT e from Entry e
        WHERE e.diary.id = :diaryId
        AND e.diary.user.id = :userId    
        """
    ) Page<Entry> findByDiaryIdAndUserId(
        @Param("diaryId") UUID diaryId,
        @Param("userId") UUID userId,
        Pageable pageable
    );
}
