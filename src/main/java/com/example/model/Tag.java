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
    @GeneratedValue(strategy= GenerationType.TABLE)
    private long id;

    @Column(name = "tagName")
    private String tagName;

    @ManyToOne(cascade = CascadeType.ALL)
    private BaseUserModel baseUserModel;

}
