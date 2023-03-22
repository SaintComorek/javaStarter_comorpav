package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Entity
public class BaseUserModel {

    @Id
    private long id;
    @NotBlank(message = "Name is mandatory")
    private String name;
    @NotBlank(message = "lastName is mandatory")
    private String lastName;
    @NotBlank(message = "age")
    private int age;
    @NotBlank(message = "status")
    private String status;


}
