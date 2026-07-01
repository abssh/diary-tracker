package com.abssh.diary_tracker.user;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.abssh.diary_tracker.user.types.dto.request.LoginRequest;
import com.abssh.diary_tracker.user.types.dto.request.RegisterRequest;
import com.abssh.diary_tracker.user.types.dto.response.LoginResponse;
import com.abssh.diary_tracker.user.types.dto.response.SignedUserResponse;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    
    private final UserService userService;

    @PostMapping("signup")
    public ResponseEntity<SignedUserResponse> signUserUp(@Valid @RequestBody RegisterRequest entity) {
        
        SignedUserResponse response = userService.register(entity);
        return ResponseEntity
            .created(URI.create("/users/" + response.username())) /* un implemented /users/${username} */
            .body(response);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponse> logUserIn(@Valid @RequestBody LoginRequest entity) {
        LoginResponse response = userService.login(entity);
        return ResponseEntity.ok(response);
    }
    
}
