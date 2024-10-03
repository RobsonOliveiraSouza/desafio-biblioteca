package br.com.robsonsouza.desafiobiblioteca.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class EmprestimoDto {

    @NotBlank(message = "Por favor informe o email!")
    @Email(message = "O email utilizado é invalido!")
    private String email;

    @NotBlank(message = "Por favor informe o ISBN")
    private String isbn;

    @NotNull(message = "Data do emprestimo invalida")
    private LocalDateTime dataEmprestimo;

    @NotBlank(message = "Não consta o status do emprestimo!")
    private String status;
}