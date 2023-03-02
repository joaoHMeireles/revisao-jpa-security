package br.senai.sc.revisao.model.dto;

import lombok.Data;

@Data
public class UsuarioDTO {

    private Long id;

    private String email;

    private String senha;
}
