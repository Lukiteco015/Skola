package com.example.skola.domain.aluno.dto;

import java.time.LocalDate;

public record AtualizacaoDTO(
        String nome,
        LocalDate dataNasc,
        String endereco,
        String necessidadesEspeciais,
        DadosFichaSaudeDTO fichaSaude
) {
}
