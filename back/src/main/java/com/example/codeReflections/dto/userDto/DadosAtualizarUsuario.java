package com.example.codeReflections.dto.userDto;

public record DadosAtualizarUsuario(
        Long id,
        String nome,
        String sobrenome,
        String email,
        String senha) {
}
