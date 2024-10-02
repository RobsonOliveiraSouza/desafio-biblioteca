package br.com.robsonsouza.desafiobiblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "usuario")
@Data
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    private Long id;

    @NotBlank(message = "O nome não pode estar em branco!")
    private String nome;

    @Email(message = "O email utilizado é invalido!")
    @Column(unique = true, nullable = false)
    private String email;

    @NotNull(message = "O campo de data de cadastro não pode estar em branco!")
    @Past(message = "A data de publicação é invalida!")
    private Date dataCadastro;

    @NotBlank(message = "O campo telefone não pode estar em branco!")
    @Pattern(regexp = "\\{2}\\{9}", message = "O telefone deve possuir DDD + 9 digitos.")
    private String telefone;

    private boolean ativo = true;
}
