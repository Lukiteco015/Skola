package com.example.skola.controller;

import com.example.skola.domain.usuario.Usuario;
import com.example.skola.domain.usuario.UsuarioRepository;
import com.example.skola.domain.usuario.dto.UsuarioResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    UsuarioRepository repository;

    @GetMapping("/me")
    public ResponseEntity<UsuarioResponseDTO> getCurrentUser(@AuthenticationPrincipal Jwt token) {

        String usuarioIdStr = token.getClaim("sub");
        UUID usuarioId = UUID.fromString(usuarioIdStr);

        Usuario usuario = repository.findById(usuarioId)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado!"));

        UsuarioResponseDTO response = new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getEmail(),
                usuario.getEscolaId(),
                usuario.getRole()
        );
        return ResponseEntity.ok(response);
    }
}
