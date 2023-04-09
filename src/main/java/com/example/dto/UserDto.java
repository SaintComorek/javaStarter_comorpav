package com.example.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserDto {


    private long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "lastName is mandatory")
    private String lastName;
    @NotBlank(message = "age")
    private int age;
    @NotBlank(message = "status")
    private String status;
    @NotBlank(message = "email address is mandatory")
    private String emailAddress;
    @NotBlank(message = "username")
    private String username;
    @NotBlank(message = "password")
    private String password;
    @NotBlank(message = "token")
    private String token;

    @NotBlank(message = "Group")
    private List<GroupDto> groupList = new ArrayList<>();
    @NotBlank(message = "Tag name ")
    private List<TagDto> tagList = new ArrayList<>();
    @NotBlank(message = "Note")
    private List<NoteDto> noteList = new ArrayList<>();


    public void setUserDto(Long id, String name, String lastname, int age, String status) {
        this.id = id;
        this.name = name;
        this.lastName = lastname;
        this.age = age;
        this.username = "";
        this.status = status;

    }

    public void setUserDto(String name, String lastname, int age, String status) {
        this.id = this.id++;
        this.name = name;
        this.lastName = lastname;
        this.age = age;
        this.username = "";
        this.status = status;

    }

    public UserDto (String name, String lastname, int age) {
        this.name = name;
        this.lastName = lastName;
        this.age = age;
        this.status = "basic";
        this.emailAddress = "";
        this.username = "";
        this.password = "";
        this.token = "";
        this.groupList = Collections.emptyList();
        this.tagList = Collections.emptyList();
        this.noteList = Collections.emptyList();
    }
    public void setUserDto(String name, String lastname,  String username , int age ) {
        this.id = this.id++;
        this.name = name;
        this.lastName = lastname;
        this.age = age;
        this.username = username;
        this.status = "basic";

    }

    public void setTagDtoToList(TagDto tagDto) {
        tagList.add(tagDto);
    }

    public void setNoteDtoToList(NoteDto noteDto) {
        noteList.add(noteDto);
    }

    public void setGroupDtoToList(GroupDto groupDto) {
        groupList.add(groupDto);
    }


}

