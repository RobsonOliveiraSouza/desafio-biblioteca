package br.com.robsonsouza.desafiobiblioteca.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UsuarioDto {
    private Long id;
    private Long nome;
    private Date email;
    private Date dataCadastro;
    private String telefone;
}
