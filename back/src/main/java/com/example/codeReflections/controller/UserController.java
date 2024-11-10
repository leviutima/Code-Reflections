package com.example.codeReflections.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.codeReflections.dto.userDto.DadosAtualizarUsuario;
import com.example.codeReflections.dto.userDto.DadosCadastroUsuario;
import com.example.codeReflections.dto.userDto.DadosDetalhamentoUsuario;
import com.example.codeReflections.dto.userDto.DadosListagemUsuario;
import com.example.codeReflections.models.User;
import com.example.codeReflections.repository.UserRepository;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("usuarios")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity create(@RequestBody @Valid DadosCadastroUsuario dados, UriComponentsBuilder uriBuilder) {
        var usuario = new User(dados);
        repository.save(usuario);

        var uri = uriBuilder.path("/usuarios/{id}").buildAndExpand(usuario.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoUsuario(usuario));
    }

    @GetMapping
    public ResponseEntity<List<DadosListagemUsuario>> read() {
        List<DadosListagemUsuario> usuarios = repository.findAll()
                .stream()
                .map(DadosListagemUsuario::new)
                .toList();
        return ResponseEntity.ok(usuarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity readOne(@PathVariable Long id) {
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<DadosDetalhamentoUsuario> update(
            @PathVariable Long id,
            @RequestBody @Valid DadosAtualizarUsuario dados) {

        var usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.atualizarInformacoes(dados);
        repository.save(usuario);

        return ResponseEntity.ok(new DadosDetalhamentoUsuario(usuario));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
