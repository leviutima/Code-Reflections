package com.example.codeReflections.models;

import com.example.codeReflections.dto.userDto.DadosAtualizarUsuario;
import com.example.codeReflections.dto.userDto.DadosCadastroUsuario;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "usuarios")
@Entity(name = "Usuario")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
    private String senha;

    public User(DadosCadastroUsuario dados) {
        this.nome = dados.nome();
        this.sobrenome = dados.sobrenome();
        this.telefone = dados.telefone();
        this.email = dados.email();
        this.senha = dados.senha();
    }

    public void atualizarInformacoes(DadosAtualizarUsuario dados) {
        this.nome = dados.nome();
        this.sobrenome = dados.sobrenome();
        this.email = dados.email();
        this.senha = dados.senha();
    }
}
