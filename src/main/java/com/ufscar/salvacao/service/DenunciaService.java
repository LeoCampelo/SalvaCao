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

    @Autowired
    private ComentarioService comentarioService;

    public List<Denuncia> findAllDenunciasByStatus(char status) {
        return repository.findAllDenunciasByStatus(status);
    }

    public Denuncia saveDenuncia(Denuncia denuncia) {
        denuncia = repository.save(denuncia);
        comentarioService.comentarioDenunciaAberta(denuncia);
        return denuncia;
    }

    public Denuncia updateStatus(int id, char status) {
        Denuncia denuncia = repository.findById(id).get();
        denuncia.setStatus(status);
        comentarioService.comentarioStatus(denuncia);
        return repository.save(denuncia);
    }

    public Denuncia updateDenuncia(Denuncia denuncia) {
        Denuncia report = repository.findById(denuncia.getId()).get();

        if(report.getStatus() != denuncia.getStatus()) {
            comentarioService.comentarioStatus(denuncia);
        }

        return repository.save(denuncia);
    }

}
