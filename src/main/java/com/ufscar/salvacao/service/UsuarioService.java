package com.ufscar.salvacao.service;

import com.ufscar.salvacao.exception.UsuarioException;
import com.ufscar.salvacao.model.Usuario;
import com.ufscar.salvacao.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario save(Usuario usuario) throws UsuarioException {
        if(usuarioExistente(usuario.getEmail())) {
            throw new UsuarioException("Usuário já cadastrado!");
        } 

        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return repository.save(usuario);
    }

    private boolean usuarioExistente(String email) {
        Usuario usuario = repository.findByEmail(email);

        if(usuario != null) 
            return true;
        return false;
    }
}
