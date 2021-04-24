package com.ufscar.salvacao.repository;

import com.ufscar.salvacao.model.Denuncia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {
    
}
