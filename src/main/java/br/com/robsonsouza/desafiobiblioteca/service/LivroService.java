package br.com.robsonsouza.desafiobiblioteca.service;

import br.com.robsonsouza.desafiobiblioteca.entity.Livro;
import br.com.robsonsouza.desafiobiblioteca.repository.LivroRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class LivroService {

    private final LivroRepository livroRepository;

    public Livro create(Livro livro) {
        return livroRepository.save(livro);
    }

    public List<Livro> list() {
        return livroRepository.findAll();
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
        livroExistente.setData_publicacao(livroAtualizado.getData_publicacao());
        livroExistente.setCategoria(livroAtualizado.getCategoria());


        return livroRepository.save(livroExistente);
    }

    public void delete(Long id) {
        livroRepository.deleteById(id);
    }
}
