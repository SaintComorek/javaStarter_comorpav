package com.example.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tags")
@NoArgsConstructor
public class Tag {

    @Id
    private long id;

    @Column(name = "name")
    private String tag;

}
