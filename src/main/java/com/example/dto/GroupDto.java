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
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDto {

    @NotBlank(message = "Group id ")
    private long id;

    @NotBlank(message = "Note recommended")
    private NoteDto note;
    @NotBlank(message = "tag recomended")
    private TagDto tag;

    private  BaseUserDto baseUserDto;

}
