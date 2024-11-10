package com.example.codeReflections.dto.userDto;

import com.example.codeReflections.models.User;

public record DadosListagemUsuario(Long id, String nome, String sobrenome, String telefone, String email) {
    
    public DadosListagemUsuario (User users) {
        this(
                users.getId(),
                users.getNome(),
                users.getSobrenome(),
                users.getTelefone(),
                users.getEmail());
    }

}
