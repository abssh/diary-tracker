package com.abssh.diary_tracker.security;

import java.util.UUID;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.abssh.diary_tracker.user.UserRepository;
import com.abssh.diary_tracker.user.types.entity.User;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService{

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
            .findByUsername(username)
            .orElseThrow(() -> UsernameNotFoundException.fromUsername(username));

        return new UserWrapper(user);
    }

    public UserDetails loadUserByUserId(UUID userId) throws UsernameNotFoundException {
        User user = userRepository
            .findById(userId)
            .orElseThrow(() -> new UsernameNotFoundException("user not found user_id: " + userId.toString()));
        
        return new UserWrapper(user);
    }

}
