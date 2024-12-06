package com.example.prueba.application.mappers;

import org.springframework.stereotype.Component;

import com.example.prueba.application.dtos.UsuarioRequestDTO;
import com.example.prueba.application.dtos.UsuarioResponseDTO;
import com.example.prueba.domain.Usuario;

@Component
public class UsuarioMapper {

    public UsuarioResponseDTO toResponseDTO(Usuario usuario) {
        return UsuarioResponseDTO.builder()
            .id(usuario.getId())
            .nombre(usuario.getNombre())
            .email(usuario.getEmail())
            .contraseña(usuario.getContrasena())
            .build();
    }

    // Convierte de DTO de Solicitud a Entidad
    public Usuario toEntity(UsuarioRequestDTO usuarioDTO) {
        return Usuario.builder()
            .nombre(usuarioDTO.getNombre())
            .email(usuarioDTO.getEmail())
            .contrasena(usuarioDTO.getContrasena())
            .build();
    }
}

