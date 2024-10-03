package br.com.robsonsouza.desafiobiblioteca.repository;

import br.com.robsonsouza.desafiobiblioteca.entity.Livro;
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
class LivroRepositoryTest {

    @Autowired
    private LivroRepository livroRepository;

    private Livro livro;

    @BeforeEach
    void setUp() {
        livro = new Livro();
        livro.setTitulo("A Guerra dos Tronos");
        livro.setAutor("George R. R. Martin");
        livro.setIsbn("1234567890");
        livro.setCategoria("Fantasia");
        livro.setAtivo(true);

        livroRepository.save(livro);
    }

    @Test
    void testFindByIsbn_Success() {
        Optional<Livro> found = livroRepository.findByIsbn("1234567890");
        assertTrue(found.isPresent());
        assertEquals("A Guerra dos Tronos", found.get().getTitulo());
    }

    @Test
    void testFindByCategoriaAndAtivoTrue_Success() {
        List<Livro> livros = livroRepository.findByCategoriaAndAtivoTrue("Fantasia");
        assertFalse(livros.isEmpty());
        assertTrue(livros.contains(livro));
    }

    @Test
    void testFindAllAtivos_Success() {
        List<Livro> ativos = livroRepository.findAllAtivos();
        assertFalse(ativos.isEmpty());
        assertTrue(ativos.contains(livro));
    }
}