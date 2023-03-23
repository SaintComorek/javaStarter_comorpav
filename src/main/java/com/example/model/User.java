package com.example.model;

import com.example.security.UserToken;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "name")
    private String name;
    @Column(name = "lastname")
    private String lastName;
    @Column(name = "age")
    private int age;
    @Column(name = "status")
    private String status;
    @Column(name = "email")
    private String emailAddress;

    @Column(name = "username")
    private String username;
    @Column(name = "password")
    private String password;
    @Column(name = "token")
    private String token;

    @OneToMany
    List<Note> noteList = new ArrayList<>();
    @OneToMany
    List<Tag> tagList = new ArrayList<>();
    @OneToMany
    List<Group> groupList = new ArrayList<>();


    public void createUser(String name, String lastName , String emailAddress, String username , String password , int age) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.status = "basic";
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.token = UserToken.generateNewToken();
    }

    public void createUser(String name, String lastName, String emailAddress, String username , String password, int age, String status) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.status = status;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.token = UserToken.generateNewToken();
    }

}
