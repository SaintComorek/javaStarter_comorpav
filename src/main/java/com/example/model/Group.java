package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @OneToOne
    private Tag tag;
    @OneToOne
    private Note note;

    @ManyToOne
    private BaseUserModel baseUserModel;

}
