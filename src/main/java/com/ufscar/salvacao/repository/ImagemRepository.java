package com.ufscar.salvacao.repository;

import com.ufscar.salvacao.model.Imagem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagemRepository extends JpaRepository<Imagem, Integer> {
    
}
