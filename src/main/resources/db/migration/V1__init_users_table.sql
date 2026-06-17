CREATE TABLE users (
    id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    username varchar(50) NOT NULL UNIQUE,
    password_hash varchar(255) NOT NULL,
    created_at TIMESTAMP NOT NULL DEFAULT now()
);