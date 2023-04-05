package com.example.dto;

import com.example.model.BaseUserModel;
import com.example.model.Tag;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GroupDto {

    @NotBlank(message = "Group id ")
    private long id;
    @NotBlank(message = "Group name")
    private String name;
    @NotBlank(message = "Note recommended")
    private NoteDto note;
    @NotBlank(message = "tag recomended")
    private List<TagDto> tags ;

    @NotBlank(message = "User data")
    private  BaseUserDto baseUserDto;


    public void GroupDto(String name , NoteDto noteDto , TagDto tagDto  , BaseUserModel baseUserModel){
        this.id = id;
        this.name = name;
        this.note = note;
        addToTagsList(tagDto);
        this.baseUserDto = baseUserDto;

    }

    public void addToTagsList(TagDto tagDto)
    {
        tags.add(tagDto);

    }

}
