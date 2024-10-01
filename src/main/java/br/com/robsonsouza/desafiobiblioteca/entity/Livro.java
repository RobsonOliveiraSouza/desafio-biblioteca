package br.com.robsonsouza.desafiobiblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "livro")
@Data
public class Livro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotBlank(message = "o titúlo da obra não pode estar em branco!")
    private String titulo;

    @NotBlank(message = "o nome do autor não pode estar em branco!")
    private String autor;

    @NotBlank(message = "ISBN invalido!")
    private String isbn;

    @Past(message = "A data de publicação é invalida!")
    @NotNull(message = "a data da publicação não pode ser vazia!")
    private Date data_publicacao;

    @NotBlank(message = "a categoria do livro não pode estar em branco!")
    private String categoria;
}
