package com.example.skola.controller;

import com.example.skola.domain.aluno.Aluno;
import com.example.skola.domain.aluno.dto.AtualizacaoDTO;
import com.example.skola.domain.aluno.dto.CadastroDTO;
import com.example.skola.service.AlunoService;
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
@RequestMapping("/api/alunos")
public class AlunoController {

    @Autowired
    private AlunoService service;

    @PostMapping
    public ResponseEntity<Aluno> cadastrar(@RequestBody @Valid CadastroDTO dto,
                                           UriComponentsBuilder uriBuilder) {
        Aluno aluno = service.cadastrar(dto);
        URI uri = uriBuilder.path("/api/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
        return ResponseEntity.created(uri).body(aluno);
    }

    @GetMapping
    public ResponseEntity<Page<Aluno>> listar(
            @PageableDefault(size = 10, sort = "nome") Pageable paginacao) {
        return ResponseEntity.ok(service.listarTodos(paginacao));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> detalhar(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Aluno> atualizar(@PathVariable Long id,
                                           @RequestBody @Valid AtualizacaoDTO dto) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }
}
