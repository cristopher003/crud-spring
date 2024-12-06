package com.example.prueba.application.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class UsuarioResponseDTO {

    private Long id;
    private String nombre;
    private String email;
    private String contraseña;
}
