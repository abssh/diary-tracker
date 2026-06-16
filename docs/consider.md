# Considerations to have when developing this application

## Architecture/data model

* Decide one entry per day vs multiple entries/timestamps, and whether entries are mutable history (audit/versioning) or simple updates.
* Use UUIDs or sequential IDs for entry/user PKs depending on whether you'll expose IDs in URLs.
* Plan for soft deletes if users should be able to "trash" then restore entries.
* Index (user_id, entry_date) and consider a unique constraint if one-entry-per-day.

## Auth & security

* Don't store plaintext passwords — use BCrypt/Argon2 via Spring Security's PasswordEncoder.
* Decide session-based vs JWT auth now; JWT is more common for REST APIs with future frontends but means handling token refresh/revocation.
* Enforce ownership checks on every endpoint — a user must only access their own diary entries (easy to miss in controller/service layer, leads to IDOR vulnerabilities).
* Use HTTPS even locally if you can, and never log passwords/tokens.
* Add rate limiting on login endpoints to prevent brute force.

## Postgres / containerization

* Use Flyway or Liquibase for schema migrations from day one — avoids pain later.
* Externalize DB credentials via environment variables/.env, don't hardcode in application.yml.
* Use Docker Compose (even if podman) to spin up Postgres + app together for local dev consistency.
* Set up a separate test database/profile (Testcontainers is great for integration tests with real Postgres).
* Plan timezone handling carefully — store timestamps in UTC, store dates (LocalDate) without timezone if "diary day" should be calendar-day based regardless of user location.

## API design

* Use DTOs, never expose JPA entities directly (avoids leaking lazy-loading issues and lets you control the contract).
* Plan pagination for entry listing endpoints from the start.
* Standardize error responses (e.g. ProblemDetail/RFC 7807) early so frontend has consistent error shapes.
* Add validation (@Valid, Bean Validation annotations) on request bodies.

## Other practical things

* Set up Spring profiles (dev/test/prod) early.
* Add basic logging and a health check endpoint (/actuator/health).
* Think about entry content size limits (text length, markdown support, attachments) since that affects DB column types (TEXT vs VARCHAR) and storage strategy if images are added later.
* Write integration tests for auth flows early — auth bugs are the most common source of "wait, why can user A see user B's diary" issues.