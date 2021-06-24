package com.ufscar.salvacao.service;

import java.io.IOException;
import java.util.List;

import com.google.api.client.util.Base64;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.ufscar.salvacao.model.File;
import com.ufscar.salvacao.repository.FileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
    
    @Autowired
    private Storage storage;

    @Autowired
    private FileRepository repository;

    public File upload(File file) throws IOException {
        final String fileName = file.getName();

        byte[] decodedBytes = Base64.decodeBase64(file.getFileBase64());

        BlobInfo blobInfo = storage.create(BlobInfo.newBuilder("salvacao-api", fileName).build(), decodedBytes);

        file.setUrl(blobInfo.getMediaLink());
        return repository.save(file);
    }

    public void delete(int id) {
        repository.deleteById(id);
    }

    public File findById(int id) {
        return repository.findById(id).get();
    }
    
    public List<File> findAllFilesByReportId(int id) {
        return repository.findAllFilesByDenunciaId(id);
    }

}
