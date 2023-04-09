package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Data
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE)
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Tag> tags;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Note> noteList;

    @ManyToOne
    @Cascade(CascadeType.ALL)
    private BaseUserModel baseUserModel;

    public void addNote(Note note) {
        noteList.add(note);
    }
    public void addTag(Tag tag){
        tags.add(tag);
    }

}
