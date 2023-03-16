package com.example.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class GroupDto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private NoteDto note;
    private TagDto tag;

}
