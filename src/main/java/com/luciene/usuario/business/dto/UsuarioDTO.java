package com.luciene.usuario.business.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder //facilita a conversão dos dados
public class UsuarioDTO {

   private String nome;
   private String email;
   private String senha;
   private List<EnderecoDTO> enderecos;
   private List<TelefoneDTO> telefones;

}
