package br.com.robsonsouza.desafiobiblioteca.service;
import br.com.robsonsouza.desafiobiblioteca.entity.Livro;
import br.com.robsonsouza.desafiobiblioteca.repository.LivroRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class LivroServiceTest {
    @InjectMocks
    private LivroService livroService;

    @Mock
    private LivroRepository livroRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateLivro_Success() {
        Livro livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("Livro Teste");

        when(livroRepository.save(any(Livro.class))).thenReturn(livro);

        Livro result = livroService.create(livro);

        assertNotNull(result);
        assertEquals("Livro Teste", result.getTitulo());
    }

    @Test
    void testFindById_Success() {
        Livro livro = new Livro();
        livro.setId(1L);
        livro.setTitulo("Livro Teste");

        when(livroRepository.findById(1L)).thenReturn(Optional.of(livro));

        Livro result = livroService.findById(1L);

        assertNotNull(result);
        assertEquals("Livro Teste", result.getTitulo());
    }

    @Test
    void testFindById_NotFound() {
        when(livroRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            livroService.findById(1L);
        });

        assertEquals("Livro não encontrado!", exception.getMessage());
    }

    @Test
    void testListByCategoria_Success() {
        Livro livro1 = new Livro();
        livro1.setCategoria("Ficção");

        Livro livro2 = new Livro();
        livro2.setCategoria("Ficção");

        when(livroRepository.findByCategoriaAndAtivoTrue("Ficção")).thenReturn(Arrays.asList(livro1, livro2));

        List<Livro> livros = livroService.listByCategoria("Ficção");

        assertNotNull(livros);
        assertEquals(2, livros.size());
    }
}