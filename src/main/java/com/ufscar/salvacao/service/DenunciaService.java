package com.ufscar.salvacao.service;

import java.util.List;

import com.ufscar.salvacao.model.CommentDTO;
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

    public Denuncia updateStatusDenuncia(CommentDTO commentDTO) {
        Denuncia report = repository.findById(commentDTO.getReportId()).get();
        report.setStatus(commentDTO.getStatus());
        comentarioService.comentarioStatus(commentDTO);

        return repository.save(report);
    }

}
