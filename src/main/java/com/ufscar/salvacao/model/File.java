package com.ufscar.salvacao.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity(name = "File")
public class File implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name="denuncia_id")
    private int reportId;

    @Column(name="autor")
    private String author;

    @Column(name="filename")
    private String name;

    @Column(name="data")
    private LocalDateTime date;

    @Column(name="tamanho")
    private int size;

    @Column(name="url")
    private String url;

    @Column(name="fileBase64", columnDefinition="TEXT")
    private String fileBase64;

}
