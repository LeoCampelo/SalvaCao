package com.ufscar.salvacao.controller;

import java.io.IOException;

//import com.google.api.client.util.Base64;
//import com.google.cloud.storage.BlobInfo;
//import com.google.cloud.storage.Storage;
import com.ufscar.salvacao.model.Imagem;
import com.ufscar.salvacao.model.ImagemBase64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

public class FileController {
    
    //@Autowired
    //private Storage storage;

    public void uploadFile(@RequestBody ImagemBase64 imagem) throws IOException {
        final String fileName = imagem.getFilename();

        String contentType = imagem.getIBase().split(",")[0].split(";")[0].split(":")[1];
        String img_data = imagem.getIBase().split(",")[1];
        //byte[] decodedBytes = Base64.decodeBase64(img_data);

        /*
        BlobInfo blobInfo =
                storage.create(
                        BlobInfo.newBuilder("edifreitas-bucket", fileName)
                                .setContentType(contentType)
                                .build(),
                            decodedBytes
                        );
                        */

        //return imagemService.insert(new Imagem(null, imagem.getFilename(), blobInfo.getMediaLink()));
    }
}
