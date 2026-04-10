package com.oauth2.backend.security;


import com.oauth2.backend.model.User;
import com.oauth2.backend.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
public class OAuth2LoginSuccessHandler implements AuthenticationSuccessHandler {


    private final UserService userService;
    private final  JwtUtil jwtTokenProvider;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException {

        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();

        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");
        String picture = oAuth2User.getAttribute("picture");
        String givenName = oAuth2User.getAttribute("given_name");
        String familyName = oAuth2User.getAttribute("family_name");
        String locale = oAuth2User.getAttribute("locale");
        String providerId = oAuth2User.getAttribute("sub");
        Boolean emailVerified = Boolean.parseBoolean(oAuth2User.getAttribute("email_verified").toString());

        User user = User.builder()
                .email(email)
                .name(name)
                .picture(picture)
                .givenName(givenName)
                .familyName(familyName)
                .locale(locale)
                .provider("google")
                .providerId(providerId)
                .emailVerified(emailVerified)
                .build();

        user = userService.saveOrUpdateGoogleUser(user);

        String token = jwtTokenProvider.generateToken(user);
        String encodedToken = URLEncoder.encode(token, StandardCharsets.UTF_8);
        String redirectUrl = "http://localhost:4200/oauth2/redirect?token=" + encodedToken;
        response.sendRedirect(redirectUrl);

    }
}
