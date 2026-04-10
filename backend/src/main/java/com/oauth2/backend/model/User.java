package com.oauth2.backend.model;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String email;

    private String name;
    private String username;
    private String password;
    private String picture;

    private String provider;

    private String providerId;

    private String locale;

    private String givenName;

    private String familyName;

    private boolean emailVerified;

    private String role = "USER";

    private String status = "ACTIVE";
    @Column(length = 512)
    private String accessToken;
}