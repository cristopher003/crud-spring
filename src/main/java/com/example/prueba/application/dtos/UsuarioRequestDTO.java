package com.example.prueba.application.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
// @NoArgsConstructor
@AllArgsConstructor
@Builder
public class UsuarioRequestDTO  {

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "Nombre debe tener entre 2 y 50 caracteres")
    private String nombre;

    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Formato de email inválido")
    private String email;

    @NotNull(message = "Contraseña requerida")
    private String contrasena;
}
