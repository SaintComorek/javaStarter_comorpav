package com.example.model;

import jakarta.persistence.*;
import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@Entity
@Table(name = "groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonIgnore
    @OneToOne
    @Cascade(CascadeType.ALL)
    private Tag tag;
    @JsonIgnore
    @OneToOne
    @Cascade(CascadeType.ALL)
    private Note note;

    @JsonIgnore
    @ManyToOne
    @Cascade(CascadeType.ALL)
    private BaseUserModel baseUserModel;

}
