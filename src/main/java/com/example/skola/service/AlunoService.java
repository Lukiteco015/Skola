package com.example.skola.service;

import com.example.skola.domain.aluno.Aluno;
import com.example.skola.domain.aluno.AlunoRepository;
import com.example.skola.domain.aluno.FichaSaude;
import com.example.skola.domain.aluno.dto.AtualizacaoDTO;
import com.example.skola.domain.aluno.dto.CadastroDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    @Autowired
    private GeradorMatriculaService geradorMatricula;

    @Autowired
    private EscolaService escolaService;

    @Transactional
    public Aluno cadastrar(CadastroDTO dto) {

        escolaService.buscarPorId(dto.escolaId());

        if (repository.existsByCpf(dto.cpf())) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com este CPF.");
        }

        if (dto.rg() != null && repository.existsByRg(dto.rg())) {
            throw new IllegalArgumentException("Já existe um aluno cadastrado com este RG.");
        }

        String novaMatricula = geradorMatricula.geradorMatricula(dto.escolaId());

        Aluno aluno = new Aluno();
        aluno.setEscolaId(dto.escolaId());
        aluno.setNome(dto.nome());
        aluno.setMatricula(novaMatricula);
        aluno.setDataNasc(dto.dataNasc());
        aluno.setRg(dto.rg());
        aluno.setCpf(dto.cpf());
        aluno.setEndereco(dto.endereco());
        aluno.setNecessidadesEspeciais(dto.necessidadesEspeciais());
        aluno.setDataMatricula(java.time.LocalDate.now());
        //aluno.setUsuario_id(dto.usuarioId());

        FichaSaude ficha = new FichaSaude();
        if (dto.fichaSaude() != null) {
            ficha.setAlergias(dto.fichaSaude().alergias());
            ficha.setMedicamentos(dto.fichaSaude().medicamentos());
            ficha.setContatoEmergencia(dto.fichaSaude().contatoEmergencia());
        }
        aluno.setFichaSaude(ficha);
        ficha.setAluno(aluno);

        return repository.save(aluno);
    }

    public Page<Aluno> listarTodos(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    public Aluno buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Aluno não encontrado com ID: " + id));
    }

    @Transactional
    public Aluno atualizar(Long id, AtualizacaoDTO dto) {
        Aluno aluno = buscarPorId(id);

        if (dto.nome() != null) aluno.setNome(dto.nome());
        if (dto.dataNasc() != null) aluno.setDataNasc(dto.dataNasc());
        if (dto.endereco() != null) aluno.setEndereco(dto.endereco());
        if (dto.necessidadesEspeciais() != null) aluno.setNecessidadesEspeciais(dto.necessidadesEspeciais());

        if (dto.fichaSaude() != null) {
            FichaSaude ficha = aluno.getFichaSaude();
            ficha.setAlergias(dto.fichaSaude().alergias());
            ficha.setMedicamentos(dto.fichaSaude().medicamentos());
            ficha.setContatoEmergencia(dto.fichaSaude().contatoEmergencia());
        }

        return repository.save(aluno);

    }

    @Transactional
    public void excluir(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Aluno não encontrado com ID: " + id);
        }
        repository.deleteById(id);
    }

}
