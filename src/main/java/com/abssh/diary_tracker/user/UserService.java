package com.abssh.diary_tracker.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.abssh.diary_tracker.common.exceptions.InvalidCredentialsException;
import com.abssh.diary_tracker.common.exceptions.UsernameAlreadyExistsException;
import com.abssh.diary_tracker.security.JwtService;
import com.abssh.diary_tracker.user.dto.request.LoginRequest;
import com.abssh.diary_tracker.user.dto.request.RegisterRequest;
import com.abssh.diary_tracker.user.dto.response.LoginResponse;
import com.abssh.diary_tracker.user.dto.response.SignedUserResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public SignedUserResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.username())) {
            throw new UsernameAlreadyExistsException(request.username());
        }

        String passwordHash = passwordEncoder.encode(request.plainPassword());
        System.out.println("Password hash: " + passwordHash + " plain: " + request.plainPassword()); // Debugging line

        User user = User.builder()
            .username(request.username())
            .passwordHash(passwordHash)
            .build();

        User saved = userRepository.save(user);
        return SignedUserResponse.from(saved);
    }

    public LoginResponse login(LoginRequest request) {
        User user = userRepository
            .findByUsername(request.username())
            .orElseThrow(InvalidCredentialsException::new);

        if (!passwordEncoder.matches(request.plainPassword(), user.getPasswordHash())) {
            throw new InvalidCredentialsException();
        }

        String token = jwtService.generateToken(user.getId());
        return new LoginResponse(token, user.getUsername());
    }

}
