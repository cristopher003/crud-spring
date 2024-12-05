package com.example.prueba.application.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.prueba.domain.Usuario;
import com.example.prueba.infrastructure.repositories.UsuarioRepository;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

     @Transactional(readOnly = true)
    public List<Usuario> obtenerTodos() {
        return usuarioRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Usuario> obtenerPorId(Long id) {
        return usuarioRepository.findById(id);
    }

     @Transactional
    public Usuario crear(@Valid Usuario usuario) {
        if (usuarioRepository.findByEmail(usuario.getEmail()).isPresent()) {
            throw new RuntimeException("El email ya existe");
        }
        return usuarioRepository.save(usuario);
    }

    @Transactional
    public Usuario actualizar(Long id, @Valid Usuario usuarioActualizado) {
        Usuario usuarioExistente = usuarioRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
   
        usuarioRepository.findByEmail(usuarioActualizado.getEmail())
        .ifPresent(usuario -> {
            // Si el email encontrado no es el mismo usuario que estamos actualizando
            if (!usuario.getId().equals(id)) {
                throw new RuntimeException("El email ya está en uso por otro usuario");
            }
        });
        
       
                usuarioExistente.setNombre(usuarioActualizado.getNombre());
                usuarioExistente.setEmail(usuarioActualizado.getEmail());
                usuarioExistente.setContrasena(usuarioActualizado.getContrasena());

                return usuarioRepository.save(usuarioExistente);
            
            
    }

    @Transactional
    public void eliminar(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuarioRepository.delete(usuario);
    }
}
