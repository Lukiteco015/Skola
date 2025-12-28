package com.example.skola.domain.funcionario.professor;

import com.example.skola.domain.funcionario.Funcionario;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "professores")
@Data
@EqualsAndHashCode(callSuper = true)
public class Professor extends Funcionario {
    private String especialidade;
    private String formacao;
}
