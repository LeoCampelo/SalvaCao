package com.ufscar.salvacao.service;

import java.util.List;

import com.ufscar.salvacao.model.Denuncia;
import com.ufscar.salvacao.repository.DenunciaRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DenunciaService {
    
    @Autowired
    private DenunciaRepository repository;

    public List<Denuncia> findAllDenunciasByStatus(char status) {
        return repository.findAllDenunciasByStatus(status);
    }

}
