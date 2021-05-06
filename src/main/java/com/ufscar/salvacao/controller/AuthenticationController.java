package com.ufscar.salvacao.controller;

import java.util.Calendar;
import java.util.TimeZone;

import com.ufscar.salvacao.model.AuthenticationRequest;
import com.ufscar.salvacao.model.AuthenticationRequestGoogle;
import com.ufscar.salvacao.model.AuthenticationResponse;
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

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));

        return ResponseEntity.ok(new AuthenticationResponse(jwt, c.getTime().toString()));
    }

    @PostMapping("/google")
    public ResponseEntity<?> createAthenticationTokenGoogle(@RequestBody AuthenticationRequestGoogle authenticationRequest) throws Exception {
        UserDetails userDetails = userDetailsService.loadUserByUsernameGoogle(authenticationRequest);

        final String jwt = jwtUtil.generateToken(userDetails);

        Calendar c = Calendar.getInstance(TimeZone.getTimeZone("America/Sao_Paulo"));

        return ResponseEntity.ok(new AuthenticationResponse(jwt, c.getTime().toString()));
    }

}
