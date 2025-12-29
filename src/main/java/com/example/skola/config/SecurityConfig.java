package com.example.skola.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import javax.crypto.spec.SecretKeySpec; // Importante para HS256

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${supa.jwt.secret}")
    private String jwtSecret;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // Rotas 100% Públicas (Login, Swagger, etc)
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/api/auth/**"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/escolas/**").permitAll()
                        .requestMatchers("/api/alunos/**").permitAll()

                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwt -> jwt.decoder(jwtDecoder())));

        return http.build();
    }


    @Bean
    public JwtDecoder jwtDecoder() {
        byte[] secretBytes = jwtSecret.getBytes();
        SecretKeySpec secretKey = new SecretKeySpec(secretBytes, "HmacSHA256");

        return NimbusJwtDecoder.withSecretKey(secretKey).build();
    }
}