package com.example.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import java.util.List;

@Data
@Entity
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    @Cascade(CascadeType.ALL)
    private Tag tag;

    @OneToMany
    @Cascade(CascadeType.ALL)
    private List<Note> noteList;


    @ManyToOne
    @Cascade(CascadeType.ALL)
    private BaseUserModel baseUserModel;

    public void addGroup_noteList(Note note)
    {
        noteList.add(note);
    }


}
