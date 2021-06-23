package com.ufscar.salvacao.controller;

import com.ufscar.salvacao.exception.UsuarioException;
import com.ufscar.salvacao.model.Usuario;
import com.ufscar.salvacao.repository.UsuarioRepository;
import com.ufscar.salvacao.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;
    
    @Autowired
    private UsuarioService service;

    @PostMapping("/cadastro")
    public ResponseEntity<?> saveUsuario(@RequestBody Usuario usuario) throws UsuarioException {
        try{
            service.save(usuario);
        } catch (UsuarioException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usuário já cadastrado!");
        }

        return ResponseEntity.status(HttpStatus.OK).body("Usuário cadastrado com sucesso!");
    }

    @PutMapping("/usuario")
    public ResponseEntity<?> updateUsuario(@RequestBody Usuario usuario) {
        service.update(usuario);
        return ResponseEntity.status(HttpStatus.OK).body("Perfil atualizado!"); 
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUsuario(@RequestBody Usuario usuario) {
        repository.deleteById(usuario.getEmail());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
