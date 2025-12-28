package com.example.skola.domain.escola;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EscolaRepository extends JpaRepository<Escola, Long> {
    boolean existsByCnpj(String cnpj);
    Optional<Escola> findByCnpj(String cnpj);
}
