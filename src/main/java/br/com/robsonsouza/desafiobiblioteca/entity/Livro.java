package br.com.robsonsouza.desafiobiblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "livro")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    @Column(unique = true, nullable = false)
    private String isbn;

    @Past(message = "A data de publicação é invalida!")
    @NotNull(message = "a data da publicação não pode ser vazia!")
    private LocalDateTime dataPublicacao;

    @NotBlank(message = "a categoria do livro não pode estar em branco!")
    private String categoria;

    private boolean ativo = true;
}
