package com.ufscar.salvacao.model;

import lombok.Data;

@Data
public class CommentDTO {

    private String author;
    private int reportId;
    private char status;
}
