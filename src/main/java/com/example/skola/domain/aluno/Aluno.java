package com.example.skola.domain.aluno;

import com.example.skola.domain.responsavel.AlunoResponsavel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "alunos")
@Data
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long escolaId;
    private UUID usuario_id;
    private Long turmaAtualId;

    private String nome;
    @Column(unique = true)
    private String matricula;
    private LocalDate dataNasc;
    private LocalDate dataMatricula;
    @Column(unique = true)
    private String rg;
    @Column(unique = true)
    private String cpf;
    private String endereco;
    private String necessidadesEspeciais;

    @OneToOne(mappedBy = "aluno", cascade = CascadeType.ALL)
    private FichaSaude fichaSaude;

    @OneToMany(mappedBy = "aluno")
    @JsonIgnore
    private List<AlunoResponsavel> responsaveis;
}
