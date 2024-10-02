package br.com.robsonsouza.desafiobiblioteca.repository;

import br.com.robsonsouza.desafiobiblioteca.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);

    @Query("SELECT u FROM Usuario u WHERE u.ativo = true")
    List<Usuario> findAllAtivos();
}
