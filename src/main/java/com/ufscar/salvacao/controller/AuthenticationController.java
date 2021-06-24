package com.ufscar.salvacao.controller;

import com.ufscar.salvacao.model.AuthenticationRequest;
import com.ufscar.salvacao.model.AuthenticationRequestGoogle;
import com.ufscar.salvacao.model.AuthenticationResponse;
import com.ufscar.salvacao.model.Usuario;
import com.ufscar.salvacao.repository.UsuarioRepository;
import com.ufscar.salvacao.security.ImplementsUserDetailsService;
import com.ufscar.salvacao.util.JwtUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {
    
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private ImplementsUserDetailsService userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> createAthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
        try {
            authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new Exception("Usuário ou senha incorreto", e);
        }
        
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails);

        AuthenticationResponse response = new AuthenticationResponse(jwt);
        Usuario usuario = usuarioRepository.findByEmail(authenticationRequest.getEmail());
        response.setEmail(usuario.getEmail()); 
        response.setNome(usuario.getNome());
        response.setAdmin(usuario.isAdmin());

        return ResponseEntity.ok(response);
    }

    @PostMapping("/google")
    public ResponseEntity<?> createAthenticationTokenGoogle(@RequestBody AuthenticationRequestGoogle authenticationRequest) throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsernameGoogle(authenticationRequest);

        final String jwt = jwtUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

}
