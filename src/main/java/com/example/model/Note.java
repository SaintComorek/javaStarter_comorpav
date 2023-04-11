package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "notes")
@NoArgsConstructor
public class Note {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private long id;

    @Column(name = "name")
    private String name;

    @Column(name = "value")
    private String value;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Tag> tags ;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    private BaseUserModel baseUserModel;;

    public void addTag(Tag tag){
        tags.add(tag);
    }

}
