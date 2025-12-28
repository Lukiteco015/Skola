package com.example.skola.domain.responsavel;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Table(name = "responsaveis")
@Data
public class Responsavel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long escolaId;
    private String nome;
    @Column(unique = true)
    private String cpf;
    private String telefone;

    @OneToMany(mappedBy = "responsavel")
    private List<AlunoResponsavel> alunosVinculados;
}
