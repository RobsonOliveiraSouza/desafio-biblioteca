package br.com.robsonsouza.desafiobiblioteca.repository;

import br.com.robsonsouza.desafiobiblioteca.entity.Emprestimo;
import br.com.robsonsouza.desafiobiblioteca.entity.Livro;
import br.com.robsonsouza.desafiobiblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmprestimoRepository extends JpaRepository<Emprestimo, Long> {
    Boolean existsByLivroAndStatus(Livro livro, String status);
    Boolean existsByUsuarioAndStatus(Usuario usuario, String status);
}