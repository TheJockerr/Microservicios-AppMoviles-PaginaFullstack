package com.example.userscrudservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UsuarioUpdateRequest {

    @NotBlank
    private String username;

    private Integer puntaje;
}
