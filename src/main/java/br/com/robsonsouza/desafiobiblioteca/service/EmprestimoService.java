package br.com.robsonsouza.desafiobiblioteca.service;

import br.com.robsonsouza.desafiobiblioteca.entity.Emprestimo;
import br.com.robsonsouza.desafiobiblioteca.entity.Livro;
import br.com.robsonsouza.desafiobiblioteca.entity.Usuario;
import br.com.robsonsouza.desafiobiblioteca.repository.EmprestimoRepository;
import br.com.robsonsouza.desafiobiblioteca.repository.LivroRepository;
import br.com.robsonsouza.desafiobiblioteca.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class EmprestimoService {

        private EmprestimoRepository emprestimoRepository;
        private LivroRepository livroRepository;
        private UsuarioRepository usuarioRepository;

        public Emprestimo create(Long livro_id, Emprestimo emprestimo) {
            Optional<Livro> livro = livroRepository.findById(livro_id);
            Optional<Usuario> usuario = usuarioRepository.findById(emprestimo.getUsuario().getId());

            if (livro.isEmpty() || usuario.isEmpty()) {
                throw new IllegalArgumentException("Livro ou Usuario não encontrado");
            }

            if (emprestimoRepository.existsByLivroAndStatus(livro.get(), "EMPRESTADO")) {
                throw new IllegalArgumentException("Livro já está emprestado!");
            }

            emprestimo.setLivro(livro.get());
            emprestimo.setUsuario(usuario.get());
            emprestimo.setData_emprestimo(new Date());
            emprestimo.setStatus("EMPRESTADO");

            return emprestimoRepository.save(emprestimo);
        }

        public Emprestimo update(Long id, String status, Date data_devolucao) {
            Optional<Emprestimo> EmprestimoOptional = emprestimoRepository.findById(id);

            if (EmprestimoOptional.isEmpty()) {
                throw new IllegalArgumentException("Emprestimo não encontrado!");
            }

            Emprestimo emprestimo = EmprestimoOptional.get();

            if(status.equalsIgnoreCase("LIBERADO")){
                emprestimo.setStatus(status);
                emprestimo.setData_devolucao(data_devolucao != null ? data_devolucao : new Date());
            } else if (status.equalsIgnoreCase("EMPRESTADO")) {
                emprestimo.setStatus(status);
                emprestimo.setData_devolucao(null);
            } else {
                throw new IllegalArgumentException("Status inválido! Use 'EMPRESTADO' ou 'LIBERADO'.");
            }
            return emprestimoRepository.save(emprestimo);
        }

        public List<Emprestimo> list() {
            return emprestimoRepository.findAll();
        }
}
