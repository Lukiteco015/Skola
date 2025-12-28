package com.example.skola.domain.funcionario;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "funcionarios")
@Inheritance(strategy = InheritanceType.JOINED)
@Data
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long escolaId;
    private UUID usuarioId;
    private String nome;
    @Column(unique = true)
    private String cpf;
    @Column(unique = true)
    private String rg;
    private String telefone;
    private String endereco;
    private LocalDate data_contratacao;

    @Enumerated(EnumType.STRING)
    private TipoFuncionario tipoFuncionario;
}
