# Diary Tracker

A REST API backend for a personal diary/journaling application, built with Spring Boot and PostgreSQL.

## Tech Stack

- **Java 25**
- **Spring Boot** : Web, Security, Data JPA, Validation, Actuator
- **PostgreSQL 16** : primary datastore
- **Flyway** : database schema migrations
- **JWT** (jjwt) : stateless authentication
- **Lombok** : boilerplate reduction
- **Podman Compose** : containerized local development and deployment
- **Maven** : build tool
- **Make** : build tool to integrate run + migration logic


## Features
* username password login : what you write should stay personal
* read and write diary entries: obviously
* multiple diary a person: one is just not enough
* different format support

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
│   │   │               ├── config
│   │   │               │   └── SecurityConfig.java
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
│   │   │                   │   │   └── Udiary-tracker/
├── docs/
│   ├── consider.md
│   └── project-tree.md
│
├── script/
│   └── bash/
│       └── shell-profiling.sh
│
├── src/
│   ├── main/
│   │   ├── java/com/abssh/diary_tracker/ (TODO)
│   │   │   ├── DiaryTrackerApplication.java
│   │   │   │
│   │   │   ├── config/
│   │   │   │   ├── SecurityConfig.java
│   │   │   │   ├── OpenApiConfig.java
│   │   │   │   └── JpaConfig.java
│   │   │   │
│   │   │   ├── security/
│   │   │   │   ├── JwtAuthFilter.java
│   │   │   │   ├── JwtService.java
│   │   │   │   ├── CustomUserDetailsService.java
|   |   |   |   └── UserWrapper.java
│   │   │   │
│   │   │   ├── user/
│   │   │   │   ├── User.java                  (entity)
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── UserService.java
│   │   │   │   ├── AuthController.java        (register/login)
│   │   │   │   └── dto/
│   │   │   │       ├── response
│   │   │   │       │   └── AuthResponse.java
│   │   │   │       │
│   │   │   │       └── request
│   │   │   │           ├── RegisterRequest.java
│   │   │   │           └── LoginRequest.java
│   │   │   │           
│   │   │   ├── entry/
│   │   │   │   ├── DiaryEntry.java            (entity)
│   │   │   │   ├── DiaryEntryRepository.java
│   │   │   │   ├── DiaryEntryService.java
│   │   │   │   ├── DiaryEntryController.java
│   │   │   │   └── dto/
│   │   │   │       ├── response
│   │   │   │       │   └── EntryResponse.java
│   │   │   │       │
│   │   │   │       └── request
│   │   │   │           ├── CreateEntryRequest.java
│   │   │   │           └── UpdateEntryRequest.java
|   |   |   |
│   │   │   ├── diary/
│   │   │   │   ├── Diary.java            (entity)
│   │   │   │   ├── DiaryRepository.java
│   │   │   │   ├── DiaryService.java
│   │   │   │   ├── DiaryController.java
│   │   │   │   └── dto/
│   │   │   │       ├── response
│   │   │   │       │   └── DiaryResponse.java
│   │   │   │       │
│   │   │   │       └── request
│   │   │   │           ├── CreateDiaryRequest.java
│   │   │   │           └── UpdateDiaryRequest.java
│   │   │   │
│   │   │   ├── common/
│   │   │   │   ├── exception/
│   │   │   │   │   ├── UsernameAlreadyExistsException.java
│   │   │   │   │   └── UnauthorizedAccessException.java
│   │   │   │   │
│   │   │   │   └── GlobalExceptionHadler.java
│   │   │   │
│   │   │   └── util/
│   │   │       └── DateUtils.java
│   │   │
│   │   └── resources/
│   │       ├── application.yml
│   │       ├── application-dev.yml
│   │       ├── application-test.yml
│   │       ├── application-prod.yml
│   │       └── db/migration/
│   │           ├── V1__init_users_table.sql
│   │           └── V2__init_diary_entries_table.sql
│   │
│   └── test/
│       └── java/com/abssh/diary_tracker/
│           ├── entry/
│           │   ├── DiaryEntryServiceTest.java
│           │   └── DiaryEntryControllerIT.java   (Testcontainers)
│           └── user/
│               └── AuthControllerIT.java
│
│
├── compose.yml
├── Dockerfile
├── .env.example
├── .gitignore
├── pom.xml
└── README.mdser.java
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
make dev-run # TODO add docker support
```
 
This starts the dev Postgres container, waits for it to be healthy, runs Flyway migrations, and starts the app with the `dev` Spring profile.
 
The API will be available at `http://localhost:8070`.
 
### 3. Run the full stack (prod-like, containerized)
 
```bash
cp .env.example .env.prod
# fill in production values
make prod-run
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
 
> **Never edit a migration file once it has been applied.** Flyway checksums each file; fix mistakes by adding a new migration.
 
## Authentication
 
Authentication is stateless and JWT-based.
 
| Endpoint | Auth required | Description |
|---|---|---|
| `POST /api/v1/auth/signup` | No | Create a new user account |
| `POST /api/v1/auth/login` | No | Exchange credentials for a JWT |
| All other endpoints | Yes | Require `Authorization: Bearer <token>` header |
 
Tokens are signed with `JWT_SECRET` and identify the user by ID (UUID), not username. Tokens expire after a configurable duration (`jwt.expiration-ms`, default 24h).
 
## Environment Profiles
 
The app supports three Spring profiles, each with its own `.env` and Compose file:
 
| Profile | `.env` file | Compose file | Notes |
|---|---|---|---|
| `dev` | `.env.dev` | `compose.dev.yaml` | App runs locally via Maven; only Postgres is containerized |
| `test` | n/a | n/a | Testcontainers manages its own database automatically |
| `prod` | `.env.prod` | `compose.prod.yaml` | Fully containerized: app + Postgres |
 
Secrets are never baked into the Docker image — they're injected at container runtime via `env_file`.
 
## Health Checks
 
Spring Boot Actuator exposes basic health/info endpoints:
 
```
GET /actuator/health
```
 
## Roadmap / Not Yet Implemented
 
- Frontend application
- Object storage (S3/MinIO) integration for diary entry attachments
- Roles/permissions beyond basic authenticated access
- Password reset flow


