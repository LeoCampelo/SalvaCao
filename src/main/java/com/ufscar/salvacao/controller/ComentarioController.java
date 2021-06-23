package com.ufscar.salvacao.controller;

import java.util.List;

import com.ufscar.salvacao.model.Comentario;
import com.ufscar.salvacao.repository.ComentarioRepository;
import com.ufscar.salvacao.service.ComentarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comment")
public class ComentarioController {
    
    @Autowired
    private ComentarioRepository repository;

    @Autowired
    private ComentarioService service;

    @GetMapping("/{id}")
    public ResponseEntity<List<Comentario>> findAllComentarioByDenunciaId(@PathVariable(value="id") Integer id) {
        return ResponseEntity.ok(service.findAllComentrioByDenunciaId(id));
    }

    @PostMapping
    public ResponseEntity<Comentario> saveComentario(@RequestBody Comentario comentario) {
        comentario = repository.save(comentario);

        return new ResponseEntity<Comentario>(comentario, HttpStatus.CREATED);
    }
}
