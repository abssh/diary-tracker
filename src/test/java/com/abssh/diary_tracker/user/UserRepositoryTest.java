package com.abssh.diary_tracker.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.abssh.diary_tracker.IntegrationTest;

import jakarta.persistence.EntityManager;

public class UserRepositoryTest extends IntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    private User testUser;

    @BeforeEach
    void setup() {
        testUser = new User();
        testUser.setUsername("testuser");
        testUser.setPasswordHash("hashedPassword");
    }

    @Test
    void shouldSaveUser() {
        User saved = userRepository.save(testUser);
        assertThat(saved).isNotNull();

        // forcing hibernate to write creation timestamp
        entityManager.flush();
        entityManager.refresh(saved);


        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getUsername()).isEqualTo("testuser");
        assertThat(saved.getCreatedAt()).isNotNull();
    }

    @Test
    void shouldFindUserByUsername() {
        userRepository.save(testUser);

        Optional<User> found = userRepository.findByUsername("testuser");
        assertThat(found).isPresent();
    }

    @Test
    void shouldCheckIfUserExistsByUsername() {
        userRepository.save(testUser);

        boolean exists = userRepository.existsByUsername("testuser");
        assertThat(exists).isTrue();
    }

    @Test
    void shouldReturnEmptyWhenUserNotFound() {
        assertThat(userRepository.findByUsername("ghost")).isEmpty();
    }

    void shouldReturnFalseWhenUserDoseNotExsist() {
        assertThat(userRepository.existsByUsername("ghost")).isFalse();
    }
}
