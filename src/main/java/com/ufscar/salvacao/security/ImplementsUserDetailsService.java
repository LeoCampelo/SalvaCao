package com.ufscar.salvacao.security;

import java.util.ArrayList;
import java.util.List;

import com.ufscar.salvacao.model.AuthenticationProvider;
import com.ufscar.salvacao.model.AuthenticationRequestGoogle;
import com.ufscar.salvacao.model.Role;
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

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(email);

        if(usuario == null) 
            throw new UsernameNotFoundException("Usuário não encontrado");
        
        if(usuario.getPassword() == null) 
            return new User(usuario.getUsername(), "", true, true, true, true, usuario.getAuthorities());
        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
    }

    public UserDetails loadUser(String email) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(email);

        if(usuario == null) 
            throw new UsernameNotFoundException("Usuário não encontrado");

        List<Role> roles = new ArrayList<>();
        usuario.setRoles(roles);
        
        if(usuario.getPassword() == null) 
            return new User(usuario.getUsername(), "", true, true, true, true, usuario.getAuthorities());
        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
    }

    public UserDetails loadUserByUsernameGoogle(AuthenticationRequestGoogle authenticationRequest) throws UsernameNotFoundException {
        Usuario usuario = repository.findByEmail(authenticationRequest.getEmail());

        if(usuario == null) {
            return createNewUser(authenticationRequest);
        }
        
        return new User(usuario.getUsername(), "", true, true, true, true, usuario.getAuthorities());
    }

    public UserDetails createNewUser(AuthenticationRequestGoogle authenticationRequest) {
        Usuario usuario = new Usuario();
    
        usuario.setEmail(authenticationRequest.getEmail());
        usuario.setNome(authenticationRequest.getNome());
        usuario.setAuthProvider(AuthenticationProvider.GOOGLE);

        Role role = new Role();
        role.setNomeRole("ROLE_USER");
        List<Role> roles = new ArrayList<Role>();
        roles.add(role);

        repository.save(usuario);

        return new User(usuario.getUsername(), "", true, true, true, true, roles);
    }

}
