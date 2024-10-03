package br.com.robsonsouza.desafiobiblioteca.repository;

import br.com.robsonsouza.desafiobiblioteca.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setNome("Robson Teste");
        usuario.setEmail("robson.teste@example.com");
        usuario.setAtivo(true);

        usuarioRepository.save(usuario);
    }

    @Test
    void testFindByEmail_Success() {
        Optional<Usuario> found = usuarioRepository.findByEmail("robson.teste@example.com");
        assertTrue(found.isPresent());
        assertEquals("Robson Teste", found.get().getNome());
    }

    @Test
    void testFindAllAtivos_Success() {
        List<Usuario> ativos = usuarioRepository.findAllAtivos();
        assertFalse(ativos.isEmpty());
        assertTrue(ativos.contains(usuario));
    }

    @Test
    void testFindByEmail_NotFound() {
        Optional<Usuario> found = usuarioRepository.findByEmail("notfound@example.com");
        assertFalse(found.isPresent());
    }
}