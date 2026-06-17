# Project tree sketch

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
├── src/
│   ├── main/
│   │   ├── java/com/yourname/diarytracker/ (TODO)
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
│   │   │   │   └── CustomUserDetailsService.java
│   │   │   │
│   │   │   ├── user/
│   │   │   │   ├── User.java                  (entity)
│   │   │   │   ├── UserRepository.java
│   │   │   │   ├── UserService.java
│   │   │   │   ├── AuthController.java        (register/login)
│   │   │   │   └── dto/
│   │   │   │       ├── RegisterRequest.java
│   │   │   │       ├── LoginRequest.java
│   │   │   │       └── AuthResponse.java
│   │   │   │
│   │   │   ├── entry/
│   │   │   │   ├── DiaryEntry.java            (entity)
│   │   │   │   ├── DiaryEntryRepository.java
│   │   │   │   ├── DiaryEntryService.java
│   │   │   │   ├── DiaryEntryController.java
│   │   │   │   └── dto/
│   │   │   │       ├── CreateEntryRequest.java
│   │   │   │       ├── UpdateEntryRequest.java
│   │   │   │       └── EntryResponse.java
│   │   │   │
│   │   │   ├── common/
│   │   │   │   └── exception/
│   │   │   │       ├── GlobalExceptionHandler.java
│   │   │   │       ├── ResourceNotFoundException.java
│   │   │   │       └── UnauthorizedAccessException.java
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
│       └── java/com/yourname/diarytracker/
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
├── pom.xml (or build.gradle)
└── README.md  (TODO)
```