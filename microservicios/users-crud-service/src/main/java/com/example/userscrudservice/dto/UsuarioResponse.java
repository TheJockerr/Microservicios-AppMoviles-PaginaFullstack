package com.example.userscrudservice.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class UsuarioResponse {

    private Long id;
    private String username;
    private int puntaje;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;
}
