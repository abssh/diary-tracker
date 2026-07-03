# Diary Tracker

🔗 **Live API:** https://diary-tracker.onrender.com
*(hosted on Render free tier — first request may take ~30s to wake up)*

📖 **Swagger UI:** https://diary-tracker.onrender.com/swagger-ui.html

A REST API backend for a personal diary/journaling application, built with Spring Boot and PostgreSQL.

## Tech Stack

- **Java 25**
- **Spring Boot** — Web, Security, Data JPA, Validation, Actuator
- **PostgreSQL 16** — primary datastore
- **Flyway** — database schema migrations
- **JWT** (jjwt) — stateless authentication
- **Springdoc OpenAPI** — Swagger UI for API documentation
- **Lombok** — boilerplate reduction
- **Podman Compose** — containerized local development and deployment
- **Testcontainers** — disposable Postgres instances for integration tests
- **Maven** — build tool
- **Make** — developer workflow automation (run, migrate, clean)

## Features

- **JWT authentication** — stateless, token-based auth; tokens identify users by UUID
- **Multiple diaries per user** — create and manage independent diaries
- **Diary entries** — create, read, update, and delete entries within a diary
- **Content type support** — entries support multiple formats (plain text, markdown, etc.)
- **Paginated responses** — all list endpoints return paginated results
- **Input validation** — request validation with structured error responses per field
- **Domain-scoped exception handling** — exceptions and handlers colocated with their domain

## API Endpoints

### Auth — public

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/auth/signup` | Register a new user account |
| `POST` | `/api/v1/auth/login` | Exchange credentials for a JWT |

### Diaries — requires `Authorization: Bearer <token>`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/diaries` | Create a diary |
| `GET` | `/api/v1/diaries` | Get all diaries (paginated) |
| `GET` | `/api/v1/diaries/{id}` | Get a diary |

### Entries — requires `Authorization: Bearer <token>`

| Method | Endpoint | Description |
|--------|----------|-------------|
| `POST` | `/api/v1/diaries/{diaryId}/entries` | Create an entry |
| `GET` | `/api/v1/diaries/{diaryId}/entries` | Get all entries (paginated) |
| `GET` | `/api/v1/diaries/{diaryId}/entries/{entryId}` | Get an entry |

### Health — public

| Method | Endpoint | Description |
|--------|----------|-------------|
| `GET` | `/actuator/health` | Application health check |

## Project Structure

``` bash
diary-tracker/
├── docs/
│   ├── consider.md
│   └── project-tree.md
│
├── script/
│   └── bash/
│       └── shell-profiling.sh
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── abssh
│   │   │           └── diary_tracker
│   │   │               ├── common
│   │   │               │   ├── dto
│   │   │               │   │   └── ErrorResponse.java
│   │   │               │   ├── exceptions
│   │   │               │   │   └── EntityNotFoundException.java
│   │   │               │   └── GlobalExceptionHandler.java
│   │   │               ├── config
│   │   │               │   ├── OpenApiConfig.java
│   │   │               │   └── SecurityConfig.java
│   │   │               ├── diary
│   │   │               │   ├── DiaryController.java
│   │   │               │   ├── DiaryExceptionHandler.java
│   │   │               │   ├── DiaryRepository.java
│   │   │               │   ├── DiaryService.java
│   │   │               │   └── types
│   │   │               │       ├── dto
│   │   │               │       │   ├── requests
│   │   │               │       │   │   └── CreateDiaryRequest.java
│   │   │               │       │   └── response
│   │   │               │       │       └── DiaryResponse.java
│   │   │               │       ├── entity
│   │   │               │       │   └── Diary.java
│   │   │               │       └── exceptions
│   │   │               │           └── DiaryNotFoundException.java
│   │   │               ├── DiaryTrackerApplication.java
│   │   │               ├── entry
│   │   │               │   ├── DiaryEntryController.java
│   │   │               │   ├── EntryExceptionHandler.java
│   │   │               │   ├── EntryRepository.java
│   │   │               │   ├── EntryService.java
│   │   │               │   └── types
│   │   │               │       ├── dto
│   │   │               │       │   ├── request
│   │   │               │       │   │   └── CreateDiaryEntryRequest.java
│   │   │               │       │   └── response
│   │   │               │       │       └── EntryResponse.java
│   │   │               │       ├── entity
│   │   │               │       │   ├── ContentType.java
│   │   │               │       │   └── Entry.java
│   │   │               │       └── exceptions
│   │   │               │           └── EntryNotFoundException.java
│   │   │               ├── security
│   │   │               │   ├── CustomUserDetailsService.java
│   │   │               │   ├── JwtAuthFilter.java
│   │   │               │   ├── JwtService.java
│   │   │               │   └── UserWrapper.java
│   │   │               └── user
│   │   │                   ├── AuthController.java
│   │   │                   ├── types
│   │   │                   │   ├── dto
│   │   │                   │   │   ├── request
│   │   │                   │   │   │   ├── LoginRequest.java
│   │   │                   │   │   │   └── RegisterRequest.java
│   │   │                   │   │   └── response
│   │   │                   │   │       ├── LoginResponse.java
│   │   │                   │   │       └── SignedUserResponse.java
│   │   │                   │   ├── entity
│   │   │                   │   │   └── User.java
│   │   │                   │   └── exceptions
│   │   │                   │       ├── InvalidCredentialsException.java
│   │   │                   │       └── UsernameAlreadyExistsException.java
│   │   │                   ├── UserExceptionHandler.java
│   │   │                   ├── UserRepository.java
│   │   │                   └── UserService.java
│   │   └── resources
│   │       ├── application-dev.yaml
│   │       ├── application-prod.yaml
│   │       ├── application-test.yaml
│   │       ├── application.yaml
│   │       └── db
│   │           └── migration
│   │               ├── V1__init_users_table.sql
│   │               ├── V2__init_diaries_table.sql
│   │               └── V3__init_diary_entries.sql
│   └── test
│       └── java
│           └── com
│               └── abssh
│                   └── diary_tracker
│                       ├── DiaryTrackerApplicationTests.java
│                       ├── IntegrationTest.java
│                       ├── TestcontainersConfiguration.java
│                       ├── TestDiaryTrackerApplication.java
│                       └── user
│                           ├── AuthControllerTest.java
│                           ├── UserRepositoryTest.java
│                           └── UserServiceTest.java
│
│
├── compose.yml
├── Dockerfile
├── .env.example
├── .gitignore
├── pom.xml
└── README.md
```

