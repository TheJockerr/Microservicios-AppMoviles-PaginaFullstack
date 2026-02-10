package com.example.users_service.repository;

import com.example.users_service.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    // Buscar usuario por username
    Optional<Usuario> findByUsername(String username);

    // Verificar que no exista un username repetido
    boolean existsByUsername(String username);

    List<Usuario> findAllByOrderByPuntajeDesc();
}
