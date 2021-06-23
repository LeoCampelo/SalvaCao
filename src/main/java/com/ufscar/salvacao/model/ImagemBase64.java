package com.ufscar.salvacao.model;

import java.io.Serializable;

import lombok.Data;

@Data
public class ImagemBase64 implements Serializable {
    private static final long serialVersionUID = 1L;

    private String iBase;
    private String filename;
    private String url;

    public ImagemBase64() {
    }

    public ImagemBase64(String iBase, String filename) {
        this.iBase = iBase;
        this.filename = filename;
    }
}
