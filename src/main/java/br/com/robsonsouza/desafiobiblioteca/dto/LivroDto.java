package br.com.robsonsouza.desafiobiblioteca.dto;

import lombok.Data;

import java.util.Date;

@Data
public class LivroDto {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private Date dataPublicacao;
    private String categoria;
}
