package com.example.skola.domain.aluno;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "fichas_saude")
@Data
public class FichaSaude {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "aluno_id", nullable = false)
    private Aluno aluno;

    private String alergias;
    private String medicamentos;
    private String contatoEmergencia;
}
