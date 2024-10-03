package br.com.robsonsouza.desafiobiblioteca.service;
import br.com.robsonsouza.desafiobiblioteca.entity.Usuario;
import br.com.robsonsouza.desafiobiblioteca.repository.EmprestimoRepository;
import br.com.robsonsouza.desafiobiblioteca.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UsuarioServiceTest {
    @InjectMocks
    private UsuarioService usuarioService;

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private EmprestimoRepository emprestimoRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateUsuario_Success() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("test@example.com");

        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        Usuario result = usuarioService.create(usuario);

        assertNotNull(result);
        assertEquals("test@example.com", result.getEmail());
        verify(usuarioRepository, times(1)).save(any(Usuario.class));
    }

    @Test
    void testFindById_Success() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setEmail("test@example.com");

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));

        Usuario result = usuarioService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    void testFindById_NotFound() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.findById(1L);
        });

        assertEquals("Usuário não encontrado!", exception.getMessage());
    }

    @Test
    void testDeleteUsuario_Success() {
        Usuario usuario = new Usuario();
        usuario.setId(1L);
        usuario.setAtivo(true);

        when(usuarioRepository.findById(1L)).thenReturn(Optional.of(usuario));
        when(emprestimoRepository.existsByUsuarioAndStatus(usuario, "EMPRESTADO")).thenReturn(false);
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuario);

        usuarioService.delete(1L);

        assertFalse(usuario.isAtivo());
        verify(usuarioRepository, times(1)).save(usuario);
        verify(usuarioRepository, never()).delete(any(Usuario.class));
    }

    @Test
    void testDeleteUsuario_NotFound() {
        when(usuarioRepository.findById(1L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            usuarioService.delete(1L);
        });

        assertEquals("Usuário não encontrado", exception.getMessage());
        verify(usuarioRepository, never()).save(any(Usuario.class));
    }
}