package br.com.robsonsouza.desafiobiblioteca.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import lombok.Data;

import java.util.Date;

@Entity
@Table(name = "emprestimo")
@Data
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O campo não pode estar vazio!")
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    @ManyToOne
    private Usuario usuario;

    @NotNull(message = "O campo não pode estar vazio!")
    @JoinColumn(name = "livro_id", referencedColumnName = "id")
    @ManyToOne
    private Livro livro;

    @NotNull(message = "A data de emprestimo está em branco")
    @Past(message = "A data de emprestimo é invalida!")
    private Date dataEmprestimo;

    @NotNull(message = "A data de devolução está em branco")
    private Date dataDevolucao;

    @NotBlank(message = "O campo status não pode estar vazio")
    private String status;
}