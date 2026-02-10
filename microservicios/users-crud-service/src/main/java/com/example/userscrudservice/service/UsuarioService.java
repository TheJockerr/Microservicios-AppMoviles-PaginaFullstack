package com.example.userscrudservice.service;

import com.example.userscrudservice.dto.UsuarioUpdateRequest;
import com.example.userscrudservice.model.Usuario;

import java.util.List;

public interface UsuarioService {

    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario update(Long id, UsuarioUpdateRequest request);
    void delete(Long id);
}
