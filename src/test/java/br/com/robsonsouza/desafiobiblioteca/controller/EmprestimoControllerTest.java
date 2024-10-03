package br.com.robsonsouza.desafiobiblioteca.controller;

import br.com.robsonsouza.desafiobiblioteca.dto.EmprestimoDto;
import br.com.robsonsouza.desafiobiblioteca.entity.Emprestimo;
import br.com.robsonsouza.desafiobiblioteca.entity.Livro;
import br.com.robsonsouza.desafiobiblioteca.entity.Usuario;
import br.com.robsonsouza.desafiobiblioteca.service.EmprestimoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.Date;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmprestimoController.class)
class EmprestimoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmprestimoService emprestimoService;

    @Autowired
    private ObjectMapper objectMapper;

    private Emprestimo emprestimo;
    private EmprestimoDto emprestimoDto;

    private Usuario usuario;
    private Livro livro;

    @BeforeEach
    public void setup() {
        usuario = Usuario.builder()
                .id(1L)
                .nome("Usuário de Teste")
                .email("usuario@exemplo.com")
                .ativo(true)
                .build();

        livro = Livro.builder()
                .id(1L)
                .titulo("Livro de Teste")
                .autor("Autor de Teste")
                .isbn("1234567890")
                .categoria("Ficção")
                .ativo(true)
                .build();

        Date dataEmprestimo = new Date();
        Date dataDevolucao = new Date(System.currentTimeMillis() + (7 * 24 * 60 * 60 * 1000L)); // 7 dias. ps: deveria ter usado LocalTime :/

        emprestimo = Emprestimo.builder()
                .id(1L)
                .usuario(usuario)
                .livro(livro)
                .dataEmprestimo(dataEmprestimo)
                .dataDevolucao(dataDevolucao)
                .status("EMPRESTADO")
                .build();

        emprestimoDto = new EmprestimoDto();
        emprestimoDto.setEmail(usuario.getEmail());
        emprestimoDto.setIsbn(livro.getIsbn());
        emprestimoDto.setDataEmprestimo(dataEmprestimo);
        emprestimoDto.setStatus("EMPRESTADO");
    }

    @Test
    public void testCreateEmprestimo() throws Exception {
        when(emprestimoService.create(any(EmprestimoDto.class))).thenReturn(emprestimo);

        mockMvc.perform(post("/emprestimo")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(emprestimoDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.status").value("EMPRESTADO"));
    }

    @Test
    public void testListEmprestimos() throws Exception {
        when(emprestimoService.list()).thenReturn(Collections.singletonList(emprestimo));

        mockMvc.perform(get("/emprestimo"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].status").value("EMPRESTADO"));
    }
}
