package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@Entity
@Table(name = "notes")
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private long id;

    @Column(name = "value")
    private String value;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private  Tag tag;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    private BaseUserModel baseUserModel;;


}
