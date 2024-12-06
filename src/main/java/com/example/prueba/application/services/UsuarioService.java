package com.example.prueba.application.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba.application.dtos.UsuarioRequestDTO;
import com.example.prueba.application.dtos.UsuarioResponseDTO;
import com.example.prueba.application.mappers.UsuarioMapper;
import com.example.prueba.domain.Usuario;
import com.example.prueba.infrastructure.repositories.UsuarioRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioMapper usuarioMapper;

     @Transactional(readOnly = true)
    public List<UsuarioResponseDTO> obtenerTodos() {
        // return usuarioRepository.findAll();
        return usuarioRepository.findAll().stream()
            .map(usuarioMapper::toResponseDTO)
            .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UsuarioResponseDTO obtenerPorId(Long id) {
        Usuario usuario=usuarioRepository.findById(id).orElseThrow(()->new RuntimeException("Usuario no encontrado con ID: " + id));
        return usuarioMapper.toResponseDTO(usuario);
    }

     @Transactional
    public UsuarioResponseDTO crear(@Valid UsuarioRequestDTO usuarioDTO) {

        if (usuarioRepository.findByEmail(usuarioDTO.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya existe");
        }

        Usuario usuario=usuarioMapper.toEntity(usuarioDTO);
        return usuarioMapper.toResponseDTO(usuarioRepository.save(usuario));
    }

    @Transactional
    public UsuarioResponseDTO actualizar(Long id, @Valid UsuarioRequestDTO usuarioDTO) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
   
        usuarioRepository.findByEmail(usuarioDTO.getEmail())
        .ifPresent(usuario -> {
            // Si el email encontrado no es el mismo usuario que estamos actualizando
            if (!usuario.getId().equals(id)) {
                throw new RuntimeException("El email ya está en uso por otro usuario");
            }
        });
        
       
                usuarioExistente.setNombre(usuarioDTO.getNombre());
                usuarioExistente.setEmail(usuarioDTO.getEmail());
                usuarioExistente.setContrasena(usuarioDTO.getContrasena());


                return usuarioMapper.toResponseDTO(usuarioRepository.save(usuarioExistente));
            
    }

    @Transactional
    public void eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }

    
}
