package com.example.skola.domain.escola.dto;

import jakarta.validation.constraints.NotBlank;
import org.hibernate.validator.constraints.br.CNPJ;

public record CadastroDTO(
        @NotBlank(message = "O nome da escola é obrigatório!")
        String nome,

        @NotBlank(message = "O CNPJ é obrigatório!")
        @CNPJ(message = "CNPJ inválido")
        String cnpj,

        String logoUrl
) {}
