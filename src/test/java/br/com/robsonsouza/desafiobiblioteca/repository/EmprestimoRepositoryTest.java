package br.com.robsonsouza.desafiobiblioteca.repository;

import br.com.robsonsouza.desafiobiblioteca.entity.Emprestimo;
import br.com.robsonsouza.desafiobiblioteca.entity.Livro;
import br.com.robsonsouza.desafiobiblioteca.entity.Usuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class EmprestimoRepositoryTest {
    @Autowired
    private EmprestimoRepository emprestimoRepository;

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    private Usuario usuario;
    private Livro livro;
    private Emprestimo emprestimo;

    @BeforeEach
    void setUp() {
        usuario = new Usuario();
        usuario.setNome("Joao Teste");
        usuario.setEmail("joao.teste@example.com");
        usuario.setAtivo(true);

        livro = new Livro();
        livro.setTitulo("O Senhor dos Anéis");
        livro.setAutor("J.R.R. Tolkien");
        livro.setIsbn("0987654321");
        livro.setCategoria("Fantasia");
        livro.setAtivo(true);

        usuarioRepository.save(usuario);
        livroRepository.save(livro);

        emprestimo = new Emprestimo();
        emprestimo.setUsuario(usuario);
        emprestimo.setLivro(livro);
        emprestimo.setStatus("EMPRESTADO");

        emprestimoRepository.save(emprestimo);
    }

    @Test
    void testExistsByLivroAndStatus_Success() {
        Boolean exists = emprestimoRepository.existsByLivroAndStatus(livro, "EMPRESTADO");
        assertTrue(exists);
    }

    @Test
    void testExistsByUsuarioAndStatus_Success() {
        Boolean exists = emprestimoRepository.existsByUsuarioAndStatus(usuario, "EMPRESTADO");
        assertTrue(exists);
    }

    @Test
    void testFindByUsuarioId_Success() {
        List<Emprestimo> emprestimos = emprestimoRepository.findByUsuarioId(usuario.getId());
        assertFalse(emprestimos.isEmpty());
        assertTrue(emprestimos.contains(emprestimo));
    }
}