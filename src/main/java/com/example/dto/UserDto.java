package com.example.dto;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    @NotBlank(message = "Group")
    private GroupDto group;
    @NotBlank(message = "Note")
    private NoteDto note;
    @NotBlank(message = "Tag name ")
    private TagDto tag;

    public UserDto(String sss, String aaa, int i1, GroupDto groupDto, NoteDto noteDto, TagDto tagDto) {
    }

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

