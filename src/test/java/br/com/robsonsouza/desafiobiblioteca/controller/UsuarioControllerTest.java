package br.com.robsonsouza.desafiobiblioteca.controller;

import br.com.robsonsouza.desafiobiblioteca.entity.Usuario;
import br.com.robsonsouza.desafiobiblioteca.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = UsuarioController.class)
class UsuarioControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private Usuario usuario;

    @BeforeEach
    public void setup() {
        usuario = Usuario.builder()
                .id(1L)
                .nome("Teste")
                .email("teste@exemplo.com")
                .ativo(true)
                .build();
    }

    @Test
    public void testCreateUsuario() throws Exception {
        when(usuarioService.create(any(Usuario.class))).thenReturn(usuario);

        mockMvc.perform(post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuario)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.nome").value("Teste"));
    }

    @Test
    public void testListUsuarios() throws Exception {
        when(usuarioService.list()).thenReturn(Collections.singletonList(usuario));

        mockMvc.perform(get("/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].nome").value("Teste"));
    }
}