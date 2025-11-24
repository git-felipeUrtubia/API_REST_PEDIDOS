package com.empresa.api_level_up.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "password_reset_token")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordResetToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Token único de recuperación
    @Column(nullable = false, unique = true)
    private String token;

    // Email del usuario que solicitó reset
    @Column(nullable = false)
    private String email;

    // Expiración del token (ej 30 min)
    @Column(nullable = false)
    private LocalDateTime expiration;
}

