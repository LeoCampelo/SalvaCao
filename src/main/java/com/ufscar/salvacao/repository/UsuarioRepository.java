package com.ufscar.salvacao.repository;

import com.ufscar.salvacao.model.Usuario;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, String> {
    
    @Query(value = "SELECT u FROM Usuario u WHERE u.email = ?1")
    Usuario findByEmail(String email);
}
