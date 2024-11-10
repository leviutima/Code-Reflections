package com.example.codeReflections.dto.userDto;

import com.example.codeReflections.models.User;

public record DadosDetalhamentoUsuario( Long id, String nome, String sobrenome, String email, String senha) {
    
    public DadosDetalhamentoUsuario (User users) {
        this(
                users.getId(),
                users.getNome(),
                users.getSobrenome(),
                users.getTelefone(),
                users.getEmail());
    }
}
