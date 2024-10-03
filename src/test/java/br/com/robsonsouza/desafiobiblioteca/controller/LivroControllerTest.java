package br.com.robsonsouza.desafiobiblioteca.controller;

import br.com.robsonsouza.desafiobiblioteca.entity.Livro;
import br.com.robsonsouza.desafiobiblioteca.service.LivroService;
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

@WebMvcTest(LivroController.class)
class LivroControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LivroService livroService;

    @Autowired
    private ObjectMapper objectMapper;

    private Livro livro;

    @BeforeEach
    public void setup() {
        livro = Livro.builder()
                .id(1L)
                .titulo("Livro Teste")
                .autor("Autor de Teste")
                .isbn("1234567890")
                .categoria("Ficção")
                .ativo(true)
                .build();
    }

    @Test
    public void testCreateLivro() throws Exception {
        when(livroService.create(any(Livro.class))).thenReturn(livro);

        mockMvc.perform(post("/livro")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(livro)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.titulo").value("Livro Teste"));
    }

    @Test
    public void testListLivros() throws Exception {
        when(livroService.list()).thenReturn(Collections.singletonList(livro));

        mockMvc.perform(get("/livro"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].titulo").value("Livro Teste"));
    }
}
