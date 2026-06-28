package com.abssh.diary_tracker.user;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.abssh.diary_tracker.IntegrationTest;

public class UserRepositoryTest extends IntegrationTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void shouldSaveUser() {
        var user = new User();
        user.setUsername("testuser");
        user.setPasswordHash("password");

        User saved = userRepository.save(user);
        assertThat(saved).isNotNull();
    }

    @Test
    void shouldFindUserByUsername() {
        var user = new User();
        user.setUsername("testuser2");
        user.setPasswordHash("password");

        userRepository.save(user);
        Optional<User> found = userRepository.findByUsername("testuser2");
        assertThat(found).isPresent();
    }

    @Test
    void shouldCheckIfUserExistsByUsername() {
        var user = new User();
        user.setUsername("testuser3");
        user.setPasswordHash("password");

        userRepository.save(user);
        boolean exists = userRepository.existsByUsername("testuser3");
        assertThat(exists).isTrue();
    }
}
