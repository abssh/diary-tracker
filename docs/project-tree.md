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
│   │   │   │       ├── CreateEntryRequest.java
│   │   │   │       ├── UpdateEntryRequest.java
│   │   │   │       └── EntryResponse.java
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
└── README.md
```