package br.senai.sc.revisao.security.service;

import br.senai.sc.revisao.model.entity.Usuario;
import br.senai.sc.revisao.repository.UsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UsuarioServiceSecurity implements UserDetailsService {

    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(username);

        if (usuarioOptional.isPresent()) {
            return usuarioOptional.get();
        }

        throw new UsernameNotFoundException("Usuário não encontrado");
    }
}
