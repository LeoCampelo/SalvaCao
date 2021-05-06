package com.ufscar.salvacao.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class AuthenticationResponse implements Serializable {

    private final String jwt;
}
