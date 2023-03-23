package com.example.dto;

import com.example.model.User;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class NoteDto {
    @NotBlank(message = "Note id")
    private long id;

    @NotBlank(message = "tag name")
    private TagDto tag;

    @NotBlank(message = "value")
    private String value;

    @NotBlank(message = "User data")
    private  BaseUserDto baseUserDto;

}
