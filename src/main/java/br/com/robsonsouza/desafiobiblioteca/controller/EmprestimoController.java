package br.com.robsonsouza.desafiobiblioteca.controller;

import br.com.robsonsouza.desafiobiblioteca.dto.EmprestimoDto;
import br.com.robsonsouza.desafiobiblioteca.entity.Emprestimo;
import br.com.robsonsouza.desafiobiblioteca.entity.Livro;
import br.com.robsonsouza.desafiobiblioteca.service.EmprestimoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/emprestimo")
@AllArgsConstructor
public class EmprestimoController {
    private final EmprestimoService emprestimoService;

    @PostMapping
    public ResponseEntity<Emprestimo> create(@RequestBody EmprestimoDto emprestimoDto) {
        Emprestimo novoEmprestimo = emprestimoService.create(emprestimoDto);
        return ResponseEntity.ok(novoEmprestimo);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Emprestimo> update(@PathVariable Long id, @RequestBody EmprestimoDto emprestimoDto) {
        Emprestimo atualizado = emprestimoService.update(id, emprestimoDto);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> list () {
        List<Emprestimo> emprestimos = emprestimoService.list();
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/recomendar")
    public ResponseEntity<List<Livro>> recomendarLivroPorCategoria(@RequestParam Long usuarioId) {
        List<Livro> livroRecomendado = emprestimoService.recomendarLivroPorCategoria(usuarioId);
        return ResponseEntity.ok(livroRecomendado);
    }
}