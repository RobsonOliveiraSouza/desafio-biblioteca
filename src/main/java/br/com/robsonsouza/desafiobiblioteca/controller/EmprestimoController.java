package br.com.robsonsouza.desafiobiblioteca.controller;

import br.com.robsonsouza.desafiobiblioteca.entity.Emprestimo;
import br.com.robsonsouza.desafiobiblioteca.service.EmprestimoService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping
@AllArgsConstructor
public class EmprestimoController {
    private final EmprestimoService emprestimoService;

    @PostMapping("/livro/{livro_id}")
    public ResponseEntity<Emprestimo> create (@PathVariable Long livro_id, @RequestBody Emprestimo emprestimo){
        Emprestimo novoEmprestimo = emprestimoService.create(livro_id, emprestimo);
        return ResponseEntity.ok(novoEmprestimo);
    }

    @PutMapping("/livro/{livro_id}")
    public ResponseEntity<Emprestimo> update(@PathVariable Long id, @RequestParam String status, @RequestParam(required = false) Date data_devolucao) {
        Emprestimo atualizado = emprestimoService.update(id, status, data_devolucao);
        return ResponseEntity.ok(atualizado);
    }

    @GetMapping
    public ResponseEntity<List<Emprestimo>> list () {
        List<Emprestimo> emprestimos = emprestimoService.list();
        return ResponseEntity.ok(emprestimos);
    }

}
