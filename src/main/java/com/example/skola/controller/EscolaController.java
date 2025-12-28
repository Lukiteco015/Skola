package com.example.skola.controller;

import com.example.skola.domain.escola.Escola;
import com.example.skola.domain.escola.dto.CadastroDTO;
import com.example.skola.service.escola.EscolaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/escolas")
public class EscolaController {

    @Autowired
    EscolaService service;

    @PostMapping
    public ResponseEntity<Escola> cadastrar(@RequestBody @Valid CadastroDTO dto, UriComponentsBuilder uriComponentsBuilder) {

        Escola escola = service.cadastrar(dto);

        URI url = uriComponentsBuilder.path("api/escolas/{id}").buildAndExpand(escola.getId()).toUri();

        return ResponseEntity.created(url).body(escola);
    }
}
