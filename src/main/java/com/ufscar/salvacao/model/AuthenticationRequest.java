package com.ufscar.salvacao.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthenticationRequest implements Serializable {
    
    private String email;
    private String password;
}
