package com.ufscar.salvacao.service;

import java.util.List;

import com.ufscar.salvacao.model.Comentario;
import com.ufscar.salvacao.model.CommentDTO;
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
        comentario.setComment("Caso aberto");

        repository.save(comentario);
    }

    public void comentarioStatus(CommentDTO commentDTO) {
        Comentario comentario = new Comentario(commentDTO);

        if(commentDTO.getStatus() == 'A') {
            comentario.setComment("A");
        } else if(commentDTO.getStatus() == 'P') {
            comentario.setComment("P");
        } else if(commentDTO.getStatus() == 'F') {
            comentario.setComment("F");
        }

        repository.save(comentario);
    }
}
