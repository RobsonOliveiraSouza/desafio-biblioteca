package br.com.robsonsouza.desafiobiblioteca.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UsuarioDto {
    private Long id;
    private Long nome;
    private String email;
    private LocalDateTime dataCadastro;
    private String telefone;
}