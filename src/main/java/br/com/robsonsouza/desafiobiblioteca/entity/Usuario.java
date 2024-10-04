package br.com.robsonsouza.desafiobiblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "usuario")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    private LocalDateTime dataCadastro;

    @NotBlank(message = "O campo telefone não pode estar em branco!")
    @Pattern(regexp = "\\{2}\\{9}", message = "O telefone deve possuir DDD + 9 digitos.")
    private String telefone;

    private boolean ativo = true;
}
