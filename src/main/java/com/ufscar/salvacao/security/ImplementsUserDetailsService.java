package com.ufscar.salvacao.security;

import com.ufscar.salvacao.model.Usuario;
import com.ufscar.salvacao.repository.UsuarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ImplementsUserDetailsService implements UserDetailsService{
    
    @Autowired
    private UsuarioRepository repository;

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Usuario usuario = repository.findByLogin(login);

        if(usuario == null) 
            throw new UsernameNotFoundException("Usuário não encontrado");
        
        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
    }
}
