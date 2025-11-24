package com.empresa.api_level_up.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // para pruebas con Postman/React
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // ✅ RUTAS PÚBLICAS
                        .requestMatchers(
                                "/api/v1/passd/auth/solicitar-password",
                                "/api/v1/passd/auth/recuperar-password"
                        ).permitAll()

                        // (si tienes login/registro también deberían ser públicas)
                        .requestMatchers(
                                "/api/v1/**"
                        ).permitAll()

                        // ✅ TODO LO DEMÁS REQUIERE AUTH
                        .anyRequest().authenticated()
                );

        return http.build();
    }

}
