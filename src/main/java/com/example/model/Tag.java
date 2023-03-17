package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tags")
@NoArgsConstructor
public class Tag {

    @Id
    private long id;

    @Column(name = "tagName")
    private String tag;

}
