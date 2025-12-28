package com.example.skola.service.escola;

import com.example.skola.domain.escola.Escola;
import com.example.skola.domain.escola.EscolaRepository;
import com.example.skola.domain.escola.dto.CadastroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EscolaService {


    @Autowired
    private EscolaRepository repository;

    @Transactional
    public Escola cadastrar(CadastroDTO dto) {

        String cnpjLimpo = dto.cnpj().replaceAll("[^0-9]", "");

        if(repository.existsByCnpj(cnpjLimpo)) {
            throw new IllegalArgumentException("Já existe uma escola cadastrada com este CNPJ.");
        }

        Escola escola = new Escola();
        escola.setNome(dto.nome());
        escola.setCnpj(cnpjLimpo);
        escola.setLogoUrl(dto.logoUrl());

        return repository.save(escola);
    }
}
