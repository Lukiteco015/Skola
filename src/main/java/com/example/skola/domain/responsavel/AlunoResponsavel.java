package com.example.skola.domain.responsavel;

import com.example.skola.domain.aluno.Aluno;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "aluno_responsavel")
@Data
public class AlunoResponsavel {

    @EmbeddedId
    private AlunoResponsavelId id = new AlunoResponsavelId();

    @ManyToOne
    @MapsId("alunoId")
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    @ManyToOne
    @MapsId("responsavelId")
    @JoinColumn(name = "responsavel_id")
    private Responsavel responsavel;

    private String parentesco;
    private Boolean podeRetirar;
}
