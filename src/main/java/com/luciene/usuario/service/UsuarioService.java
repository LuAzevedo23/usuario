package com.luciene.usuario.service;

import com.luciene.usuario.infrastructure.entity.Usuario;
import com.luciene.usuario.infrastructure.repository.UsuarioRepository;
import com.luciene.usuario.service.converter.UsuarioConverter;
import com.luciene.usuario.service.dto.UsuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO){
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );
    }
}
