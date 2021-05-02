package com.ufscar.salvacao.controller;

import java.util.List;
import java.util.Optional;

import com.ufscar.salvacao.model.Denuncia;
import com.ufscar.salvacao.repository.DenunciaRepository;
import com.ufscar.salvacao.service.DenunciaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {
    
    @Autowired
    private DenunciaRepository repository;

    @Autowired
    private DenunciaService service;

    @GetMapping
    public List<Denuncia> findAllDenuncias() {
        return repository.findAll();
    }

    @GetMapping("/status/{status}")
    public List<Denuncia> findAllDenunciasByStatus(@PathVariable(value = "status") char status) {
        return service.findAllDenunciasByStatus(status);
    }

    @GetMapping("/{id}")
    public Optional<Denuncia> findById(@PathVariable(value="id") Integer id) {
        return repository.findById(id);
    }

    @PostMapping
    public ResponseEntity<Denuncia> saveDenuncia(@RequestBody Denuncia denuncia) {
        denuncia = repository.save(denuncia);

        return new ResponseEntity<Denuncia>(denuncia, HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Denuncia> updateDenuncia(@RequestBody Denuncia denuncia) {
        denuncia = repository.save(denuncia);

        return new ResponseEntity<Denuncia>(denuncia, HttpStatus.OK);
    }

    @DeleteMapping
    @RequestMapping("/{id}")
    public ResponseEntity<Denuncia> deleteDenuncia(@PathVariable(value="id") Integer id) {
         repository.deleteById(id);

         return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
