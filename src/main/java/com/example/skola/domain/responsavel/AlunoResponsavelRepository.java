package com.example.skola.domain.responsavel;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlunoResponsavelRepository extends JpaRepository<AlunoResponsavel, AlunoResponsavelId> {
    List<AlunoResponsavel> findByAlunoIdAndPodeRetirarTrue(Long alunoId);
}
