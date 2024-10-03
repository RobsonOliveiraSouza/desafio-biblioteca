package br.com.robsonsouza.desafiobiblioteca.service;

import br.com.robsonsouza.desafiobiblioteca.dto.EmprestimoDto;
import br.com.robsonsouza.desafiobiblioteca.entity.Emprestimo;
import br.com.robsonsouza.desafiobiblioteca.entity.Livro;
import br.com.robsonsouza.desafiobiblioteca.entity.Usuario;
import br.com.robsonsouza.desafiobiblioteca.repository.EmprestimoRepository;
import br.com.robsonsouza.desafiobiblioteca.repository.LivroRepository;
import br.com.robsonsouza.desafiobiblioteca.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class EmprestimoService {

    private EmprestimoRepository emprestimoRepository;
    private LivroRepository livroRepository;
    private UsuarioRepository usuarioRepository;

    public Emprestimo create(EmprestimoDto emprestimoDto) {
        Optional<Livro> livro = livroRepository.findByIsbn(emprestimoDto.getIsbn());
        Optional<Usuario> usuario = usuarioRepository.findByEmail(emprestimoDto.getEmail());

        if (livro.isEmpty() || usuario.isEmpty()) {
            throw new IllegalArgumentException("Livro ou Usuario não encontrado");
        }

        if (emprestimoRepository.existsByLivroAndStatus(livro.get(), "EMPRESTADO")) {
            throw new IllegalArgumentException("Livro já está emprestado!");
        }

        Emprestimo emprestimo = new Emprestimo();

        emprestimo.setLivro(livro.get());
        emprestimo.setUsuario(usuario.get());
        emprestimo.setDataEmprestimo(LocalDateTime.now());
        emprestimo.setStatus("EMPRESTADO");

        return emprestimoRepository.save(emprestimo);
    }

    public Emprestimo update(Long id, EmprestimoDto emprestimoDto) {
        Optional<Emprestimo> EmprestimoOptional = emprestimoRepository.findById(id);

        if (EmprestimoOptional.isEmpty()) {
            throw new IllegalArgumentException("Emprestimo não encontrado!");
        }

        Emprestimo emprestimo = EmprestimoOptional.get();

        String status = emprestimoDto.getStatus();
        if (status.equalsIgnoreCase("LIBERADO")) {
            emprestimo.setStatus(status);
            emprestimo.setDataDevolucao(LocalDateTime.now());
        } else if (status.equalsIgnoreCase("EMPRESTADO")) {
            emprestimo.setStatus(status);
            emprestimo.setDataDevolucao(null);
        } else {
            throw new IllegalArgumentException("Status inválido! Use 'EMPRESTADO' ou 'LIBERADO'.");
        }

        return emprestimoRepository.save(emprestimo);
    }

    public List<Emprestimo> list() {
        return emprestimoRepository.findAll();
    }

    public List<Livro> recomendarLivroPorCategoria(Long usuarioId) {
        List<Emprestimo> emprestimoUsuario = emprestimoRepository.findByUsuarioId(usuarioId);

        Set<Long> idLivroJaEmprestado = emprestimoUsuario.stream()
                .map(emprestimo -> emprestimo.getLivro().getId())
                .collect(Collectors.toSet());

        Set<String> categoriaJaEmprestado = emprestimoUsuario.stream()
                .map(emprestimo -> emprestimo.getLivro().getCategoria())
                .collect(Collectors.toSet());

        List<Livro> livroRecomendado = new ArrayList<>();

        for (String categoria : categoriaJaEmprestado) {
            List<Livro> livroPorGenero = livroRepository.findByCategoriaAndAtivoTrue(categoria);

            List<Livro> livroNaoEmprestado = livroPorGenero.stream()
                    .filter(livro -> !idLivroJaEmprestado.contains(livro.getId()))
                    .collect(Collectors.toList());

            livroRecomendado.addAll(livroNaoEmprestado);
        }
        return livroRecomendado;
    }
}