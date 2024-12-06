package com.example.prueba.presentation.controlers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.prueba.application.dtos.UsuarioRequestDTO;
import com.example.prueba.application.dtos.UsuarioResponseDTO;
import com.example.prueba.application.services.UsuarioService;


import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class UsuarioController  {

    private final UsuarioService usuarioService;

    @GetMapping
    public List<UsuarioResponseDTO> obtenerTodos() {
        return usuarioService.obtenerTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> obtenerPorId(@PathVariable Long id) {
        // return usuarioService.obtenerPorId(id)
        //         .map(ResponseEntity::ok)
        //         .orElseGet(() -> ResponseEntity.notFound().build());
        return ResponseEntity.ok(usuarioService.obtenerPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioResponseDTO> crear(@Valid @RequestBody UsuarioRequestDTO usuarioDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(usuarioService.crear(usuarioDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> actualizar
    (@PathVariable Long id, @Valid @RequestBody UsuarioRequestDTO usuarioDTO) {
        return ResponseEntity.ok(usuarioService.actualizar(id, usuarioDTO));
    }

   @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
        return ResponseEntity.noContent().build();
    } 
}
