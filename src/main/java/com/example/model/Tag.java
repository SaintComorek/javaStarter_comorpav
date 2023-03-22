package com.example.model;

import com.example.dto.BaseUserDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tags")
@NoArgsConstructor
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "tagName")
    private String tag;

    @ManyToOne
    private BaseUserModel baseUserModel;

}
