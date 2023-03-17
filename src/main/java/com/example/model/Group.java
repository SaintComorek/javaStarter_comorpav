package com.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "groups")
public class Group {

    @Id
    private long id;
    @OneToOne
    private Tag tag;
    @OneToOne
    private Note note;

}
