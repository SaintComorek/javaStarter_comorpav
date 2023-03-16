package com.example.dto;


import com.example.model.Group;
import com.example.model.Note;
import com.example.model.Tag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String lastName;
    private int age;
    private String status;
    private Group group;
    private Note note;
    private Tag tag;

    public void setUserDto(Long id, String name, String lastname, int age, String status) {
        this.id = id;
        this.name = name;
        this.lastName = lastname;
        this.age = age;
        this.status = status;

    }
    public void setUserDto(String name, String lastname, int age, String status) {
        this.id = this.id++;
        this.name = name;
        this.lastName = lastname;
        this.age = age;
        this.status = status;

    }


}

