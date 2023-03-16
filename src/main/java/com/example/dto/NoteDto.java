package com.example.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class NoteDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private TagDto tag;
    private String words;

}
