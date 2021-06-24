package com.ufscar.salvacao.service;

import java.util.List;

import com.ufscar.salvacao.model.Comentario;
import com.ufscar.salvacao.model.Denuncia;
import com.ufscar.salvacao.repository.ComentarioRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ComentarioService {
    
    @Autowired
    private ComentarioRepository repository;

    public List<Comentario> findAllComentrioByDenunciaId (int id) {
        return repository.findAllComentarioByDenunciaId(id);
    }

    public void comentarioDenunciaAberta(Denuncia denuncia) {
        Comentario comentario = new Comentario(denuncia);
        comentario.setComment("Caso aberto!");

        repository.save(comentario);
    }

    public void comentarioStatus(Denuncia denuncia) {
        Comentario comentario = new Comentario(denuncia);

        if(denuncia.getStatus() == 'A') {
            comentario.setComment("Caso reaberto");
        } else if(denuncia.getStatus() == 'P') {
            comentario.setComment("Caso sendo processado");
        } else if(denuncia.getStatus() == 'F') {
            comentario.setComment("Caso encerrado");
        }

        repository.save(comentario);
    }
}
