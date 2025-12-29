package com.example.skola.domain.aluno.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.util.UUID;

public record CadastroDTO(

        @NotNull(message = "O ID da escola é obrigatório")
        Long escolaId,

        //@NotNull(message = "O ID do usuário é obrigatório")
        //UUID usuarioId,

        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotNull(message = "A data de nascimento é obrigatória")
        LocalDate dataNasc,

        @NotBlank(message = "O RG é obrigatório")
        String rg,

        @NotBlank(message = "O CPF é obrigatório")
        String cpf,

        @NotBlank(message = "O endereco é obrigatório")
        String endereco,

        String necessidadesEspeciais,

        DadosFichaSaudeDTO fichaSaude
) {}
