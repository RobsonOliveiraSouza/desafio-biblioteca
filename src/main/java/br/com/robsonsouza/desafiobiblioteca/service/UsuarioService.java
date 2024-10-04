package br.com.robsonsouza.desafiobiblioteca.service;

import br.com.robsonsouza.desafiobiblioteca.entity.Usuario;
import br.com.robsonsouza.desafiobiblioteca.repository.EmprestimoRepository;
import br.com.robsonsouza.desafiobiblioteca.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final EmprestimoRepository emprestimoRepository;

    public Usuario create(Usuario usuario) {
        usuario.setDataCadastro(LocalDateTime.now());
        return usuarioRepository.save(usuario);

    }

    public List<Usuario> list() {
        return usuarioRepository.findAllAtivos();
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado!"));
    }

    public Usuario update(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = findById(id);

        usuarioExistente.setNome(usuarioAtualizado.getNome());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setTelefone(usuarioAtualizado.getTelefone());

        return usuarioRepository.save(usuarioExistente);
    }

    public void delete(Long id) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();

            if (emprestimoRepository.existsByUsuarioAndStatus(usuario, "EMPRESTADO")) {
                throw new IllegalStateException("Usuário não pode ser excluído porque possui empréstimos ativos.");
            }

            usuario.setAtivo(false);
            usuarioRepository.save(usuario);
        } else {
            throw new IllegalArgumentException("Usuário não encontrado");
        }
    }
}
