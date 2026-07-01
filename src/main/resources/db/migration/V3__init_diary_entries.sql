CREATE TABLE diary_entries (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    diary_id UUID REFERENCES diaries(id) ON DELETE CASCADE,
    entry_date DATE NOT NULL,
    content text NOT NULL,
    content_type varchar(30) NOT NULL DEFAULT 'MARKDOWN'
        CHECK ( content_type IN ('MARKDOWN', 'HTML', 'XML', 'RAW_TEXT') ),
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NOT NULL
);

CREATE INDEX idx_diary_entries_diary_id ON diary_entries(diary_id);
CREATE INDEX idx_diary_entries_entry_date ON diary_entries(diary_id, entry_date);