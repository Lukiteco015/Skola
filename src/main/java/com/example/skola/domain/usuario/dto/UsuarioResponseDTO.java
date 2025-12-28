package com.example.skola.domain.usuario.dto;

import com.example.skola.domain.usuario.Role;

import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String email,
        Long escolaId,
        Role role
) {}
