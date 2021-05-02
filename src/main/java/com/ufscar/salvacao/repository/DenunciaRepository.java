package com.ufscar.salvacao.repository;

import java.util.List;

import com.ufscar.salvacao.model.Denuncia;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface DenunciaRepository extends JpaRepository<Denuncia, Integer> {
    
    @Query(
        value = "SELECT * FROM denuncia u WHERE u.status = ?1", 
        nativeQuery = true)
    List<Denuncia> findAllDenunciasByStatus(char status);
}
