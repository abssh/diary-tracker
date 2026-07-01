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