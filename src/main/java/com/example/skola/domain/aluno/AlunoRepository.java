package com.example.skola.domain.aluno;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param; // <--- Importante
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {

    @Query("SELECT MAX(a.matricula) FROM Aluno a WHERE a.escolaId = :escolaId AND a.matricula LIKE :anoPattern")
    String buscarUltimaMatriculaPorEscola(@Param("escolaId") Long escolaId, @Param("anoPattern") String anoPattern);

    boolean existsByMatricula(String matricula);
    boolean existsByCpf(String cpf);
    boolean existsByRg(String rg);
}