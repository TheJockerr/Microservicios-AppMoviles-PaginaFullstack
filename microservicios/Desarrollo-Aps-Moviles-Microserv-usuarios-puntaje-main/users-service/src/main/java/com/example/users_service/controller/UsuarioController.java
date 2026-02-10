package com.example.users_service.controller;


import com.example.users_service.dto.LoginRequest;
import com.example.users_service.dto.RegistroRequest;
import com.example.users_service.dto.UsuarioResponse;
import com.example.users_service.model.Usuario;
import com.example.users_service.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/registro")
    public ResponseEntity<UsuarioResponse> registrar(@Valid @RequestBody RegistroRequest request) {
        Usuario usuario = new Usuario();
        usuario.setUsername(request.getUsername());
        usuario.setPasswordHash(request.getPassword());

        Usuario creado = usuarioService.registrarUsuario(usuario);

        return ResponseEntity.ok(UsuarioResponse.builder()
                .id(creado.getIdUsuario())
                .username(creado.getUsername())
                .puntaje(creado.getPuntaje())
                .build());
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioResponse> login(@Valid @RequestBody LoginRequest request) {
        Usuario usuario = usuarioService.login(request.getUsername(), request.getPassword());
        return ResponseEntity.ok(UsuarioResponse.builder()
                .id(usuario.getIdUsuario())
                .username(usuario.getUsername())
                .puntaje(usuario.getPuntaje())
                .build());
    }

    @PutMapping("/{id}/puntaje")
    public ResponseEntity<UsuarioResponse> actualizarPuntaje(
            @PathVariable Long id,
            @RequestParam int puntaje) {

        Usuario usuario = usuarioService.actualizarPuntaje(id, puntaje);
        return ResponseEntity.ok(UsuarioResponse.builder()
                .id(usuario.getIdUsuario())
                .username(usuario.getUsername())
                .puntaje(usuario.getPuntaje())
                .build());
    }

    @GetMapping("/ranking")
    public ResponseEntity<List<UsuarioResponse>> obtenerRanking() {
        List<Usuario> ranking = usuarioService.obtenerRanking();
        List<UsuarioResponse> respuesta = ranking.stream()
            .map(usuario -> UsuarioResponse.builder()
                    .id(usuario.getIdUsuario())
                    .username(usuario.getUsername())
                    .puntaje(usuario.getPuntaje())
                    .build())
            .toList();

        return ResponseEntity.ok(respuesta);
    }

}
