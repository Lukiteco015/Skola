package com.example.skola.controller;

import com.example.skola.domain.escola.Escola;
import com.example.skola.domain.escola.dto.AtualizacaoDTO;
import com.example.skola.domain.escola.dto.CadastroDTO;
import com.example.skola.service.EscolaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/api/escolas")
public class EscolaController {

    @Autowired
    EscolaService service;

    @GetMapping
    public ResponseEntity<Page<Escola>> listar(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao
    ) {
        var pagina = service.listarTodas(paginacao);
        return ResponseEntity.ok(pagina);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Escola> detalhar(@PathVariable Long id) {
        var escola = service.buscarPorId(id);
        return ResponseEntity.ok(escola);
    }

    @PostMapping
    public ResponseEntity<Escola> cadastrar(@RequestBody @Valid CadastroDTO dto, UriComponentsBuilder uriComponentsBuilder) {

        Escola escola = service.cadastrar(dto);

        URI url = uriComponentsBuilder.path("api/escolas/{id}").buildAndExpand(escola.getId()).toUri();

        return ResponseEntity.created(url).body(escola);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Escola> atualizar(@PathVariable Long id, @RequestBody AtualizacaoDTO dto) {
        Escola escola = service.atualizar(id, dto);
        return ResponseEntity.ok(escola);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
