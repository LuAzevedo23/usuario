package com.luciene.usuario.infrastructure.repository;


import com.luciene.usuario.infrastructure.entity.Usuario;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    boolean existsByEmail(String email);

    //Ideal para tratar e evitar retorno quando nulo, caso usuario não exista, usamos o optional.
    Optional<Usuario> findByEmail(String Email);

    @Transactional
    void deleteByEmail(String email);
}
