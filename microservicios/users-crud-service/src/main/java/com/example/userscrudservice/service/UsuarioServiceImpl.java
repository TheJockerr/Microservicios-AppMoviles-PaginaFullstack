package com.example.userscrudservice.service;

import com.example.userscrudservice.dto.UsuarioUpdateRequest;
import com.example.userscrudservice.model.Usuario;
import com.example.userscrudservice.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public Usuario update(Long id, UsuarioUpdateRequest request) {
        Usuario usuario = findById(id);

        usuario.setUsername(request.getUsername());

        if (request.getPuntaje() != null) {
            usuario.setPuntaje(request.getPuntaje());
        }

        return usuarioRepository.save(usuario);
    }

    @Override
    public void delete(Long id) {
        Usuario usuario = findById(id);
        usuarioRepository.delete(usuario);
    }
}
