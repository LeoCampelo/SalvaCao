package com.ufscar.salvacao.repository;

import com.ufscar.salvacao.model.Imagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
    
    @Query(value = "select img from FOTOS img where img.url = ?1")
    Imagem findByURL(String url);
}
