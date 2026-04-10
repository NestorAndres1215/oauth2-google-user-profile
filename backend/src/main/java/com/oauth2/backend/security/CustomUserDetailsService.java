package com.oauth2.backend.security;

import com.oauth2.backend.exception.ResourceNotFoundException;
import com.oauth2.backend.model.User;
import com.oauth2.backend.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login)  {

        User user = userRepository.findByUsername(login)
                .orElseGet(() -> userRepository.findByEmail(login)
                        .orElseThrow(() -> new ResourceNotFoundException("Invalid User")));
        return new CustomUserDetails(user);
    }

}