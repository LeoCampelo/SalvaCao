package com.ufscar.salvacao.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="denuncia")
public class Denuncia {
    
    @Id
    @Column(name="denuncia_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name="descricao")
    private String descricao;

    @Column(name="endereco")
    private String endereco;

    @Column(name="especie")
    private String especie;

    @Column(name="raca")
    private String raca;

    @Column(name="status")
    private char status;

    @Column(name="ind_anonimo")
    private boolean indAnonimo;

    @Column(name="latitude")
    private String latitude;

    @Column(name="longitude")
    private String longitude;

    @Column(name="data")
    private LocalDateTime data;

    @Column(name="autor")
    private String author;

    @Column(name="usuario_id")
    private int usuarioId;

}
