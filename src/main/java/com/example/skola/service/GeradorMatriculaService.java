package com.example.skola.service;

import com.example.skola.domain.aluno.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Year;

@Service
public class GeradorMatriculaService {

    @Autowired
    private AlunoRepository repository;

    public String geradorMatricula(Long escolaId) {

        String anoAtual = String.valueOf(Year.now().getValue());

        String ultimaMatricula = repository.buscarUltimaMatriculaPorEscola(escolaId, anoAtual + "%");

        if (ultimaMatricula == null) {
            return anoAtual + "0001";
        }

        String sequencialString = ultimaMatricula.substring(4);
        int sequencial = Integer.parseInt(sequencialString);

        sequencial++;

        String novaSequencia = String.format("%04d", sequencial);

        return anoAtual + novaSequencia;
    }
}