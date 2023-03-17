package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "notes")
@NoArgsConstructor
public class Note {

    @Id
    private long id;

    @Column(name = "value")
    private String words;

    @OneToOne
    private  Tag tag;


}
