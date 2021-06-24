package com.ufscar.salvacao.controller;

import java.io.IOException;
import java.util.List;

import com.ufscar.salvacao.model.File;
import com.ufscar.salvacao.service.FileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/files")
public class FileController {

    @Autowired
    private FileService service;

    @PostMapping
    public ResponseEntity<File> uploadFile(@RequestBody File file) throws IOException {
        File fileUrl = service.upload(file);

        return new ResponseEntity<File>(fileUrl, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFile(@PathVariable int id) {
        service.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<File> findFileById(@PathVariable int id) {
        return new ResponseEntity<File>(service.findById(id), HttpStatus.OK);
    }

    @GetMapping("/reports/{id}")
    public ResponseEntity<List<File>> findAllFilesByReportId(@PathVariable int id) {
        return new ResponseEntity<List<File>>(service.findAllFilesByReportId(id), HttpStatus.OK);
    }
}
