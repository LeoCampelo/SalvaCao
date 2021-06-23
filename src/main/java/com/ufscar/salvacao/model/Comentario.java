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
@Table(name="comentario")
public class Comentario {
    
    @Id
    @Column(name="comentario_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int comentarioId;

    @Column(name="denuncia_id")
    private int reportId;
    
    @Column(name="data")
    private LocalDateTime date;

    @Column(name="comentario")
    private String comment;

    @Column(name="autor")
    private String author;

    public Comentario() {
        
    }

    public Comentario(Denuncia denuncia) {
        this.reportId = denuncia.getId();
        this.date = denuncia.getData();
        this.author = denuncia.getAuthor();
    }
}
