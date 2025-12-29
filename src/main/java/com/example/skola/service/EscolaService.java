package com.example.skola.service;

import com.example.skola.domain.escola.Escola;
import com.example.skola.domain.escola.EscolaRepository;
import com.example.skola.domain.escola.dto.AtualizacaoDTO;
import com.example.skola.domain.escola.dto.CadastroDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @Transactional
    public Escola atualizar(Long id, AtualizacaoDTO dto) {

        Escola escola = buscarPorId(id);

        if(dto.nome() != null && !dto.nome().isBlank()) {
            escola.setNome(dto.nome());
        }
        if (dto.logoUrl() != null) {
            escola.setLogoUrl(dto.logoUrl());
        }

        return repository.save(escola);
    }

    @Transactional
    public void excluir(Long id) {
        if(!repository.existsById(id)) {
            throw new EntityNotFoundException("Escola não encontrada com ID: " + id);
        }
        repository.deleteById(id);
    }

    public Page<Escola> listarTodas(Pageable pagina) {
        return repository.findAll(pagina);
    }

    public Escola buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Escola não encontrada com ID: " + id));
    }
}
