package br.com.robsonsouza.desafiobiblioteca.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LivroDto {
    private Long id;
    private String titulo;
    private String autor;
    private String isbn;
    private LocalDateTime dataPublicacao;
    private String categoria;
}
