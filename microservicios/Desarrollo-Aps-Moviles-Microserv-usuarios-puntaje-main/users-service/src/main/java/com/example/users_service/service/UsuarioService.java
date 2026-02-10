package com.example.users_service.service;


import com.example.users_service.model.Usuario;
import java.util.List;

public interface UsuarioService {

    Usuario registrarUsuario(Usuario usuario);

    Usuario login(String username, String password);

    Usuario actualizarPuntaje(Long idUsuario, int nuevoPuntaje);

    Usuario obtenerPorId(Long idUsuario);

    Usuario obtenerPorUsername(String username);

    List<Usuario> obtenerRanking();

}
