package com.luciene.usuario.business;

import com.luciene.usuario.business.converter.UsuarioConverter;
import com.luciene.usuario.business.dto.UsuarioDTO;
import com.luciene.usuario.infrastructure.entity.Usuario;
import com.luciene.usuario.infrastructure.exceptions.ConflictException;
import com.luciene.usuario.infrastructure.exceptions.ResourceNotFoundException;
import com.luciene.usuario.infrastructure.repository.UsuarioRepository;
import com.luciene.usuario.infrastructure.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioConverter usuarioConverter;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public UsuarioService(UsuarioRepository usuarioRepository, UsuarioConverter usuarioConverter, PasswordEncoder passwordEncoder, BCryptPasswordEncoder bCryptPasswordEncoder, JwtUtil jwtUtil) {
        this.usuarioRepository = usuarioRepository;
        this.usuarioConverter = usuarioConverter;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public UsuarioDTO salvaUsuario(UsuarioDTO usuarioDTO) {
        emailExiste(usuarioDTO.getEmail());
        usuarioDTO.setSenha(passwordEncoder.encode(usuarioDTO.getSenha()));
        Usuario usuario = usuarioConverter.paraUsuario(usuarioDTO);
        return usuarioConverter.paraUsuarioDTO(
                usuarioRepository.save(usuario)
        );
    }

    public void emailExiste(String email) {
        try {
            boolean existe = verificaEmailExistente(email);
            if (existe) {
                throw new ConflictException("Email já cadastrado" + email);
            }
        } catch (ConflictException e) {
            throw new ConflictException("Email já cadastrado" + e.getCause());
        }
    }

    public boolean verificaEmailExistente(String email) {

        return usuarioRepository.existsByEmail(email);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return usuarioRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Email não encontrado" + email));

    }

    public void deletaUsuarioPorEmail(String email) {

        usuarioRepository.deleteByEmail(email);
    }

    public UsuarioDTO atualizarDadosUsuario(String token, UsuarioDTO dto) {
        //Busquei o email do usuário através do token (para tirar a obrigatoriedade atraves do email)
        String email = jwtUtil.extrairEmailToken(token.substring(7));

        //Criptografia de senha
        dto.setSenha(dto.getSenha() != null ? passwordEncoder.encode(dto.getSenha()) : null );

        //Aqui busquei os dados do usuario no banco de dados
        Usuario usuarioEntity = usuarioRepository.findByEmail(email).orElseThrow(() ->
                new ResourceNotFoundException("Email não localizado"));

        //Aqui mesclei os dados que recebi na requisição  DTO com os dados do banco de dados
        Usuario usuario = usuarioConverter.updateUsuario(dto, usuarioEntity);

       //Salvei os dados do usuário convertido e depois peguei o retorno e convertido para UsuarioDto
        return usuarioConverter.paraUsuarioDTO((usuarioRepository.save(usuario)));
    }
}