## Prerequisites

- Java 25 (JDK)
- Maven
- Podman with Compose support

## Getting Started

### 1. Clone and configure environment

```bash
git clone <repo-url>
cd diary-tracker
cp .env.example .env.dev
```

Fill in `.env.dev` with your local values:

```bash
DB_NAME=diarytracker_dev
DB_USERNAME=postgres
DB_PASSWORD=devpassword123
JWT_SECRET=<generate with: openssl rand -base64 64 | tr -d '\n'>
```

### 2. Run locally (dev)

```bash
make dev-run
```

Starts the dev Postgres container, waits for it to be healthy, runs Flyway migrations, and starts the app with the `dev` Spring profile.

The API will be available at `http://localhost:8070` and Swagger UI at `http://localhost:8070/swagger-ui.html`.

### 3. Run the full stack (prod-like, containerized)

```bash
cp .env.example .env.prod
# fill in production values
make prod-run ENV=prod
```

Builds the application image and runs it alongside Postgres via `compose.prod.yaml`.

### 4. Run tests

```bash
make test
```

Integration tests use Testcontainers to spin up a disposable Postgres instance automatically — no manual database setup required.

## Database Migrations

Schema changes are managed with Flyway. Migration files live in `src/main/resources/db/migration` and follow the naming convention `V{number}__description.sql`.

```bash
make db-migrate ENV=dev    # run pending migrations
make db-clean ENV=dev      # drop, recreate, and re-migrate the database
```

> **Never edit a migration file once it has been applied.** Flyway checksums each file — fix mistakes by adding a new migration.

## Environment Profiles

| Profile | `.env` file | Compose file | Notes |
|---------|-------------|--------------|-------|
| `dev` | `.env.dev` | `compose.dev.yaml` | App runs locally via Maven; only Postgres is containerized |
| `test` | n/a | n/a | Testcontainers manages its own database automatically |
| `prod` | `.env.prod` | `compose.prod.yaml` | Fully containerized: app + Postgres |

Secrets are never baked into the Docker image — they are injected at container runtime via `env_file`.

## Testing

The test suite uses Spring Boot integration tests backed by Testcontainers:

- `UserRepositoryTest` — repository layer: save, find, exists
- `UserServiceTest` — service layer: register and login flows
- `AuthControllerTest` — HTTP layer: request validation, status codes, error responses

All tests are transactional and roll back after each test method, so they are fully isolated with no shared state.

## Roadmap

- Refresh token / re-authentication flow
- Frontend application
- Object storage (S3/MinIO) for diary entry attachments
- Roles and permissions beyond basic authenticated access
- Password reset flow
- Archived / owner-less entries