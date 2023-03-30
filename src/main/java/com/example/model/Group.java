package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
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

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Tag tag;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Note> noteList;

    @JsonIgnore
    @ManyToOne
    @Cascade(CascadeType.ALL)
    private BaseUserModel baseUserModel;

    public void addGroup_noteList(Note note) {

        noteList.add(note);

    }

}
