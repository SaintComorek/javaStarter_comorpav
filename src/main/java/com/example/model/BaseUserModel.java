package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

@Data
@Entity
public class BaseUserModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @NotBlank(message = "Name is mandatory")
    @Cascade(CascadeType.ALL)
    private String name;

    @NotBlank(message = "User name is mandatory")
    @Cascade(CascadeType.ALL)
    private String username;
    @NotBlank(message = "lastName is mandatory")
    @Cascade(CascadeType.ALL)
    private String lastName;


}
