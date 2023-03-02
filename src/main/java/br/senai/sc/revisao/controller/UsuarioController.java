package br.senai.sc.revisao.controller;

import br.senai.sc.revisao.model.dto.UsuarioDTO;
import br.senai.sc.revisao.model.entity.Usuario;
import br.senai.sc.revisao.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    private UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(value = "id") Long idUsuario) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(idUsuario));
        } catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

//        if (!usuarioService.existsById(idUsuario)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este ID");
//        }
//
//        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findById(idUsuario));
    }

    @GetMapping("/email/{email}")
    public ResponseEntity<Object> findByEmail(@PathVariable(value = "id") String email) {
        if (!usuarioService.existsByEmail(email)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este ID");
        }

        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.findByEmail(email));
    }

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody UsuarioDTO usuarioDTO) {
        if(usuarioService.existsByEmail(usuarioDTO.getEmail())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email já cadastrado no banco");
        }

        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> edit(@RequestBody UsuarioDTO usuarioDTO, @PathVariable(value = "id") Long idUsuario) {
        try{
            Usuario usuario = usuarioService.findById(idUsuario);
            BeanUtils.copyProperties(usuarioDTO, usuario);
            usuario.setIdUsuario(idUsuario);

            return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
        } catch(Exception exception){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
        }

//        if (!usuarioService.existsById(idUsuario)) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com o ID informado");
//        }
//
//        Usuario usuario = usuarioService.findById(idUsuario);
//        BeanUtils.copyProperties(usuarioDTO, usuario);
//        usuario.setIdUsuario(idUsuario);
//
//        return ResponseEntity.status(HttpStatus.OK).body(usuarioService.save(usuario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteById(@PathVariable(value = "id") Long idUsuario) {
        if (!usuarioService.existsById(idUsuario)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não foi encontrado nenhum usuário com este ID");
        }
        usuarioService.deleteById(idUsuario);
        return ResponseEntity.status(HttpStatus.OK).body("usuário deletado com sucesso!");
    }

}
