package com.abssh.diary_tracker.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.abssh.diary_tracker.IntegrationTest;
import com.abssh.diary_tracker.common.exceptions.InvalidCredentialsException;
import com.abssh.diary_tracker.common.exceptions.UsernameAlreadyExistsException;
import com.abssh.diary_tracker.user.dto.request.LoginRequest;
import com.abssh.diary_tracker.user.dto.request.RegisterRequest;
import com.abssh.diary_tracker.user.dto.response.LoginResponse;
import com.abssh.diary_tracker.user.dto.response.SignedUserResponse;

public class UserServiceTest extends IntegrationTest {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    /* Test Register */
    @Test
    void shouldRegisterUser() {
        RegisterRequest request = new RegisterRequest("testuser", "plainPassword123");
        SignedUserResponse response =  userService.register(request);

        // check response correctness
        assertThat(response.accessToken()).isNotBlank();
        assertThat(response.username()).isEqualTo("testuser");

        // check if user is saved
        Optional<User> found = userRepository.findByUsername("testuser");
        assertThat(found).isPresent();
        
        User saved = found.get();
        assertThat(saved.getId()).isNotNull();
        assertThat(saved.getUsername()).isEqualTo("testuser");
        assertThat(saved.getPasswordHash()).isNotEqualTo("plainPassword123");
        assertThat(saved.getCreatedAt()).isNotNull();
    }

    @Test
    void shouldThrowWhenUsernameAlreadyExists() {
        RegisterRequest request = new RegisterRequest("testuser", "plainPassword123");
        userService.register(request);

        assertThatThrownBy(() -> userService.register(request))
            .isInstanceOf(UsernameAlreadyExistsException.class);
    }

    /* Test Login */
    @Test
    void shouldLoginUser() {
        RegisterRequest setup = new RegisterRequest("testuser", "plainPassword123");
        userService.register(setup);

        LoginRequest request = new LoginRequest("testuser", "plainPassword123");
        LoginResponse response = userService.login(request);

        assertThat(response.accessToken()).isNotBlank();
        assertThat(response.username()).isEqualTo("testuser");
    }

    @Test
    void shouldThrowWhenLoginWithWrongPassword() {
        RegisterRequest setup = new RegisterRequest("testuser", "plainPassword123");
        userService.register(setup);

        LoginRequest request = new LoginRequest("testuser", "wrongPassword123");

        assertThatThrownBy(() -> userService.login(request))
            .isInstanceOf(InvalidCredentialsException.class);
    }

    void shouldThrowWhenLoginWithNonExsistentUsername() {
        LoginRequest request = new LoginRequest("nonExsistentUsername", "plainPassword123");

        assertThatThrownBy(() -> userService.login(request))
            .isInstanceOf(InvalidCredentialsException.class);
    }
    
}
