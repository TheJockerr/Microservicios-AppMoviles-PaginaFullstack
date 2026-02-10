package com.example.users_service.service.impl;


import com.example.users_service.exception.UsuarioException;
import com.example.users_service.exception.UsuarioNoEncontradoException;
import com.example.users_service.model.Usuario;
import com.example.users_service.repository.UsuarioRepository;
import com.example.users_service.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Usuario registrarUsuario(Usuario usuario) {
        if (usuarioRepository.existsByUsername(usuario.getUsername())) {
            throw new UsuarioException("El username ya existe");
        }
        usuario.setPasswordHash(passwordEncoder.encode(usuario.getPasswordHash()));
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> obtenerRanking() {
        return usuarioRepository.findAllByOrderByPuntajeDesc();
    }

    @Override
    public Usuario login(String username, String password) {
        Usuario usuario = usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getPasswordHash())) {
            throw new UsuarioException("Contraseña incorrecta");
        }
        return usuario;
    }

    @Override
    public Usuario actualizarPuntaje(Long idUsuario, int nuevoPuntaje) {
        Usuario usuario = obtenerPorId(idUsuario);
        usuario.setPuntaje(nuevoPuntaje);
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario obtenerPorId(Long idUsuario) {
        return usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
    }

    @Override
    public Usuario obtenerPorUsername(String username) {
        return usuarioRepository.findByUsername(username)
                .orElseThrow(() -> new UsuarioNoEncontradoException("Usuario no encontrado"));
    }
}
