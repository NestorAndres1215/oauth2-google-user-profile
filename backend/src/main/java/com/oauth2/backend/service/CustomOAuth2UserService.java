package com.oauth2.backend.service;

import com.oauth2.backend.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserService userService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String, Object> attributes = oAuth2User.getAttributes();

        User user = User.builder()
                .email((String) attributes.get("email"))
                .name((String) attributes.get("name"))
                .picture((String) attributes.get("picture"))
                .givenName((String) attributes.get("given_name"))
                .familyName((String) attributes.get("family_name"))
                .locale((String) attributes.get("locale"))
                .provider("google")
                .providerId((String) attributes.get("sub"))
                .emailVerified(Boolean.parseBoolean(attributes.get("email_verified").toString()))
                .build();

        return (OAuth2User) userService.saveOrUpdateGoogleUser(user);
    }
}
