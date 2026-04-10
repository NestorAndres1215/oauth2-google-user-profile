package com.oauth2.backend.controller;

import com.oauth2.backend.model.User;
import com.oauth2.backend.security.JwtUtil;
import com.oauth2.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final JwtUtil jwtTokenProvider;



    @GetMapping("/user")
    public Map<String, Object> getUserProfile(@AuthenticationPrincipal OAuth2User principal) {
        if (principal == null) throw new RuntimeException("No authenticated user");

        User user = userService.findByEmail(principal.getAttribute("email"))
                .orElseThrow(() -> new RuntimeException("User not found"));

        String token = jwtTokenProvider.generateToken(user);

        return Map.of(
                "name", user.getName(),
                "email", user.getEmail(),
                "picture", user.getPicture(),
                "provider", user.getProvider(),
                "token", token
        );
    }
}
