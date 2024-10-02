package br.com.robsonsouza.desafiobiblioteca.service;

import br.com.robsonsouza.desafiobiblioteca.entity.Livro;
import br.com.robsonsouza.desafiobiblioteca.repository.EmprestimoRepository;
import br.com.robsonsouza.desafiobiblioteca.repository.LivroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;
    private final EmprestimoRepository emprestimoRepository;

    public Livro create(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> list() {
        return livroRepository.findAllAtivos();
    }

    public Livro findById(Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Livro não encontrado!"));
    }

    public Livro update(Long id, Livro livroAtualizado) {
        Livro livroExistente = findById(id);

        livroExistente.setTitulo(livroAtualizado.getTitulo());
        livroExistente.setAutor(livroAtualizado.getAutor());
        livroExistente.setIsbn(livroAtualizado.getIsbn());
        livroExistente.setDataPublicacao(livroAtualizado.getDataPublicacao());
        livroExistente.setCategoria(livroAtualizado.getCategoria());


        return livroRepository.save(livroExistente);
    }

    public void delete(Long id) {
        Optional<Livro> livroOptional = livroRepository.findById(id);

        if (livroOptional.isPresent()) {
            Livro livro = livroOptional.get();

            if (emprestimoRepository.existsByLivroAndStatus(livro, "EMPRESTADO")) {
                throw new IllegalStateException("Livro não pode ser excluído porque está emprestado.");
            }

            livro.setAtivo(false);
            livroRepository.save(livro);
        } else {
            throw new IllegalArgumentException("Livro não encontrado");
        }
    }
}
