package com.ufscar.salvacao.repository;

import java.util.List;

import com.ufscar.salvacao.model.File;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface FileRepository extends CrudRepository<File, Integer> {
    
    @Query(value = "SELECT u FROM File u WHERE u.reportId = ?1")
    List<File> findAllFilesByDenunciaId(int id);
}
