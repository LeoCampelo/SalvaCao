package com.ufscar.salvacao.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthenticationResponse implements Serializable {

    private String jwt;
    private String nome;
    private String email;
    private boolean isAdmin;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

}
