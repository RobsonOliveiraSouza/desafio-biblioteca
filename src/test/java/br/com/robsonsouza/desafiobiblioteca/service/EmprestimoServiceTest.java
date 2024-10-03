package br.com.robsonsouza.desafiobiblioteca.service;

import br.com.robsonsouza.desafiobiblioteca.dto.EmprestimoDto;
import br.com.robsonsouza.desafiobiblioteca.entity.Emprestimo;
import br.com.robsonsouza.desafiobiblioteca.entity.Livro;
import br.com.robsonsouza.desafiobiblioteca.entity.Usuario;
import br.com.robsonsouza.desafiobiblioteca.repository.EmprestimoRepository;
import br.com.robsonsouza.desafiobiblioteca.repository.LivroRepository;
import br.com.robsonsouza.desafiobiblioteca.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class EmprestimoServiceTest {

    @InjectMocks
    private EmprestimoService emprestimoService;

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @Mock
    private LivroRepository livroRepository;

    @Mock
    private UsuarioRepository usuarioRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateEmprestimo_Success() {
        Livro livro = new Livro();
        livro.setId(1L);
        livro.setIsbn("123456");
        livro.setCategoria("Ficção");

        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("usuario@example.com");

        EmprestimoDto emprestimoDto = new EmprestimoDto();
        emprestimoDto.setIsbn("123456");
        emprestimoDto.setEmail("usuario@example.com");

        when(livroRepository.findByIsbn("123456")).thenReturn(Optional.of(livro));
        when(usuarioRepository.findByEmail("usuario@example.com")).thenReturn(Optional.of(usuario));
        when(emprestimoRepository.existsByLivroAndStatus(livro, "EMPRESTADO")).thenReturn(false);
        when(emprestimoRepository.save(any(Emprestimo.class))).thenReturn(new Emprestimo());

        Emprestimo result = emprestimoService.create(emprestimoDto);

        assertNotNull(result);
        verify(emprestimoRepository).save(any(Emprestimo.class));
    }

    @Test
    void testCreateEmprestimo_LivroNotFound() {
        EmprestimoDto emprestimoDto = new EmprestimoDto();
        emprestimoDto.setIsbn("123456");
        emprestimoDto.setEmail("usuario@example.com");

        when(livroRepository.findByIsbn("123456")).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            emprestimoService.create(emprestimoDto);
        });
        assertEquals("Livro ou Usuario não encontrado", exception.getMessage());
    }
}