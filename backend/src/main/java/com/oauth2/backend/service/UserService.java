package com.oauth2.backend.service;

import com.oauth2.backend.model.User;
import com.oauth2.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User saveOrUpdateGoogleUser(User user) {
        Optional<User> existingUser = userRepository.findByEmail(user.getEmail());

        if (existingUser.isPresent()) {
            User u = existingUser.get();
            u.setName(user.getName());
            u.setPicture(user.getPicture());
            u.setLocale(user.getLocale());
            u.setGivenName(user.getGivenName());
            u.setFamilyName(user.getFamilyName());
            u.setProvider(user.getProvider());
            u.setProviderId(user.getProviderId());
            u.setEmailVerified(user.isEmailVerified());
            u.setAccessToken(user.getAccessToken());
            return userRepository.save(u);
        } else {
            return userRepository.save(user);
        }
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User getProfile(Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated()) {
            throw new RuntimeException("Not authenticated");
        }

        String username = authentication.getName();

        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        user.setPassword(null);

        return user;
    }
}
