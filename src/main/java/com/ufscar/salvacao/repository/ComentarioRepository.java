package com.ufscar.salvacao.repository;

import java.util.List;

import com.ufscar.salvacao.model.Comentario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface ComentarioRepository extends JpaRepository<Comentario, Integer> {
    
    @Query(
        value = "SELECT * FROM comentario u WHERE u.denuncia_id = ?1 ORDER BY data ASC", 
        nativeQuery = true)
    public List<Comentario> findAllComentarioByDenunciaId(int id);
}
