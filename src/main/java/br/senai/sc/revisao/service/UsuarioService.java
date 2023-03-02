package br.senai.sc.revisao.service;

import br.senai.sc.revisao.model.entity.Usuario;
import br.senai.sc.revisao.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    public Usuario findById(Long idUsuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(idUsuario);
        if(usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }

        throw new RuntimeException("Usuario não encontrado");
    }

    public Usuario findByEmail(String email) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(email);;
        if(usuarioOptional.isPresent()){
            return usuarioOptional.get();
        }

        throw new RuntimeException("Usuario não encontrado");
    }

    public <S extends Usuario> S save(S entity) {
        return usuarioRepository.save(entity);
    }
    public boolean existsById(Long idUsuario) {
        return usuarioRepository.existsById(idUsuario);
    }

    public boolean existsByEmail(String email) {
        return usuarioRepository.existsByEmail(email);
    }

    public void deleteById(Long idUsuario) {
        usuarioRepository.deleteById(idUsuario);
    }
}
