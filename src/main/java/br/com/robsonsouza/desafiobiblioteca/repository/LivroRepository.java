package br.com.robsonsouza.desafiobiblioteca.repository;

import br.com.robsonsouza.desafiobiblioteca.entity.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    Optional<Livro> findByIsbn(String isbn);

    @Query("SELECT l FROM Livro l WHERE l.ativo = true")
    List<Livro> findAllAtivos();

    @Query("SELECT l FROM Livro l WHERE l.categoria = :categoria AND l.ativo = true")
    List<Livro> findByCategoriaAndAtivoTrue(@Param("categoria") String categoria);
}
